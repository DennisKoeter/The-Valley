package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Edwin
 */
public class EntityTest {
    private Character character;
    private final float X = 10;
    private final float Y = 10;
    private final String NAME = "harrie";
    private PlayerClass PLAYERCLASS = PlayerClass.ARCHER;
    private Vector2 location;
    private Projectile projectile;

    private final double ANGLE = 10;
    private final Vector2 LOCATION = new Vector2(X, Y);
    private final Vector2 VELOCITY = new Vector2(X, Y);
    private static final int DAMAGE = 25;
    private static final int ATTACKRANGE = 400;
    @Before
    public void setUp() throws Exception {

        character = new Character(X, Y, NAME, PLAYERCLASS);
        projectile = new Projectile(ANGLE, LOCATION, VELOCITY, DAMAGE, ATTACKRANGE);
        Vector2 location = new Vector2(10,10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void draw() throws Exception {

    }

    @Test
    public void getBounds() throws Exception {
        Vector2 locationTest;
        locationTest = new Vector2(10,10);
        int WIDTH = 32;
        int HEIGHT = 32;
        character.setLocation(10,10);
        Rectangle rectest = new Rectangle(locationTest.x, locationTest.y, WIDTH, HEIGHT);
        assertEquals("is hetzelfde", character.getBounds(), rectest );
    }

    @Test
    public void onCollisionWithObject() throws Exception {

    }

    @Test
    public void move() throws Exception {

    }

    @Test
    public void getLocation() throws Exception {
        Vector2 vec = new Vector2(10,10);
        assertEquals("get location", vec, character.getLocation());
    }

    @Test
    public void setLocation() throws Exception {
    character.setLocation(20,20);
        Vector2 vec = new Vector2(20,20);
        assertEquals(vec, character.getLocation());
    }

    @Test
    public void setLocation1() throws Exception {
       Vector2 veccharacter = new Vector2(20,20);
        character.setLocation(veccharacter);
        Vector2 vectest = new Vector2(20,20);
        assertEquals(vectest, character.getLocation());
    }

    @Test
    public void getGridLocation() throws Exception {
        int gridx = Math.round(location.x / 32f);
        int gridy = Math.round(location.y / 32f);
        Vector2 vec = new Vector2(gridx, gridy);

        assertEquals(vec, character.getGridLocation());
    }

    @Test
    public void getName() throws Exception {
    assertEquals("harrie", character.getName());
    }

    @Test
    public void getMaxHp() throws Exception {
    assertEquals(100, character.getMaxHp());
    }

    @Test
    public void getCurrentHp() throws Exception {
    assertEquals(100, character.getCurrentHp());
    }

    @Test
    public void damageWarrior() throws Exception {
        character = new Character(X, Y, NAME, PlayerClass.WARRIOR);
        character.damage(11);
        assertEquals(97, character.getCurrentHp());
    }
@Test
    public void damageMage() throws Exception {
    character = new Character(X, Y, NAME, PlayerClass.MAGE);
    character.damage(11);
    assertEquals(89, character.getCurrentHp());
    }
@Test
    public void damageArcher() throws Exception {
    character = new Character(X, Y, NAME, PlayerClass.ARCHER);
    character.damage(11);
    assertEquals(89, character.getCurrentHp());
    }

}