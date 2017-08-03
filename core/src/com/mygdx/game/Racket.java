package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        this.width = game.getScreenWidth() / 4;

        int randomX = (int )(Math.random() * (game.getScreenWidth() - width) + (width/2));

        this.x = randomX - (width/2);
        //this.x = (game.getScreenWidth() / 2) - (width / 2);
        this.y = 180;
        this.color = Color.WHITE;
    }

    public void moveTo(int x){
        this. x = x - (width / 2);
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch){
        if(Gdx.input.isTouched()){
            if(!game.getBall().isMoving()) game.getBall().startMoving();
            this.x =  Gdx.input.getX() - (width / 2);
        }

        Gdx.gl.glEnable(GL20.GL_BLEND); //Debut transparence
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //shadow
        shapeRenderer.setColor(new Color(255, 255, 255, 0.1f));
        shapeRenderer.rect(x, game.getGameBoard().getY(), width, game.getGameBoard().getHeight());

        //racket
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND); //Fin transparence

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
