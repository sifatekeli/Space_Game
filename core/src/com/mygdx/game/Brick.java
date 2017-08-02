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

    public Brick(Game game, int x, int y){
        this.game = game;

        this.height = 50;
        this.width = game.getScreenWidth() / 10;
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }


    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void dispose () {
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }
}
