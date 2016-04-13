package com.groeps33.valley.chat;

import com.groeps33.valley.entity.Character;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
class ChatMessage {
    private String textMessage;
    private LocalDateTime sendTime;
    private Character sender;

    /**
     * Creates an instance of chatmessage, sendTime will be calculated by itself
     * @param textMessage content of the message
     * @param sender Character object of the sender
     */
    public ChatMessage(String textMessage, Character sender) {
        this.textMessage = textMessage;
        sendTime = LocalDateTime.now();
        this.sender = sender;
    }
}
