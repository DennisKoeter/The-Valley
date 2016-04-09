package com.groeps33.valley.chat;

import com.groeps33.valley.entity.Character;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Dennis on 09/04/16.
 */
public class ChatTest {
    Character sender;
    ChatMessage m1;
    ChatMessage m2;
    ChatMessage m3;
    Chat chat;
    List<ChatMessage> messages;
    List<ChatMessage> expected;

    @Before
    public void setUp() throws Exception {
        sender = new Character(1, 1, "sender", 10, 10, 10, 10);
        m1 = new ChatMessage("message one", sender);
        m2 = new ChatMessage("message two", sender);
        m3 = new ChatMessage("message three", sender);

        chat = new Chat();
        chat.sendMessage("message one", sender);

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
        chat.sendMessage("message two", sender);
        expected.add(m2);
        assertEquals("lists are not equal", chat.getMessages(), expected);
    }
}