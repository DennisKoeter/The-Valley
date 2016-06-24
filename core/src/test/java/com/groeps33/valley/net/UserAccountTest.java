package com.groeps33.valley.net;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Edwin
 */
public class UserAccountTest {
    private UserAccount user;
    @Before
    public void setUp() throws Exception {
    user = new UserAccount("moob", "test@test.nl");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getUsername() throws Exception {
        assertEquals("moob", user.getUsername());
    }

    @Test
    public void getEmail() throws Exception {
    assertEquals("test@test.nl", user.getEmail());
    }

    @Test
    public void equals() throws Exception {

    }

}