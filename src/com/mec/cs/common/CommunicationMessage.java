package com.mec.cs.common;

public class CommunicationMessage {
	/*
	 * ������ͨ�ŷ���ģ�͵���ײ㴫��ĵ���Ϣģ�ͣ�ͨ���ж�command�Ĳ�ͬ
	 * �����ж��Ƿ���ܻ��ͳɹ����
	 */
	
       private CommunicationCommand command;    //��Ҫ�жϵ�����
       private String message;   //�ŵ��д������Ϣ
       private String id;  //�ͻ����ڷ������Ǳߵ�Id
       
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
