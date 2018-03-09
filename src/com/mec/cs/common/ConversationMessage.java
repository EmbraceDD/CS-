package com.mec.cs.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;

public class ConversationMessage implements IConversationMessage {
      private String from;
      private String to;
      private ConversationCommand command;
      private String message;
      private String action;
      

	public ConversationMessage() {
	}

	public String getFrom() {
		return from;
	}

	public ConversationMessage setFrom(String from) {
		this.from = from;
		return this;
	}

	public String getTo() {
		return to;
	}

	public ConversationMessage setTo(String to) {
		this.to = to;
		return this;
	}

	public ConversationCommand getCommand() {
		return command;
	}

	public ConversationMessage setCommand(ConversationCommand command) {
		this.command = command;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ConversationMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getAction() {
		return action;
	}

	public ConversationMessage setAction(String action) {
		this.action = action;
		return this;
	}

	@Override
	public IConversationMessage toIConversationMessage(String message) {

		ConversationMessage conversationMessage = new ConversationMessage();
		Class<?> klass = conversationMessage.getClass();
		String [] strArr = message.split("[?]");
		setDefaultValue(strArr[0],conversationMessage);
		String [] variable = strArr[1].split("&");
		
		for(int i =0 ; i< variable.length;i++) {
			String str = variable[i];
			String[]  arr = str.split("=");
			String methodName = "" ;
			Method method  ;
			try {
				arr[0] = arr[0].trim();
				methodName= "set"+arr[0].substring(0, 1).toUpperCase() +arr[0].substring(1);
				method  = klass.getMethod(methodName, String.class);
				method.invoke(conversationMessage, arr[1]);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
 		}
		return conversationMessage;
	}

	@Override
	public String toMessage(IConversationMessage conversationMessage) {
		
		StringBuffer str = new StringBuffer();
		str.append(conversationMessage.getFrom());
		str.append(":");
		str.append(conversationMessage.getTo());
		str.append("//");
		str.append(conversationMessage.getCommand());
		str.append(":");
		str.append(conversationMessage.getAction());
		str.append("?");
		str.append("message=");
		str.append(conversationMessage.getMessage());
		
		return str.toString() ;
	}
	
	private void setDefaultValue(String value, ConversationMessage conversationMessage){
		String [] var = value.split("//");
		String before[] = var[0].split(":");
		String after[] = var[1].split(":");
        conversationMessage.setFrom(before[0]);
        conversationMessage.setTo(before[1]);
        conversationMessage.setCommand( Enum.valueOf(ConversationCommand.class, after[0]));
        conversationMessage.setAction(after[1]);
	}
	
}
