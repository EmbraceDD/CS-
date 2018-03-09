package com.mec.cs.common;

import java.io.DataInputStream;
 import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Communication implements Runnable, ICommunicationPublisher {
	//public static Logger logger = LogManager.getLogger(Communication.class);
	
	private  ICommunicationListenner listenner;
	private Socket socket;
	private  DataInputStream dis;
	private  DataOutputStream dos;
	
	private boolean  startReceive;
	private Thread thread;
	
	private String id;
	//private String debug;  //控制日志是否需要写入 
	
	
	public Communication(Socket socket, ICommunicationListenner listenner) {
		this.listenner = listenner;
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			
			this.startReceive = true;
			thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void sendMessage(String message){
		 try {
			this.dos.writeUTF(message);
		} catch (IOException e) {
			CommunicationMessage communicationMessage = new CommunicationMessage();
			communicationMessage.setCommand(CommunicationCommand.SEND_FAILURE);
			communicationMessage.setId(this.id);
			communicationMessage.setMessage("对方断线！！！");
			this.listenner.communicationMessageGained(communicationMessage);
		};
		
	}
	
	private String receiveMessage() throws IOException{
		 String message= null;
		 message= this.dis.readUTF();
		 return message;
	}
	
	public void stopCommnuication(){
		/*
		 * 这个方法是一个中断信道过程
		 */
      this.startReceive = false;
		
		try {
			if(this.dis != null){
				this.dis.close();
				this.dis =null;
			}
			
			if(this.dos != null){
				this.dos.close();
				this.dos = null;
			}
			
			if(this.socket != null){
				this.socket.close();
				this.socket = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setStartReceive() {
		this.startReceive = false;
	}

	@Override
	public void setCommnictaionListenner(ICommunicationListenner listenner) {
		 if(this.listenner != null){
			 return;
		 }
		 this.listenner = listenner;
	}

	@Override
	public void removeCommnictaionListenner(ICommunicationListenner listenner) {
		if(this.listenner == null){
			return;
		}
		this.listenner = null;
	}

	@Override
	public void run() {
		String message = null;
		 while(startReceive){
			 try {
				 
				message = receiveMessage();
				CommunicationMessage communicationMessage = new CommunicationMessage();
				communicationMessage.setCommand(CommunicationCommand.RECEIVE_OK);
				communicationMessage.setId(this.id);
				communicationMessage.setMessage(message);
				this.listenner.communicationMessageGained(communicationMessage);
			} catch (IOException e) {
				 CommunicationMessage communicationMessage = new CommunicationMessage();
				 communicationMessage.setCommand(CommunicationCommand.RECEIVE_FAILURE);
				 communicationMessage.setId(this.id);
				 communicationMessage.setMessage("异常下线！！！");
				 this.listenner.communicationMessageGained(communicationMessage);
				 stopCommnuication();
			}
		 }
		 
		 stopCommnuication();
	}

}
