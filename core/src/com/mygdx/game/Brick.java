package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Sifa Tekeli on 01/08/2017.
 */

public class Brick {

    private Game game;

    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;
    private int nbLife;

    public Brick(Game game, int x, int y, int nbLife){
        this.game = game;

        this.height = game.getScreenHeight() / 25;
        this.width = game.getScreenWidth() / 10;
        this.x = x;
        this.y = y;
        this.nbLife = nbLife;
        this.color = color.WHITE;
    }

    public void render(ShapeRenderer shapeRenderer){

        shapeRenderer.setColor(color);
        if(nbLife == 2) shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(nbLife == 1) shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void dispose () {
    }

    public void removeLife(int nb){
        this.nbLife = this.nbLife - nb;
    }

    public int getNbLife(){
        return nbLife;
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }
}
