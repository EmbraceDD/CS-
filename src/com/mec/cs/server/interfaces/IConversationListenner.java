package com.mec.cs.server.interfaces;

import com.mec.cs.common.ConversationMessage;

public interface IConversationListenner {
   void  conversationMessageGained(ConversationMessage conversationMessage);
}
