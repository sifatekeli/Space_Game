package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sifa Tekeli on 31/07/2017.
 */

public class GameBoard {
    private Texture texture;

    private int x;
    private int y;
    private int height;
    private int width;


    public GameBoard(Game game){
        texture = new Texture("background.jpg");

        x = game.getScoreBoard().getHeight();
        y = 0;
        height = game.getScreenHeight() - game.getScoreBoard().getHeight();
        width = game.getScreenWidth();

    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture, 0, 0, width, height);
        batch.end();
    }

    public void dispose () {
        texture.dispose();
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }

}
