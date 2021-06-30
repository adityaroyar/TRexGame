package hw9;

import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Main class for the game window
 * @author royar
 *
 */

public class GameWindow extends JFrame{
	/**
	 * Creating a variable for Screen Width
	 */
	public static final int SCREEN_WIDTH = 600;
	/**
	 * Creating a variable of GameScreen
	 */
	private GameScreen gameScreen;
	/**
	 * Game Window Constructor
	 */
	public GameWindow() {
		
		// Setting the title of the window
		super("T-Rex Game");
		
		// Setting the size of the window
		setSize(SCREEN_WIDTH,175);
		// Setting the window location
		setLocation(400,200);
		// Setting the Closing operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameScreen = new GameScreen();
		add(gameScreen);
		addKeyListener(gameScreen);
	}
	
	public void startGame() {
		gameScreen.startGame();
	}
	/**
	 * Main method for the game
	 * @param args
	 */
	public static void main(String args[]) {
		
		// Creating a GameWindow to be visible
		GameWindow gw = new GameWindow();
		gw.setVisible(true);
		gw.startGame();
	}
	
	
}
