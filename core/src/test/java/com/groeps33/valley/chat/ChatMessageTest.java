package com.groeps33.valley.chat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class ChatMessageTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConstructor() throws Exception {
        //Todo add proper character instead of null
        ChatMessage test = new ChatMessage("test", null);
        assertNotNull("object is null", test);
    }
}