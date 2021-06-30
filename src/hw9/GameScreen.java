package hw9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import hw9.Character;

import javax.swing.JPanel;
/**
 * Class containing code for what to be used within the game screen
 * Class implements Runnable to keep the game running and start another thread
 * Class extends JPanel 
 * @author royar
 *
 */


public class GameScreen extends JPanel implements Runnable, KeyListener{
	/**
	 * Defining the initial state of the game
	 */
	public static final int GAME_FIRST_STATE = 0;
	/**
	 * Defining the int value for the game play state
	 */
	public static final int GAME_PLAY_STATE = 1;
	/**
	 * Defining the state when the game is over
	 */
	public static final int GAME_OVER_STATE = 2;
	/**
	 * Variable for gravity
	 */
	public static final float GRAVITY = 0.1f;
	/**
	 * Declaring a variable for the main character
	 */
	private Character character;
	/**
	 * Variable for the ground(in the Y direction) which is a constant measured by pixels
	 */
	public static final float GROUNDY = 110;
	/**
	 * Creating an object for the land for the GameScreen
	 */
	private Land land;
	/**
	 * Creating a cloud object
	 */
	private Clouds cloud;
	/**
	 * Object for the various obstacles in the game
	 */
	private EnemyManager enemiesManager;

	/**
	 * Thread object for running the game
	 */
	private Thread thread;
	/**
	 * Defining a variable for gameState - game is running or game is over
	 */
	private int gameState = GAME_FIRST_STATE;
	/**
	 * Defining the Game Over image
	 */
	private BufferedImage gameOverImage;
	/**
	 * Defining a variable for the score
	 */
	private int score;
	/**
	 * Defining a String for the game instructions
	 */
	private String gameStart;
	/**
	 * Constructor for the Game screen
	 */
	public GameScreen() {
		// Creating a control infinity loop
		// Game Loop to make sure the game keeps running
		thread = new Thread(this);
		// Setting all the variables to the initial states
		this.score = 0;
		character = new Character();
		cloud = new Clouds();
		character.setX(50);
		character.setY(60);
		land = new Land(this);
		enemiesManager = new EnemyManager(this.character,this);
		this.gameOverImage = Resource.getResourceImage("data/gameover_text.png");
		this.gameStart = "Welcome! Avoid the cacti in your way by jumping.";

	}
	public void startGame() {
		// Starting the thread for the game
		thread.start();
	}
	@Override
	public void paint(Graphics g) {
		// Draws the background again to remove trace of the previous rectangle
		//	super.paint(g);
		// Setting the background color
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(0,(int)GROUNDY, getWidth(), (int)GROUNDY);
		

		switch(this.gameState) {
		case GAME_FIRST_STATE:
			character.drawObject(g);
			g.drawString(gameStart, 300, 20);
			g.drawString("To jump,press spacebar. Have fun!",300,35);
			break;
		case GAME_PLAY_STATE:
			cloud.draw(g);
			land.draw(g);
			character.drawObject(g);
			enemiesManager.draw(g);
			g.drawString("SCORE "+String.valueOf(score), 500, 20);
			break;
		case GAME_OVER_STATE:
			cloud.draw(g);
			land.draw(g);
			character.drawObject(g);
			enemiesManager.draw(g);
			g.drawImage(this.gameOverImage,150,50,null);
			g.drawString("SCORE "+String.valueOf(score), 500, 20);
			break;
		

		}
	}
	

	public int getScore() {
		return score;
	}
	/**
	 * Updates the score when the character jumps over the cactus
	 * @param score
	 */
	public void setScore(int score) {
		this.score += score;
	}
	@Override
	public void run() {
		while(true) {
			//	System.out.println(i++);
			try {
				this.update();
				// Repainting the shapes after updating the coordinates
				repaint();
				// Delaying the thread
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Update method for the game screen
	 * Updating all the x coordinate values of the components of the game screen
	 */
	public void update() {
		switch(this.gameState) {
			case GAME_PLAY_STATE:
				this.character.valueUpdate();
				this.cloud.update();
				this.land.update();
				this.enemiesManager.update();
				if(!this.character.isAlive()) {
					this.gameState = GAME_OVER_STATE;
				}
				break;
		}
	}
	/**
	 * Resets all the parameters to the initial state
	 */
	private void resetGame() {
		character.setAlive(true);
		character.setX(50);
		character.setY(60);
		this.score = 0;
		land.resetGame();
		
		enemiesManager.resetGame();
	}
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {

		//System.out.println("Key Pressed");
		


	}
	@Override
	/**
	 * When the space bar key is released then depending on the state of the game,
	 * the objects are updated
	 */
	public void keyReleased(KeyEvent e) {

		switch(e.getKeyCode()) {
		// If the user presses a space bar
		case KeyEvent.VK_SPACE:
			// If the game is in the first state
			if(this.gameState == GAME_FIRST_STATE) {
				// The game state will be changed to the play state
				this.gameState = GAME_PLAY_STATE;
				
			}else if(this.gameState == GAME_PLAY_STATE) {
				// If the space bar is released then the character will jump
				this.character.characterJump();
				// Once the game is over, the game state is reset to the game play state
			}else if(this.gameState == GAME_OVER_STATE) {
				this.resetGame();
				this.gameState = GAME_FIRST_STATE;
				
			}
		}
		//System.out.println("Key Released");

	}
}
