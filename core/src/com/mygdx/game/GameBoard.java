package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Sifa Tekeli on 31/07/2017.
 */

public class GameBoard {
    private Texture texture;

    private int x;
    private int y;
    private int height;
    private int width;
    private Game game;

    public GameBoard(Game game){
        this.game = game;
        this.texture = new Texture("background.jpg");

        this.x = 0;
        this.y = 0;
        this.height = game.getScreenHeight() - game.getScoreBoard().getHeight();
        this.width = game.getScreenWidth();

    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch){
        //Fond d'ecran
        batch.begin();
        batch.draw(texture, 0, 0, width, height);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND); //Debut transparence
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Zone rouge
        shapeRenderer.setColor(new Color(255, 0, 0, 0.3f));
        //shapeRenderer.rect(this.getX(), this.getY(), this.getWidth(), (this.game.getRacket().getY() + this.game.getRacket().getHeight()));
        shapeRenderer.rect(this.getX(), this.game.getRacket().getY(), this.getWidth(), (this.game.getRacket().getHeight()));

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND); //Fin transparence
    }

    public void dispose () {
        texture.dispose();
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }

}
