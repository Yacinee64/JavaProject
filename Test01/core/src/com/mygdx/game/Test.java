package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

// La classe Test étend ApplicationAdapter, qui est une classe de base de libGDX pour la création d'applications.
public class Test extends ApplicationAdapter {
/*
    int x = 50;
    int y = 50;
    int xSpeed = 5;

    // Déclaration d'un objet ShapeRenderer pour dessiner des formes géométriques.
    ShapeRenderer shape;

    // Méthode appelée une seule fois au début du programme pour initialiser les variables et les ressources.
    @Override
    public void create () {
        // Initialisation de l'objet ShapeRenderer.
        shape = new ShapeRenderer();
    }

    // Méthode appelée à chaque image pour effectuer le rendu.
    @Override
    public void render () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        // Début du dessin des formes géométriques ici remplis
        shape.begin(ShapeRenderer.ShapeType.Filled);
        
        // Dessin d'un cercle avec le centre aux coordonnées (50, 50) et un rayon de 50 pixels.
        shape.circle(x, y, 50);
        
        // Fin du dessin des formes géométriques.
        shape.end();
        x += xSpeed;
        if (x > Gdx.graphics.getWidth()) {
            xSpeed = -5;
        }
        if (x < 0) {
            xSpeed = 5;
        }
    } // Fin de la méthode render()
    */
    ShapeRenderer shape;
    ArrayList<Ball> balls = new ArrayList<>();
    Random r = new Random();

    @Override
    public void create() {
        shape = new ShapeRenderer();
        for (int i = 0; i < 10; i++) {
            balls.add(new Ball(r.nextInt(Gdx.graphics.getWidth()),
                    r.nextInt(Gdx.graphics.getHeight()),
                    r.nextInt(100), r.nextInt(15), r.nextInt(15)));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball ball : balls) {
            ball.update();
            ball.draw(shape);
        }
        shape.end();
    }
}
