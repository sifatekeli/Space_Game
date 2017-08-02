package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Sifa Tekeli on 31/07/2017.
 */

public class Racket {

    private Game game;

    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;

    public Racket(Game game){
        this.game = game;

        this.height = 20;
        this.width = game.getScreenWidth() / 5;
        this.x = (game.getScreenWidth() / 2) - (width / 2);
        this.y = 180;
        this.color = Color.WHITE;
    }

    public void moveTo(int x){
        this. x = x - (width / 2);
    }

    public void render(ShapeRenderer shapeRenderer){
        if(Gdx.input.isTouched()){
            if(!game.getBall().isMoving()) game.getBall().startMoving();
            this.x =  Gdx.input.getX() - (width / 2);
        }

        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void dispose () {
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }

}
