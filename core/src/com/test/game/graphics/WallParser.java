// WallParser.java
package com.test.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.test.game.planes.Zeppelin;

public class WallParser {
    public static final char WALL_SYMBOL = '█';
    public static final char HEART_SYMBOL = '♥';

    public static Array<Object> parseWalls(String fileName) {
        Array<Object> objects = new Array<>();
        
        FileHandle fileHandle = Gdx.files.internal(fileName);
        String[] lines = fileHandle.readString().split("\\r?\\n");
        
        Texture dirtTexture = new Texture("dirt.png");
        Texture grassTexture = new Texture("grass.jpg");
        
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                char symbol = line.charAt(x);
                switch (symbol) {
                    case WALL_SYMBOL:
                        {
                            int xPos = x * Wall.WIDTH;
                            int yPos = (lines.length - 1 - y) * Wall.HEIGHT;
                            objects.add(new Wall(xPos, yPos, dirtTexture));
                            break;
                        }
                    case '▓':
                        {
                            int xPos = x * Wall.WIDTH;
                            int yPos = (lines.length - 1 - y) * Wall.HEIGHT;
                            objects.add(new Wall(xPos, yPos, grassTexture));
                            break;
                        }
                    case HEART_SYMBOL:
                        {
                            int xPos = x * Wall.WIDTH;
                            int yPos = (lines.length - 1 - y) * Wall.HEIGHT;
                            objects.add(new Zeppelin(xPos, yPos)); // Ajout d'un Zeppelin à la liste
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        
        return objects;
    }

    public static int calculateMapWidth(String fileName) {
        FileHandle fileHandle = Gdx.files.internal(fileName);
        String[] lines = fileHandle.readString().split("\\r?\\n");
        return lines[0].length() * Wall.WIDTH;
    }
}
