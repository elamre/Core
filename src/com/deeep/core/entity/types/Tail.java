package com.deeep.core.entity.types;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.core.entity.abstraction.Entity;
import com.deeep.core.graphics.Assets;
import com.deeep.core.network.mutual.packets.Packet;
import com.deeep.core.network.mutual.packets.TailSpecificPacket;
import com.deeep.core.system.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 11/8/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tail extends Entity {
    private TextureRegion textureRegion;
    private boolean changed = false;
    private boolean corner = false;
    private boolean end = false;

    public Tail() {
        super();
    }

    public Tail(int x, int y) {
        super();
        setPosition(x, y);
    }

    @Override
    protected void loadAssets() {
        textureRegion = Assets.getAssets().getRegion("snakes/snake_1_tail");
    }

    public void setTailSpecific(TailSpecificPacket tailSpecific) {
        if (tailSpecific.end) {
            textureRegion = Assets.getAssets().getRegion("snakes/snake_1_tail");
            end = tailSpecific.end;
        } else if (tailSpecific.corner) {
            textureRegion = Assets.getAssets().getRegion("snakes/snake_1_body_angled");
            corner = tailSpecific.corner;
        } else {
            textureRegion = Assets.getAssets().getRegion("snakes/snake_1_body");
        }
        setDirection(tailSpecific.direction);
    }

    @Override
    public void implementUpdate(float deltaT) {

    }

    /**
     * Some entity Specifics. Override this method if the entity has any specific data to send on change
     *
     * @return
     */
    @Override
    public Packet getSpecifics() {
        TailSpecificPacket tailSpecificPacket = new TailSpecificPacket();
        tailSpecificPacket.corner = corner;
        tailSpecificPacket.end = end;
        tailSpecificPacket.direction = direction.getValue();
        changed = false;
        return tailSpecificPacket;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(textureRegion, x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE);
        spriteBatch.end();
    }

    public void setEnd(boolean end){
        this.end = end;
        change();
    }

    public void setCorner(boolean corner){
        this.corner = corner;
    }
}
