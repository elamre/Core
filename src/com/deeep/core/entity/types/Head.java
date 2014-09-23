package com.deeep.core.entity.types;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.core.entity.abstraction.Entity;
import com.deeep.core.graphics.Assets;
import com.deeep.core.system.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 11/8/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Head extends Entity {
    private TextureRegion textureRegion;
    @Override
    protected void loadAssets() {
        textureRegion = Assets.getAssets().getRegion("snakes/snake_1_head");
    }

    @Override
    public void implementUpdate(float deltaT) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(textureRegion, x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE);
        spriteBatch.end();
    }
}
