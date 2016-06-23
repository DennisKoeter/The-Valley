package com.groeps33.valley.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6-4-2016.
 */
public class Character extends Entity {
    private final PlayerClass playerClass;
    private long lastAtkTime;
    private long lastSpecialAtkTime;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(byte ordinal) {
        setDirection(Direction.values()[ordinal]);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void resetHp() {
        currentHp = maxHp;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public enum Direction {SOUTH, WEST, EAST, NORTH}

    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private int bonusAttackSpeed;
    private int bonusMoveSpeed;
    private int bonusHp;
    private int bonusDefence;
    private int bonusAttackDamage;
    private int gold;
    private int currentMana;
    private int maxMana;
    private boolean specialActive;

    private float manaCounter;

    private Animation animation;
    private Texture spriteSheet;
    private TextureRegion currentFrame;
    private TextureRegion[][] frames;
    private float frameTime;
    private Direction direction;
    private final List<Projectile> projectiles;
    private Texture projectileTexture;

    private BitmapFont font;

    public Character(float x, float y, String name, PlayerClass playerClass) {
        super(x, y, name, 100, playerClass.getDefence(), playerClass.getAttackDmg(), playerClass.getMoveSpeed(), playerClass.getAttackInterval());
        this.playerClass = playerClass;
        this.maxMana = 100;
        this.currentMana = this.maxMana;
        direction = Direction.SOUTH;
        projectiles = new ArrayList<>();
    }

    public int getGold() {
        return this.gold;
    }

    public void reduceGold(int amount) {
        this.gold -= amount;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public boolean canAttack() {
        boolean canAttack = System.currentTimeMillis() - lastAtkTime > attackSpeedInterval;
        if (canAttack) {
            lastAtkTime = System.currentTimeMillis();
        }
        return canAttack;
    }

    private void setDirection(Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
            frameTime = 0f;
            animation = new Animation(0.10f, frames[direction.ordinal()]);
        }
    }

    public void updateFrame() {
        if (frames != null) {
            frameTime += Gdx.graphics.getDeltaTime();
            currentFrame = animation.getKeyFrame(frameTime, true);

            if (currentFrame.isFlipY())
                currentFrame.flip(false, true);
        }
    }

    private void init() {
        spriteSheet = new Texture(Gdx.files.internal(playerClass.getSpriteSheet()));
        frames = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 3, spriteSheet.getHeight() / 4);
        animation = new Animation(0.10f, frames[0][0]);
        frameTime = 0;
        currentFrame = animation.getKeyFrame(frameTime);
        font = new BitmapFont();

        //temp texture for projectile
//        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pixmap.setColor(new Color(0, 0f, 1f, 1f));
//        pixmap.fill();
//        this.projectileTexture = new Texture(pixmap);
        this.projectileTexture = new Texture(playerClass.getProjectileSpriteSheet());
    }

    @Override
    public void update(float deltaTime) {

        if(manaCounter < 1) manaCounter += deltaTime * 0.2f;

        if(currentMana < maxMana && manaCounter>=1){
            currentMana+=1;
            manaCounter=0;
        }

        if(specialActive && (lastSpecialAtkTime >= 2000)){
            specialActive = false;
            this.moveSpeed -= 200;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && System.currentTimeMillis() - lastSpecialAtkTime > 500){
            switch(playerClass){
                case WARRIOR:
                    if(this.currentMana >= 30 && !specialActive){
                        specialActive = true;
                        this.moveSpeed += 200;
                        this.currentMana += 30;
                        lastSpecialAtkTime = System.currentTimeMillis();
                    }
                    break;
                case MAGE:
                    if(this.currentMana >= 40) {
                        this.currentHp += 20;
                        this.currentMana -= 40;
                        lastSpecialAtkTime = System.currentTimeMillis();
                    }
                    break;
            }
        }

        if (frames == null) {
            init();
        }

        velocity.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = deltaTime * -moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = deltaTime * moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y = deltaTime * moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y = deltaTime * -moveSpeed;
        }

        if (velocity.y != 0) {
            if (velocity.y > 0) {
                setDirection(Direction.NORTH);
            } else {
                setDirection(Direction.SOUTH);
            }
        } else if (velocity.x > 0) {
            setDirection(Direction.EAST);
        } else {
            setDirection(Direction.WEST);
        }

        if (velocity.x != 0 || velocity.y != 0) {
            frameTime += deltaTime;
            currentFrame = animation.getKeyFrame(frameTime, true);

            if (currentFrame.isFlipY())
                currentFrame.flip(false, true);

            move();
        }
    }

    public Projectile spawnProjectile() {
        Vector2 velocity = new Vector2();//nextY - location.y, nextX - location.x
        double angle = Math.atan2(Gdx.input.getX() - (Gdx.graphics.getWidth() / 2), Gdx.input.getY() - (Gdx.graphics.getHeight() / 2));
        angle -= Math.PI / 2.0;
        velocity.set(playerClass.getProjectileSpeed() * (float) Math.cos(angle), playerClass.getProjectileSpeed() * (float) Math.sin(angle));
        Projectile projectile = new Projectile(angle, new Vector2(location.x + WIDTH / 2, location.y + HEIGHT / 2), velocity, attackDamage + bonusAttackDamage, playerClass.getAttackRange());
        projectiles.add(projectile);
        return projectile;
    }

    @Override
    public void onCollisionWithObject(MapObject object) {
        velocity.set(-velocity.x, -velocity.y);
        move();
    }

    @Override
    public void draw(Batch batch) {
        if (frames == null) {
            init();
        }

        batch.draw(currentFrame, location.x, location.y);
        font.draw(batch, name, location.x, location.y + 50);

        //projectiles
        synchronized (projectiles) {
            for (Projectile projectile : projectiles) {
                //todo rotate projectiles
                batch.draw(projectileTexture, projectile.getLocation().x - playerClass.getProjectileSize()/2, projectile.getLocation().y - playerClass.getProjectileSize()/2 ,
                        playerClass.getProjectileSize(), playerClass.getProjectileSize(), 0, 0, projectileTexture.getWidth(), projectileTexture.getHeight(), false, false);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, WIDTH, HEIGHT);
    }

    public Vector2 getLocation() {
        return location;
    }

    public Animation getCharacterAnimator() {
        return animation = new Animation(0.10f, frames[0]);
    }

    public int getCurrentMana(){
        return currentMana;
    }

    public int getMaxMana(){
        return maxMana;
    }
}
