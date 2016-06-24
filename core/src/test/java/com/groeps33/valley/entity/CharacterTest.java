package com.groeps33.valley.entity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Edwin
 */
public class CharacterTest {

    private Character character;
    private final float X = 10;
    private final float Y = 10;
    private final String NAME = "Naam";
    private PlayerClass PLAYERCLASS = PlayerClass.ARCHER;

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
    }

    @After
    public void tearDown() throws Exception {
        character = null;
    }

    @Test
    public void getDirection() throws Exception {
        /**
         * Move the character to south
         */
        character.setDirection((byte) 0);
        assertEquals(character.getDirection(), Character.Direction.SOUTH);

        /**
         * Move the character to west
         */
        character.setDirection((byte) 1);
        assertEquals(character.getDirection(), Character.Direction.WEST);

        /**
         * Move the character to east
         */
        character.setDirection((byte) 2);
        assertEquals(character.getDirection(), Character.Direction.EAST);

        /**
         * Move the character to north
         */
        character.setDirection((byte) 3);
        assertEquals(character.getDirection(), Character.Direction.NORTH);
    }

    @Test
    public void setDirection() throws Exception {
        /**
         * Move the character to south
         */
        character.setDirection((byte) 0);
        assertEquals(character.getDirection(), Character.Direction.SOUTH);

        /**
         * Move the character to west
         */
        character.setDirection((byte) 1);
        assertEquals(character.getDirection(), Character.Direction.WEST);

        /**
         * Move the character to east
         */
        character.setDirection((byte) 2);
        assertEquals(character.getDirection(), Character.Direction.EAST);

        /**
         * Move the character to north
         */
        character.setDirection((byte) 3);
        assertEquals(character.getDirection(), Character.Direction.NORTH);
    }

    @Test
    public void getProjectiles() throws Exception {
        character.addProjectile(projectile);
        assertEquals("Projectiles did not match", projectile, character.getProjectiles().get(0));
    }

    @Test
    public void resetHp() throws Exception {
    int hp = character.getCurrentHp();
        character.resetHp();
        assertEquals("hp is at maximum", character.getCurrentHp(), character.maxHp);

    }

    @Test
    public void getPlayerClass() throws Exception {
    assertEquals("character is een archer", PLAYERCLASS.ARCHER, character.getPlayerClass());

    }

    @Test
    public void getGold() throws Exception {
    assertEquals("moet 0 gold hebben met een nieuw character", character.getGold(), 0);
    }

    @Test
    public void reduceGold() throws Exception {
        character.reduceGold(10);
    assertEquals("gold is gereduced", character.getGold(), -10);
    }

    @Test
    public void addProjectile() throws Exception {
    character.addProjectile(projectile);
        assertEquals("projectile added", character.getProjectiles().size(), 1);
    }

    @Test
    public void canNotAttack() throws Exception {
    character.attackSpeedInterval =  Long.MAX_VALUE;
        assertFalse("kan niet aanvallen", character.canAttack());

    }

    public void canAttack() throws Exception {
        character.attackSpeedInterval = 1;
        assertFalse("kan aanvallen", character.canAttack());

    }

    @Test
    public void updateFrame() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void spawnProjectile() throws Exception {

    }

    @Test
    public void onCollisionWithObject() throws Exception {
        MapObject obj = new MapObject();

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
    public void getLocation() throws Exception {
        character.setLocation(10,10);
        Vector2 vec = new Vector2(10,10);
        assertEquals(vec, character.getLocation());
    }

    @Test
    public void getCharacterAnimator() throws Exception {

    }

}