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

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    private BitmapFont font;

    public ScoreBoard(Game game){
        this.game = game;

        this.height = 50;
        this.width = game.getScreenWidth();
        this.x = 0;
        this.y = game.getScreenHeight() - height;
        this.color = Color.BLACK;

        parameter.size = 25;
        font = generator.generateFont(parameter);
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch){

        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, y, width, height);
        shapeRenderer.end();



        batch.begin();
        font.setColor(Color.WHITE);

        String textScore = "SCORE: " + game.getScore();
        String textLevel = "  LVL: " + game.getLevel();
        String textLife = "LIFE: " + game.getNbBall() + "  ";

        int textLevelX = 0;
        int textScoreX = (int) (this.x + (this.width - new GlyphLayout(font, textScore).width) / 2);
        int textLifeX = (int) (this.width - new GlyphLayout(font, textLife).width);

        int textY = game.getScreenHeight() - this.height / 3;



        font.draw(batch, textLevel, textLevelX, textY);
        font.draw(batch, textScore, textScoreX, textY);
        font.draw(batch, textLife, textLifeX, textY);

        batch.end();

    }


    public void dispose () {
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public int getX() { return x;   }

    public int getY() { return y;   }

    public int getHeight() {    return height;  }

    public int getWidth() { return width;   }
}
