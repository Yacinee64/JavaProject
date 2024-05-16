package com.test.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.game.graphics.Wall;
import com.test.game.graphics.WallParser;
import com.test.game.planes.Zeppelin;

public class GameScreen implements Screen {
    // Écran
    private final OrthographicCamera camera;
    private Viewport viewport;

    // Graphiques
    private SpriteBatch batch;
    private Texture background;

    // Timings
    private int backgroundOffset;

    // Paramètres du monde
    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 480;

    private final int VIEW_PORT_WIDTH = 800;
    private final int VIEW_PORT_HEIGHT = 480;
    private final Test game;
    
    Array<Object> objects; // Liste d'objets
    float scrollSpeed = 200; // Vitesse de défilement
    ShapeRenderer shape;
   
    com.test.game.planes.Plane player;
    final int PLAYER_START_LINE_Y = 200;
    final int PLAYER_START_LINE_X = 0;

    // Déclaration de backgroundOffsetX
    private float backgroundOffsetX;

    public GameScreen(final Test game) {
        this.game = game;
        this.game.addScreen(this);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        background = new Texture("background.jpg");
        backgroundOffset = 0;
        backgroundOffsetX = 0; // Initialisation de backgroundOffsetX

        camera.setToOrtho(false, VIEW_PORT_WIDTH, VIEW_PORT_HEIGHT);
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        // Charger les murs depuis le fichier texte
        objects = WallParser.parseWalls("text_art_zepplin_version.txt");
        player = new com.test.game.planes.Plane(PLAYER_START_LINE_X, PLAYER_START_LINE_Y, 100, 80);
    }

    public void MapGeneration() {
        float delta = Gdx.graphics.getDeltaTime();

        // Effacer l'écran
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Commencer à dessiner avec ShapeRenderer
        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Mettre à jour et dessiner le joueur
        player.update();
        player.draw(batch);

        // Fin de ShapeRenderer
        batch.end();

        // Commencer à dessiner avec SpriteBatch
        batch.begin();

        // Calculer la largeur totale de la carte
        int mapWidth = WallParser.calculateMapWidth("text_art_test.txt");

        // Mettre à jour et dessiner les objets
        for (Object object : objects) {
            if (object instanceof Wall) {
                Wall wall = (Wall) object;
                // Mettre à jour les coordonnées X pour faire défiler de gauche à droite
                float newX = wall.getX() - scrollSpeed * delta;

                // Réinitialiser la position lorsque la carte entière a défilé hors de l'écran
                if (newX < -Wall.WIDTH) {
                    newX += mapWidth;
                }

                // Mettre à jour la position du mur
                wall.bounds.x = newX;

                // Dessiner le mur avec les nouvelles coordonnées
                wall.draw(batch);
            } else if (object instanceof Zeppelin) {
                // Mettre à jour les coordonnées X du Zeppelin pour le faire défiler de gauche à droite
                Zeppelin zep = (Zeppelin) object;
                zep.setPosition(zep.getPosition().x - scrollSpeed * delta, zep.getPosition().y);

                // Réinitialiser la position du Zeppelin lorsque la carte entière a défilé hors de l'écran
                if (zep.getPosition().x < -70) {
                    zep.setPosition(mapWidth, zep.getPosition().y);
                }

                // Dessiner le Zeppelin avec les nouvelles coordonnées
                zep.draw(batch);
            }
        }

        // Vérifier les collisions entre le joueur et les murs
        for (Object object : objects) {
            if (object instanceof Wall) {
                Wall wall = (Wall) object;
                player.checkCollision(wall);
            }
        }

        // Fin du batch
        batch.end();
    }

    public void PlayerGeneration() {
        batch.begin();

        // Mettre à jour et dessiner le joueur
        player.update();
        player.draw(batch);

        // Fin de ShapeRenderer
        batch.end();
    }

    @Override
    public void render(float truc) {
        // Effacer l'écran
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Défilement du fond
        backgroundOffsetX += scrollSpeed * Gdx.graphics.getDeltaTime(); // Mettre à jour le décalage horizontal
        if (backgroundOffsetX > WORLD_WIDTH) {
            backgroundOffsetX = 0;
        }

        // Dessiner deux fois l'image de fond pour un défilement continu
        batch.draw(background, -backgroundOffsetX, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, -backgroundOffsetX + WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);

        batch.end();

        // Commencer à dessiner avec ShapeRenderer
        PlayerGeneration(); // génération de l'objet contrôlé par le joueur
        MapGeneration(); // génération de la carte
    }

    public void create() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
