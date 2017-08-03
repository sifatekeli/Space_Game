package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import sun.rmi.runtime.Log;

/**
 * Created by Sifa Tekeli on 31/07/2017.
 */

public class ScoreBoard {

    private Game game;
    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;

    private Writer writer;

    public ScoreBoard(Game game){
        this.game = game;

        this.height = game.getScreenHeight() / 25;
        this.width = game.getScreenWidth();
        this.x = 0;
        this.y = game.getScreenHeight() - height;
        this.color = Color.BLACK;

        writer = new Writer(this.height / 2);
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch){

        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, y, width, height);
        shapeRenderer.end();



        batch.begin();
        writer.getFont().setColor(Color.WHITE);

        String textScore = "SCORE: " + game.getScore();
        String textLevel = "  LVL: " + game.getLevel();
        String textLife = "LIFE: " + game.getNbBall() + "  ";

        int textLevelX = 0;
        int textScoreX = (int) (this.x + (this.width - new GlyphLayout(writer.getFont(), textScore).width) / 2);
        int textLifeX = (int) (this.width - new GlyphLayout(writer.getFont(), textLife).width);

        int textY = game.getScreenHeight() - this.height / 3;



        writer.getFont().draw(batch, textLevel, textLevelX, textY);
        writer.getFont().draw(batch, textScore, textScoreX, textY);
        writer.getFont().draw(batch, textLife, textLifeX, textY);

        batch.end();

    }


    public void dispose () {
        writer.getGenerator().dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }
}
