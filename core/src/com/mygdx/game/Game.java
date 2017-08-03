package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Vector;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private GameBoard gameBoard;
	private ScoreBoard scoreBoard;
	private ShapeRenderer shapeRenderer;
	private Racket racket;
	private Ball ball;
	private Vector<Brick> bricks;
	private int nbBall;
	private int score = 0;
	private int level;
	int scoreTotal = 0;

	private boolean isGameRunning = false;

	private Sound begin, wallPop, brickPop, looseSound;

	@Override
	public void create () {
		begin = Gdx.audio.newSound(Gdx.files.internal("begin.mp3"));
		wallPop = Gdx.audio.newSound(Gdx.files.internal("wall_pop.wav"));
		brickPop = Gdx.audio.newSound(Gdx.files.internal("brick_pop.wav"));
		looseSound = Gdx.audio.newSound(Gdx.files.internal("loose_sound.wav"));

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		scoreBoard = new ScoreBoard(this);
		gameBoard = new GameBoard(this);
		racket = new Racket(this);
		ball = new Ball(this);
		bricks = new Vector<Brick>();

		nbBall = 5;
		level = 1;

		int saut = this.getScreenWidth()/10;
		int maxHeight = gameBoard.getHeight();

		bricks.add(new Brick(this, saut*0, maxHeight - 50 - 50, 2));
		bricks.add(new Brick(this, saut*2, maxHeight - 50 - 50, 2));
		bricks.add(new Brick(this, saut*4, maxHeight - 50 - 50, 2));
		bricks.add(new Brick(this, saut*6, maxHeight - 50 - 50, 2));
		bricks.add(new Brick(this, saut*8, maxHeight - 50 - 50, 2));

		bricks.add(new Brick(this, saut*1, maxHeight - 50 - 100, 2));
		bricks.add(new Brick(this, saut*3, maxHeight - 50 - 100, 2));
		bricks.add(new Brick(this, saut*5, maxHeight - 50 - 100, 2));
		bricks.add(new Brick(this, saut*7, maxHeight - 50 - 100, 2));
		bricks.add(new Brick(this, saut*9, maxHeight - 50 - 100, 2));

		bricks.add(new Brick(this, saut*0, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*1, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*2, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*3, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*4, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*5, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*6, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*7, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*8, maxHeight - 50 - 200, 2));
		bricks.add(new Brick(this, saut*9, maxHeight - 50 - 200, 2));



		bricks.add(new Brick(this, saut*0, maxHeight - 50 - 300, 2));
		bricks.add(new Brick(this, saut*2, maxHeight - 50 - 300, 2));
		bricks.add(new Brick(this, saut*4, maxHeight - 50 - 300, 2));
		bricks.add(new Brick(this, saut*6, maxHeight - 50 - 300, 2));
		bricks.add(new Brick(this, saut*8, maxHeight - 50 - 300, 2));

		bricks.add(new Brick(this, saut*1, maxHeight - 50 - 350, 2));
		bricks.add(new Brick(this, saut*3, maxHeight - 50 - 350, 2));
		bricks.add(new Brick(this, saut*5, maxHeight - 50 - 350, 2));
		bricks.add(new Brick(this, saut*7, maxHeight - 50 - 350, 2));
		bricks.add(new Brick(this, saut*9, maxHeight - 50 - 350, 2));

		bricks.add(new Brick(this, saut*0, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*1, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*2, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*3, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*4, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*5, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*6, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*7, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*8, maxHeight - 50 - 450, 1));
		bricks.add(new Brick(this, saut*9, maxHeight - 50 - 450, 1));

		for(Brick b: bricks){
			scoreTotal += b.getNbLife();
		}

		begin.play();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		if(Gdx.input.isTouched()){
			if(!isGameRunning())	startGame();
			racket.moveTo(Gdx.input.getX());
		}

		gameBoard.render(batch);
		scoreBoard.render(shapeRenderer, batch);
		racket.render(shapeRenderer, batch);
		ball.render(shapeRenderer);
		for(Brick b: bricks){
			b.render(shapeRenderer);
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameBoard.dispose();
		scoreBoard.dispose();
		racket.dispose();
		for(Brick b: bricks){
			b.dispose();
		}
	}

	public void startGame(){
		if(nbBall > 0) {
			isGameRunning = true;
			nbBall--;
		}
	}

	public void looseBall(){
		ball = new Ball(this, ball.getSpeed());
		if(isGameRunning()) {
			if (nbBall == 0){
				looseGame();
			}else{
				Gdx.input.vibrate(300);
			}
		}
	}

	public void looseGame(){
		Gdx.input.vibrate(1000);
		stopGame();
	}

	public void deleteBricks(Vector<Brick> touchedBricks){
		for(Brick brick: touchedBricks) {
			addScore(1);
			brick.removeLife(1);
			int nbLifeOfBrick = brick.getNbLife();
			if(nbLifeOfBrick <= 0){
				bricks.remove(brick);
			}
			if(score == scoreTotal) win();
		}
	}

	public void win() {
		level++;
	}

	public void stopGame(){
		isGameRunning = false;
	}

	public void addScore(int nb){
		score += nb;
	}

	public int getScreenWidth() {		return Gdx.graphics.getWidth();	}

	public int getScreenHeight(){		return Gdx.graphics.getHeight();	}

	public GameBoard getGameBoard() {	return gameBoard;	}

	public ScoreBoard getScoreBoard() {	return scoreBoard;	}

	public Racket getRacket() {			return racket;	}

	public Ball getBall() {				return ball;	}

	public int getNbBall() {			return nbBall;	}

	public int getLevel() {				return level;	}

	public int getScore() {				return score;	}

	public boolean isGameRunning() {	return isGameRunning;	}

	public Vector<Brick> getBricks() {	return bricks;	}

	public void wallPop(){
		wallPop.play();
	}

	public void brickPop(){
		brickPop.play();
	}

	public void looseSound(){
		looseSound.play();
	}
}
