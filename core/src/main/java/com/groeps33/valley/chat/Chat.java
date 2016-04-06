package com.groeps33.valley.chat;

import com.groeps33.valley.entity.Character;

import java.util.List;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class Chat {
    private List<ChatMessage> messages;

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    /**
     * Creates a ChatMessage and adds it to this class' list of messages
     * @param textMessage the content of the chatmessage
     * @param sender the Character object of the sender
     */
    public void sendMessage(String textMessage, Character sender) {

    }
}
