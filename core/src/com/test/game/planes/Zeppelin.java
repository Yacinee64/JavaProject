package com.test.game.planes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Zeppelin {
    private static final String TEXTURE_FILE_NAME = "zeppelin.png";
    private static final Texture texture = new Texture(Gdx.files.internal(TEXTURE_FILE_NAME));
    private final Rectangle shape;

    public Zeppelin(int x, int y){
        shape = new Rectangle(x, y, 70, 50);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, shape.x, shape.y, shape.width, shape.height);
    }

    public Rectangle getPosition() {
        return shape;
    }

    public void setPosition(float x, float y) {
        shape.setPosition(x, y);
    }
}
