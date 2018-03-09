package com.mec.cs.common;

public class CommunicationMessage {
	/*
	 * 该类是通信服务模型的最底层传输的的消息模型，通过判断command的不同
	 * 可以判断是否接受或发送成功与否
	 */
	
       private CommunicationCommand command;    //主要判断的类型
       private String message;   //信道中传输的消息
       private String id;  //客户端在服务器那边的Id
       
       public CommunicationMessage() {
    	}

		public CommunicationCommand getCommand() {
			return command;
		}
	
		public CommunicationMessage setCommand(CommunicationCommand  command) {
			this.command =command;
			return this;
		}
	
		public String getMessage() {
			return message;
		}
	
		public CommunicationMessage setMessage(String message) {
			this.message = message;
			return this;
		}
	
		public String getId() {
			return id;
		}
	
		public CommunicationMessage setId(String id) {
			this.id = id;
			return this;
		}
       
       
       
}
