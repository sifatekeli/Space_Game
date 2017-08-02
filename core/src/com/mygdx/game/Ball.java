package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Sifa Tekeli on 31/07/2017.
 */

public class Ball {

    private Game game;

    private int x;
    private int y;
    private int radius;
    private Color color;
    private boolean isMoving = false;

    int directionX = 1;
    int directionY = 1;
    double speed = 10;

    private Vector<Brick> bricksToDelete;


    public Ball(Game game){
        this.game = game;
        this.radius = 15;
        this.x = game.getRacket().getX() + (game.getRacket().getWidth() / 2);
        this.y = game.getRacket().getY() + game.getRacket().getHeight() + (radius / 2) + 20;
        this.color = Color.WHITE;
        bricksToDelete = new Vector<Brick>();
    }

    public Ball(Game game, double speed){
        this.game = game;
        this.radius = 15;
        this.speed = speed;
        this.x = game.getRacket().getX() + (game.getRacket().getWidth() / 2);
        this.y = game.getRacket().getY() + game.getRacket().getHeight() + (radius / 2) + 20;
        this.color = Color.WHITE;
        bricksToDelete = new Vector<Brick>();
    }

    public void startMoving(){
    }

    public void updatePos(){
        if(!isMoving){
            isMoving = true;
            directionX = random.nextInt(2);
            if(directionX == 0) directionX = -1;
        }

        if(game.isGameRunning()){
            for(int i = 0; i < speed; i++){
                updateDirection();
                x += directionX;
                y += directionY;
            }
        }
    }

    public void updateDirection(){

        //Ball touch the racket
        /*
        if(((this.y - radius) == (game.getRacket().getY() + game.getRacket().getHeight()))){
            //directionY = 1;
            updateDirectionCircleRectangle(getCircle(), game.getRacket().getRectangle());
            game.wallPop();
        }
        */
        if(((this.y - radius) == (game.getRacket().getY() + game.getRacket().getHeight()))){
            if(game.getRacket().getX() <= this.x && this.x <= (game.getRacket().getX() + game.getRacket().getWidth())){
                directionY = 1;
                game.wallPop();
                speed += 0.5;
            }
        }

        //Ball go under the racket
        if(this.y - this.radius < game.getRacket().getY() + game.getRacket().getHeight()){
            System.out.println(this.y - this.radius + "  <  " + (game.getRacket().getY() + game.getRacket().getHeight()));
            game.looseBall();
            game.stopGame();
        }

        //Ball touch the wall
        if(((this.x + this.radius) >= game.getGameBoard().getWidth())){
            //directionX = -1;
            directionX = directionX * -1;
            game.wallPop();
        }
        if((this.x - this.radius) <= 0){
            //directionX = 1;
            directionX = directionX * -1;
            game.wallPop();
        }

        if((this.y + this.radius) >= game.getGameBoard().getHeight()){
            //directionY = -1;
            directionY = directionY * -1;
            game.wallPop();
        }

        if((this.y - this.radius) <= 0){
            //directionY = 1;
            directionY = directionY * -1;
            game.looseSound();
            game.stopGame();
        }

        if(Intersector.overlaps(this.getCircle(), game.getRacket().getRectangle())){
            updateDirectionCircleRectangle(this.getCircle(), game.getRacket().getRectangle());
            game.brickPop();
        }

        //Ball touch a brick
        for(Brick brick: game.getBricks()){
            if(Intersector.overlaps(this.getCircle(), brick.getRectangle())){


                //SI LA BOULE TOUCHE 2 COTES, ON ESTIME QU'ELLE A EFFLEURE ET DONC PAS TOUCHE LA BRIQUE
                int touchedFaces = updateDirectionCircleRectangle(this.getCircle(), brick.getRectangle());
                if(touchedFaces == 1) {
                    bricksToDelete.add(brick);
                    game.brickPop();
                    game.addScore();
                }
            }
        }
        for(Brick brick: bricksToDelete) game.getBricks().remove(brick);
        bricksToDelete.clear();

    }

    public int updateDirectionCircleRectangle(Circle circle, Rectangle rectangle){
        float calcTop = (circle.y - circle.radius) - (rectangle.y + rectangle.width);
        float calcBottom = rectangle.y - (circle.y + circle.radius);
        float calcLeft = rectangle.x - (circle.x + circle.radius);
        float calcRight = (circle.x - circle.radius) - (rectangle.x + rectangle.width);

        float plusPetit =  Math.max(calcLeft, Math.max(calcTop, Math.max(calcRight, calcBottom)));

        // VOIR SI LE PLUS PETIT EST ASSEZ GRAND POUR EVITER QU'IL TOUCHE DEUX COTES EN MEME TEMPS
        // PLUS PETIT > 1.0

        boolean coteGauche = calcLeft == plusPetit;
        boolean coteTop = calcTop == plusPetit;
        boolean coteDroit = calcRight == plusPetit;
        boolean coteBas = calcBottom == plusPetit;

        int nbtrue = 0;

        if (coteTop) nbtrue++;
        if (coteBas) nbtrue++;
        if (coteGauche) nbtrue++;
        if (coteDroit) nbtrue++;

        if(nbtrue == 1) {
            if (coteTop) {
                //directionY = directionY * -1;
                directionY = 1;
            }
            if (coteBas) {
                //directionY = directionY * -1;
                directionY = -1;
            }
            if (coteGauche) {
                //directionX = directionX * -1;
                directionX = -1;
            }
            if (coteDroit) {
                //directionX = directionX * -1;
                directionX = 1;
            }
        }
        return nbtrue;
    }


    public void render(ShapeRenderer shapeRenderer){
        if(game.isGameRunning())    updatePos();

        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
    }

    public void dispose () {
    }

    public boolean isMoving(){
        return isMoving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public double getSpeed() { return speed;  }

    public Circle getCircle(){
        return new Circle(x, y, radius);
    }

    public Rectangle getSquare(){
        return new Rectangle(x-radius, y-radius, radius*2, radius*2);
    }
}
