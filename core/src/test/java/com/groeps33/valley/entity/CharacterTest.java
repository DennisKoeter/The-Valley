package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    }

    @Test
    public void getPlayerClass() throws Exception {

    }

    @Test
    public void getGold() throws Exception {

    }

    @Test
    public void reduceGold() throws Exception {

    }

    @Test
    public void addProjectile() throws Exception {

    }

    @Test
    public void canAttack() throws Exception {

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

    }

    @Test
    public void draw() throws Exception {

    }

    @Test
    public void getBounds() throws Exception {

    }

    @Test
    public void getLocation() throws Exception {

    }

    @Test
    public void getCharacterAnimator() throws Exception {

    }

}