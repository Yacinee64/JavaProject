
package com.test.game.planes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.test.game.graphics.Wall;

public class Plane {
    int x0; // position du plan x
    int y0; // position sur le plan y
    int width; // largeur
    int height; // hauteur
    private Texture texture;
    Color color = Color.WHITE;

    public Plane(int a, int b, int c, int d) {
        this.x0 = a;
        this.y0 = b;
        this.width = c;
        this.height = d;
        this.texture = new Texture(Gdx.files.internal("red_baron.png"));
    }

    // Utilisez SpriteBatch pour dessiner la texture
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x0, y0, width, height);
    }

    public void update() {
        // Mise à jour de la position
        this.x0 = Gdx.input.getX() - (width / 2);
        this.y0 = (Gdx.graphics.getHeight() - Gdx.input.getY()) - (height / 2);
        // Reste du code pour empêcher de sortir de l'écran...
        if (Gdx.graphics.getWidth() < Gdx.input.getX() + (width / 2)) {
            // System.out.println("syop");
            this.x0 = Gdx.graphics.getWidth() - (width);
        }
        if (Gdx.input.getX() - (width / 2) < 0) {
            // System.out.println("ye");
            this.x0 = 0;
        }
        // pour ne pas sortir sur l'écran sur l axe des y
        System.out.println(Gdx.graphics.getHeight() - Gdx.input.getY());
        if ((Gdx.graphics.getHeight() - Gdx.input.getY()) < height / 2) {
            this.y0 = 0;
        }
        if ((Gdx.graphics.getHeight() - Gdx.input.getY()) + (height / 2) > Gdx.graphics.getHeight()) {
            this.y0 = Gdx.graphics.getHeight() - height;
        }
    }

    public boolean collidesWith(Wall wall) {
        if ((x0 + width >= wall.bounds.x) && (x0 <= (wall.bounds.x + wall.getWidth())) && (y0 + height >= wall.bounds.y)
                && (y0 <= (wall.bounds.y + wall.getHeight()))) {
            return true;
        }
        return false;
    }

    public void checkCollision(Wall wall) {
        if (collidesWith(wall)) {
            color = Color.RED;
            System.out.println("toucheew");
        } else {
            color = Color.WHITE;
        }
    }
}
