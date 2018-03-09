package com.mec.cs.client.interfaces;

public interface IClientViewPublisher {
      void  setListenner(IClientViewListenner listenner);
      void removeListenner(IClientViewListenner listenner);
}
