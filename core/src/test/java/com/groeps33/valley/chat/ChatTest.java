package com.groeps33.valley.chat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class ChatTest {
    ChatMessage m1;
    ChatMessage m2;
    ChatMessage m3;
    Chat chat;
    List<ChatMessage> messages;
    List<ChatMessage> expected;

    @Before
    public void setUp() throws Exception {
        //Todo add proper character instead of null
        m1 = new ChatMessage("message one", null);
        m2 = new ChatMessage("message two", null);
        m3 = new ChatMessage("message three", null);

        chat = new Chat();
        chat.sendMessage("message one", null);

        messages = new ArrayList<ChatMessage>();
        messages.add(m2);

        expected = new ArrayList<ChatMessage>();
        expected.add(m1);
    }

    @Test
    public void testConstructor() throws Exception {
        Chat test = new Chat();
        assertNotNull("object is null", test);
    }

    @Test
    public void testGetMessages() throws Exception {
        assertEquals("lists are not equal", chat.getMessages(), expected);
    }

    @Test
    public void testSetMessages() throws Exception {
        chat.setMessages(messages);
        expected = messages;
        assertEquals("lists are not equal", chat.getMessages(), expected);
    }

    @Test
    public void testSendMessage() throws Exception {
        //Todo add proper character instead of null
        chat.sendMessage("message two", null);
        expected.add(m2);
        assertEquals("lists are not equal", chat.getMessages(), expected);
    }
}