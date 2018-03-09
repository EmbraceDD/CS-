package com.mec.cs.common;

public interface IConversationMessage {	
	 IConversationMessage setFrom(String from);
	 IConversationMessage setTo(String to);
	 IConversationMessage setCommand(ConversationCommand command);
	 IConversationMessage setMessage(String message);
	 IConversationMessage setAction(String action);
	 
	 String getFrom();
	 String getTo();
	 ConversationCommand getCommand(); 
	 String getMessage();
	 String getAction();
	 
	 IConversationMessage toIConversationMessage(String message);
	 String toMessage(IConversationMessage conversationMessage);

}
