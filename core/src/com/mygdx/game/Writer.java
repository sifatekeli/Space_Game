package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Created by Sifa Tekeli on 03/08/2017.
 */

public class Writer {

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    private FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    private BitmapFont font;

    public Writer(int size){
        parameter.size = size;
        font = generator.generateFont(parameter);
    }

    public BitmapFont getFont(){
        return font;
    }

    public FreeTypeFontGenerator getGenerator(){
        return generator;
    }


}
