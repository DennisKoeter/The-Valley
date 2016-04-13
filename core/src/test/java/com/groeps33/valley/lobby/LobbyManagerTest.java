package com.groeps33.valley.lobby;

import org.junit.Before;
import org.junit.Test;
import com.groeps33.valley.entity.Character;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class LobbyManagerTest {
    private Character creator;
    private LobbyManager lobbyManager;
    private List<Lobby> expected;

    @Before
    public void setUp() throws Exception {
        creator = new Character(1, 1, "creator", 10, 10, 10, 10);

        lobbyManager = new LobbyManager();
        lobbyManager.createLobby(1, "lobby1", 4, "hunter2", creator);

        expected = new ArrayList<Lobby>();
        expected.add(new Lobby(1, "lobby1", 4, "hunter2", creator));
    }

    @Test
    public void testConstructor() throws Exception {
        LobbyManager test = new LobbyManager();
        assertNotNull("object is null", test);
    }

    @Test
    public void testCreateLobby() throws Exception {
        Lobby expectedLobby = new Lobby(2, "lobby2", 4, "hunter2", creator);
        Lobby result = lobbyManager.createLobby(2, "lobby2", 4, "hunter2", creator);
        expected.add(expectedLobby);

        assertEquals("lobbies are not equal", result, expectedLobby);
        assertEquals("lists are not equal", lobbyManager.getLobbies(), expected);
    }

    @Test
    public void testGetLobbies() throws Exception {
        assertEquals("lists are not equal", lobbyManager.getLobbies(), expected);
    }

    @Test
    public void testFindLobby() throws Exception {
        Lobby expectedLobby = new Lobby(1, "lobby1", 4, "hunter2", creator);
        Lobby result = lobbyManager.findLobby("lobby1");
        assertEquals("lobbies are not equal", expectedLobby, result);
    }

    @Test
    public void testCloseLobby() throws Exception {
        lobbyManager.closeLobby("lobby1");
        expected.remove(0);
        assertEquals("lobbies are not equal", lobbyManager.getLobbies(), expected);
    }
}