package com.mec.cs.server.interfaces;

public interface IConversationPublisher {
	void setConversationListenner(IConversationListenner listenner);
    void removeConversationListenner(IConversationListenner listenner);
}
