package com.mec.cs.common;

public interface ICommunicationPublisher {
    void setCommnictaionListenner(ICommunicationListenner listenner);
    void removeCommnictaionListenner(ICommunicationListenner listenner);
}
