package com.groeps33.valley.lobby;

import com.groeps33.valley.entity.Character;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class LobbyTest {
    Character creator;
    Character player1;
    Character player2;
    List<Character> expected;
    int id = 1;
    String name = "lobby";
    int playerAmount = 4;
    String password = "hunter2";
    Lobby lobby;

    @Before
    public void setUp() throws Exception {
        creator = new Character(1, 1, "creator", 10, 10, 10, 10);
        player1 = new Character(1, 1, "player1", 10, 10, 10, 10);
        player2 = new Character(1, 1, "player2", 10, 10, 10, 10);

        expected = new ArrayList<>();
        expected.add(player1);

        lobby = new Lobby(id, name, playerAmount, password, creator);
        lobby.addCharacter(player1, password);
    }

    @Test
    public void testConstructor() throws Exception {
        Lobby test = new Lobby(id, name, playerAmount, password, creator);
        assertNotNull("object is null", test);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("id's are not equal", lobby.getId(), id);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("names are not equal", lobby.getName(), name);
    }

    @Test
    public void testGetPlayerAmount() throws Exception {
        assertEquals("playeramounts are not equal", lobby.getPlayerAmount(), playerAmount);
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals("passwords are not equal", lobby.getPassword(), password);
    }

    @Test
    public void testGetCreator() throws Exception {
        assertEquals("creators are not equal", lobby.getCreator(), creator);
    }

    @Test
    public void testGetCharacters() throws Exception {
        assertEquals("lists are not equal", lobby.getCharacters(), expected);
    }

    @Test
    public void testAddCharacterRightPassword() throws Exception {
        assertTrue("character was not added", lobby.addCharacter(player2, "hunter2"));
        expected.add(player2);
        assertEquals("lists were not equal", lobby.getCharacters(), expected);
    }

    @Test
    public void testAddCharacterWrongPassword() throws Exception {
        assertTrue("character was incorrectly added", lobby.addCharacter(player2, "hunter1"));
        assertEquals("lists were not equal", lobby.getCharacters(), expected);
    }
}