package com.mec.cs.client.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mec.cs.client.interfaces.IClientViewListenner;
import com.mec.cs.client.interfaces.IClientViewPublisher;
import com.mec.cs.common.Communication;
import com.mec.cs.common.CommunicationCommand;
import com.mec.cs.common.CommunicationMessage;
import com.mec.cs.common.ConversationCommand;
import com.mec.cs.common.ConversationMessage;
import com.mec.cs.common.ICommunicationListenner;

public  abstract class MecClient implements ICommunicationListenner,IClientViewPublisher{
	
	private Communication  communication;
	private Socket socket;
	private boolean isStart;
	private String ip;
	private int port;
	
	private IClientViewListenner listenner;
	private ConversationCommand command;
	private String to;
	
	public MecClient(String ip,int port,IClientViewListenner listenner) {
		this.ip = ip;
		this.port = port;
		this.listenner = listenner;
	}

	public void start() {
		try {
			socket = new Socket(ip, port);
			this.isStart = true;
			communication = new Communication(socket, this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * �÷����ǿͻ��������Ͽ����ӣ�ǿ�����ߣ�δ֪ͨ��������������
	 */
	public void offLine() {
		isStart = false;
		communication.stopCommnuication();
	}
	//���յ����ص�ֵ֮���������
	public abstract void dealResponseMessage(String action,String message);
	//������崻�֮���ҿͻ��˸���ʲô��
	public abstract void netFailure();
	
	public void setActionAndParameter(String action,String parameter){
		ConversationMessage conversationMessage = new ConversationMessage();
		conversationMessage.setAction(action);
		if(this.command == null){
			conversationMessage.setCommand(ConversationCommand.REQUEST);
		}else{
			conversationMessage.setCommand(this.command);
			this.command =null;
		}
		if(this.to == null){
			conversationMessage.setFrom("SERVER");
		}else{
			conversationMessage.setTo(this.to);
			this.to = null;
		}
		conversationMessage.setFrom(communication.getId());
		conversationMessage.setMessage(parameter);
		communication.sendMessage(conversationMessage.toMessage(conversationMessage));
	}
	
	public void setCommand(ConversationCommand command){
		this.command = command;
	}
	
    public void setTo(String to){
		this.to = to;
	}

	@Override
	public void communicationMessageGained(CommunicationMessage communicationMessage) {
		CommunicationCommand command = communicationMessage.getCommand();
		String message = communicationMessage.getMessage();
		
		switch (command) {
		case RECEIVE_FAILURE:
			this.listenner.clientMessageGianed("������崻���");
			netFailure();
			break;
		case RECEIVE_OK:
			 dealReceiveMessage(message);
			break;
		case SEND_FAILURE:
			this.listenner.clientMessageGianed("������崻���");
			netFailure();
			break;
		default:
			break;
		}
	}

	private void dealReceiveMessage(String message) {
		  ConversationMessage conversationMessage = new ConversationMessage();
          conversationMessage = (ConversationMessage) conversationMessage.toIConversationMessage(message);
          
          ConversationCommand command= conversationMessage.getCommand();
          String mess = conversationMessage.getMessage();
          String action= conversationMessage.getAction();
          String from = conversationMessage.getFrom();
          
          switch (command) {
			case ID:
				communication.setId(mess);
				break;
			case NET_FAILUREE:
				this.listenner.clientMessageGianed(message);
				break;
			case NORMAL_MESSAGE:
				this.listenner.clientMessageGianed(message);
				break;
			case OFF_LINE:
				communication.setStartReceive();
				this.listenner.clientMessageGianed("�����������ߣ�");
				break;
			case RESPONSE:
				dealResponseMessage(action,mess);
				break;
			case TO_ALL:
				this.listenner.clientMessageGianed("����"+from+"�㲥��Ϣ��"+mess);
				break;
			case TO_ONE:
				this.listenner.clientMessageGianed("����"+from+"��Ϣ��"+mess);
				break;
			case TO_OTHER:
				this.listenner.clientMessageGianed("����"+from+"Ⱥ����Ϣ��"+mess);
				break;
			default:
				break;
			}
	}
	
	public boolean isStart() {
		return isStart;
	}

	@Override
	public void setListenner(IClientViewListenner listenner) {
		if(this.listenner != null){
			return;
		}
		this.listenner = listenner;
	}

	@Override
	public void removeListenner(IClientViewListenner listenner) {
		if(this.listenner == null) {
			return;
		}
		this.listenner = null;
	}
}
