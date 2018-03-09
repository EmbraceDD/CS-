package com.mec.cs.server.core;

import java.util.HashMap;
import java.util.Map;

import com.mec.cs.common.Communication;
import com.mec.cs.common.CommunicationCommand;
import com.mec.cs.common.CommunicationMessage;
import com.mec.cs.common.ConversationCommand;
import com.mec.cs.common.ConversationMessage;
import com.mec.cs.common.ICommunicationListenner;
import com.mec.cs.server.interfaces.IConversationListenner;
import com.mec.cs.server.interfaces.IConversationPublisher;
import com.mec.request.action.IActionInterfaces;
import com.mec.request.action.RequestAction;

public class Conversation implements ICommunicationListenner, IConversationPublisher{
	
	private IConversationListenner listenner;
	private  Map<String , Communication> communicationMap;
	private IActionInterfaces action;
	
	public Conversation(IConversationListenner listenner) {
		communicationMap = new HashMap<>();
		this.listenner = listenner;
	}
	public void setAction(IActionInterfaces action) {
		this.action = action;
	}
	
	public void setSocket(String id,Communication communication){
		communication.setId(id);
		 ConversationMessage conversationMessage = new ConversationMessage();
		 conversationMessage.setCommand(ConversationCommand.ID);
		 conversationMessage.setMessage(id);
		 communication.sendMessage(conversationMessage.toMessage(conversationMessage));
		 communicationMap.put(id, communication);
	}
	
	public boolean isIdSame(String id ){
		for(String key: communicationMap.keySet()){
			int index = key.indexOf("@");
			key = key.substring(0,index);
			if(id.equals(key)){
				return true;
			}
		}
		return false;
	}
	
	public int getCommunicationCount() {
		return communicationMap.size();
	}
	
	public void clearCommunication() {
		for(Communication communication : communicationMap.values()){
			communication.stopCommnuication();
		}
	}
	
	   private void  transpondMessage(ConversationMessage message ){
	       Communication communication = 	communicationMap.get(message.getTo());
	       communication.sendMessage(message.toString());
		}
	    private void  transpondMessage(boolean all,ConversationMessage message){
	    	String from = message.getFrom();
	    	
	    		for(String key : communicationMap.keySet()){
	    			if(!all && from.equals(key)) {
	    				continue;
	    			}
	    			 Communication communication = communicationMap.get(key);
	    			 ConversationMessage netMessage = new ConversationMessage();
	    			 netMessage.setFrom(message.getFrom())
	    			 									.setTo(key)
	    			 									.setCommand(message.getCommand())
	    			 									.setMessage(message.getMessage());
	    			 communication.sendMessage(netMessage.toMessage(netMessage));
	    		}
			
		}
	    

	@Override
	public void setConversationListenner(IConversationListenner listenner) {
	}

	@Override
	public void removeConversationListenner(IConversationListenner listenner) {
	}

	@Override
	public  synchronized void communicationMessageGained(CommunicationMessage communicationMessage) {
		CommunicationCommand command  = communicationMessage.getCommand();
		String id = communicationMessage.getId();
		String message = communicationMessage.getMessage();
		
		switch (command) {
		case RECEIVE_FAILURE :
			ConversationMessage conversationMessage = new ConversationMessage();
			conversationMessage.setCommand(ConversationCommand.NET_FAILUREE);
			conversationMessage.setFrom(id);
			conversationMessage.setMessage(message);
			this.listenner.conversationMessageGained(conversationMessage);
			this.communicationMap.remove(id);
			break;
		case RECEIVE_OK:
			 dealReceiveMessage(message);
			break;
		case SEND_FAILURE:
			ConversationMessage conversationMessage1 = new ConversationMessage();
			conversationMessage1.setCommand(ConversationCommand.NET_FAILUREE);
			conversationMessage1.setFrom(id);
			conversationMessage1.setMessage(message);
			this.listenner.conversationMessageGained(conversationMessage1);
			this.communicationMap.remove(id);
			break;
		default:
			break;
		}
	}

	private void dealReceiveMessage(String message) {
            ConversationMessage conversationMessage = new ConversationMessage();
            conversationMessage = (ConversationMessage) conversationMessage.toIConversationMessage(message);
            ConversationCommand command= conversationMessage.getCommand();
            String from = conversationMessage.getFrom();
            
            switch (command) {
			case NORMAL_MESSAGE:
				break;
			case OFF_LINE:
				ConversationMessage message2 = new ConversationMessage();
				message2.setCommand(ConversationCommand.OFF_LINE);
				Communication communication = communicationMap.get(from);
				communication.sendMessage(message2.toMessage(message2));
				message2.setFrom(from);
				communication.stopCommnuication();
				communicationMap.remove(from);
				message2.setMessage("正常下线！");
				this.listenner.conversationMessageGained(message2);
				break;
			case REQUEST:
				String actionName = conversationMessage.getAction();
				message = conversationMessage.getMessage();
				if(this.action == null) {
					return;
				}
				String result = action.executeAction(actionName, message);
				
                conversationMessage.setMessage(result);
                conversationMessage.setCommand(ConversationCommand.RESPONSE);
                Communication communicationResponse= communicationMap.get(from);
                communicationResponse.sendMessage(conversationMessage.toMessage(conversationMessage));
				break;
			case TO_ALL:
				transpondMessage(true,conversationMessage);
				break;
			case TO_ONE:
				transpondMessage(conversationMessage );
				break;
			case TO_OTHER:
				transpondMessage(false,conversationMessage);
				break;

			default:
				break;
			}
            
	}
     
}
