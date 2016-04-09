package com.groeps33.valley.chat;

import com.groeps33.valley.entity.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class ChatMessageTest {
    Character sender;

    @Before
    public void setUp() throws Exception {
        sender = new Character(1, 1, "sender", 10, 10, 10, 10);
    }

    @Test
    public void testConstructor() throws Exception {
        ChatMessage test = new ChatMessage("test", sender);
        assertNotNull("object is null", test);
    }
}