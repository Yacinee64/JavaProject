package com.test.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    final Test game;
    OrthographicCamera camera;
    final int VIEW_PORT_WIDTH = 800;
    final int VIEW_PORT_HEIGHT = 480;
    public BitmapFont test;
    public BitmapFont Tfont;
    private Texture BackgroundTexture;
    final int OFFSET_TITLE = 200;
    Button ButtonPlay;
    Sound sonMenu;
    Sound sonJeu;

    public MenuScreen(final Test game) {
        this.game = game;
        this.test = new BitmapFont();
        this.Tfont = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEW_PORT_WIDTH, VIEW_PORT_HEIGHT);
        BackgroundTexture = new Texture("red-wings.jpg");
        sonMenu = Gdx.audio.newSound(Gdx.files.internal("Unlimited-Blade-Works.mp3"));// on utilise files.internal
                                                                                      // parceque la fonction ne prend
                                                                                      // pas de chaine en parametre
        sonMenu.play();
        sonJeu = Gdx.audio.newSound(Gdx.files.internal("Red-B.mp3"));
    }

    public void draw(SpriteBatch batch) {
        // Dessinez le mur à l'écran en utilisant les coordonnées du rectangle et la
        // texture
        batch.draw(BackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        // game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        draw(game.batch);
        Tfont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        Tfont.getData().setScale(3);
        Tfont.setColor(Color.RED);
        Tfont.draw(game.batch, "Time To fly Red Baron ", (Gdx.graphics.getWidth() / 2) - OFFSET_TITLE,
                Gdx.graphics.getHeight() - 10);
        // BackgroundTexture = new Texture("badlogic.jpg");
        // test.draw(game.batch, "YESSSS", 300, 300);
        // game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        if (Gdx.input.isTouched()) {

            game.setScreen(new GameScreen(game));
            dispose();
            sonMenu.dispose();
            sonJeu.play();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
