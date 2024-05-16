// Test.java
package com.test.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.test.game.graphics.WallParser;

public class Test extends Game {
    SpriteBatch batch;
    Texture img;
    Array<Object> objects; // Maintenant une liste d'objets
    float scrollSpeed = 200; // Vitesse de défilement
    ShapeRenderer shape;
    com.test.game.planes.Plane player;
    final int PLAYER_START_LINE_Y = 200;
    final int PLAYER_START_LINE_X = 0;

    private Array<Screen> screens = new Array<>();

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        shape = new ShapeRenderer();
        // Chargez les objets depuis le fichier texte
        objects = WallParser.parseWalls("text_art_zepplin_version.txt");
        player = new com.test.game.planes.Plane(PLAYER_START_LINE_X, PLAYER_START_LINE_Y, 80, 15);
        Screen mm = new MenuScreen(this);
        this.addScreen(mm);
        this.setScreen(mm);
    }

    @Override
    public void render() {
        super.render();
    }
    

    
    public void dispose() {
        batch.dispose();
        for (Screen s : screens) {
            s.dispose();
        }
    }
}
