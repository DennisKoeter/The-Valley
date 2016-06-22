package com.groeps33.valley.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Queue;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Bram on 6/21/2016.
 *
 * @author Bram Hoendervangers
 */
public class HudRenderer {
    private static final int HP_BAR_WIDTH = 150;
    private static final int HP_BAR_HEIGHT = 20;

    private static final Texture HUD_BACKGROUND_TEXTURE = new Texture(createPixmap(0f, 0f, 0f,0.7f));
    private static final Texture HP_BAR_TEXTURE = new Texture(createPixmap(1f, 0f, 0f, 1f));
    private static final Texture HP_BAR_BACKGROUND_TEXTURE = new Texture(createPixmap(0f, 0f, 0f,1f));

    private final GameScreen gameScreen;
    private final BitmapFont font = new BitmapFont();
    private final SpriteBatch spriteBatch;
    private final List<Message> messages = new ArrayList<>();

    public HudRenderer(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.spriteBatch = new SpriteBatch();
    }

    //float ratio = width/health;
    //ratio * health
    public void render() {
        spriteBatch.begin();

        Character localPlayer = gameScreen.getLocalPlayer();
        if (localPlayer != null) {
            //background
            spriteBatch.draw(HUD_BACKGROUND_TEXTURE, 0, Gdx.graphics.getHeight() - 60, 200, 60);

            //health bar
            int hpBarX = 40;
            int hpBarY = Gdx.graphics.getHeight() - 50;
            float healthWidth = (float) HP_BAR_WIDTH / (float) localPlayer.getMaxHp();
            spriteBatch.draw(HP_BAR_BACKGROUND_TEXTURE, hpBarX, hpBarY, HP_BAR_WIDTH, HP_BAR_HEIGHT);
            spriteBatch.draw(HP_BAR_TEXTURE, hpBarX, hpBarY, healthWidth * localPlayer.getCurrentHp(), HP_BAR_HEIGHT);
            font.draw(spriteBatch, "HP: ", hpBarX - 30, hpBarY + 15);

            //player name
            String loc = "(" + (int) localPlayer.getLocation().x + ", " + (int) localPlayer.getLocation().y + ")";
            font.draw(spriteBatch, "Player: " + localPlayer.getName() + "  " + loc, 10, Gdx.graphics.getHeight() - 10);
        }

        //draw messages
//        spriteBatch.draw(HUD_BACKGROUND_TEXTURE, 0, 50, 200, 200);

        int x = 10;
        int y = 100;

        Iterator<Message> it = messages.iterator();
        while (it.hasNext()) {
            Message message = it.next();
            if (!message.isValid()) {
                it.remove();
            } else {
                font.setColor(message.getType().getColor());
                font.draw(spriteBatch, message.getMessage(), x, y);
                y+=15;
            }
        }

        font.setColor(Color.WHITE);

        //loc
//        font.draw(spriteBatch, ", 10, Gdx.graphics.getHeight() - 10);
        spriteBatch.end();
    }

    private static Pixmap createPixmap(float r, float g, float b, float a) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(r, g, b, a);
        pixmap.fill();

        return pixmap;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
