package com.mec.cs.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mec.cs.common.Communication;
import com.mec.cs.common.ConversationCommand;
import com.mec.cs.common.ConversationMessage;
import com.mec.cs.server.interfaces.IConversationListenner;
import com.mec.cs.server.interfaces.IServerViewListenner;
import com.mec.cs.server.interfaces.IServerViewPublisher;
import com.mec.request.action.RequestAction;

public class Server implements Runnable ,IConversationListenner ,IServerViewPublisher{
	private ServerSocket serverSocket;
	private Conversation conversation;
	private boolean isStart;
	private IServerViewListenner listenner;
	
	private boolean startAccept;
	
	private int port;
	
	public Server(IServerViewListenner listenner,int port) {
		isStart = false;
		startAccept = false;
		this.listenner = listenner;
		this.port = port;
	}
	
	public void startUp() {
		if(isStart){
			this.listenner.serverMessageGianed("�������ѿ����������ٴο���");
			return;
		}
		
		try {
			this.listenner.serverMessageGianed("������׼������......");
			serverSocket = new ServerSocket(port);
			this.isStart = true;
			this.startAccept = true;
			new Thread(this).start();
			this.listenner.serverMessageGianed("�������ѿ�����");
			conversation = new Conversation(this);
			conversation.setAction(new RequestAction());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void conversationMessageGained(ConversationMessage conversationMessage) {
	   String message = conversationMessage.getMessage();
	   String id = conversationMessage.getFrom();
		this.listenner.serverMessageGianed(id+":" +message);
	}

	@Override
	public void run() {
		while(startAccept) {
			try {
				this.listenner.serverMessageGianed("��������ʼ����...");
				Socket socket = serverSocket.accept();
				 Communication communication = new Communication(socket, conversation);
				 String id = socket.getInetAddress().getHostAddress();
//				 if(CSUtile.getValue(CSUtile.ALLOW_SAME_ID).equals(0)){
//					 if(conversation.isIdSame(id)){
//						 this.listenner.serverMessageGianed("���û������ߣ������ظ����ߣ�");
//						 continue;
//					 }
//				 }
				 id= id +"@" +communication.hashCode();
				 this.listenner.serverMessageGianed(id+":�����ߣ�");
				 conversation.setSocket(id,communication);
			} catch (IOException e) { 
			}
			
		}
	}
	
	public void  shutDown() {
		try {
			if(conversation.getCommunicationCount()>0){
				conversation.clearCommunication();
			}
			this.startAccept = false;
			this.serverSocket.close();
			this.isStart = false;
			this.listenner.serverMessageGianed("�������ѹرգ�");
		} catch (IOException e) {
		}
	}
	public boolean isServerStart(){
		return this.isStart;
	}

	@Override
	public void removeListenner(IServerViewListenner listenner) {
	}

	public int getCommunicationCount() {
		 return this.conversation.getCommunicationCount();
	}

}
