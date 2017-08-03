package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Sifa Tekeli on 03/08/2017.
 */

public class Menu  {

    Stage stage;
    Skin skin;
    TextButton startGameButton;
    TextButton optionsButton;
    TextButton creditsButton;
    TextButton quitButton;

    public Menu(){
        skin = new Skin( Gdx.files.internal( "ui/defaultskin.json" ));
        stage=new Stage();

        Table table = new Table();
        table.setSize(800,480);

        startGameButton = new TextButton("Start Game", skin);
        table.add(startGameButton).width(200).height(50);
        table.row();

        optionsButton = new TextButton("Options",skin);
        table.add(optionsButton).width(150).padTop(10).padBottom(3);
        table.row();

        creditsButton = new TextButton("Credits",skin);
        table.add(creditsButton).width(150);
        table.row();

        quitButton = new TextButton("Quit",skin);
        table.add(quitButton).width(100).padTop(10);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

        startGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGameButton.addAction(Actions.fadeOut(0.7f));
            }
        });
    }

}