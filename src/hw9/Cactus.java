package hw9;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class extends the enemy abstract class
 *  Contains the attributes about the cactus
 * @author royar
 *
 */
public class Cactus extends Enemy {
	/**
	 * Declaring a variable for the image of the cactus
	 */
	private BufferedImage cactusImage;
	/**
	 * Declaring an integer variable for the x coordinate of the cactus
	 */
	private int posX;
	/**
	 * Declaring an integer variable for the y coordinate of the cactus
	 */
	private int posY;
	/**
	 * Declaring a rectangle for checking if the character collided with the cactus
	 */
	private Rectangle rect;
	
	/**
	 * Declaring a character object
	 */
	private Character character;
	/**
	 * Declaring a boolean variable for incrementing the score only once
	 */
	private boolean isScoreGot;
	/**
	 * Declaring an object for the game
	 */
	private GameScreen game;
	/**
	 * Declaring an integer for the cutoff score
	 */
	private int cutOffScore = 100;
	/**
	 * Declaring an integer for leveling up
	 */
	private int levelUp;
	/**
	 * Constructor for the cactus class
	 * @param character
	 */
	public Cactus(Character character, GameScreen game) {
		this.levelUp = 0;
		this.game = game;
		this.setCharacter(character);
		this.setCactusImage(Resource.getResourceImage("data/cactus1.png"));
		this.posX = 200;
		this.posY = 65;
		rect = new Rectangle();
	}
	/**
	 * Used when updating the frame
	 */
	@Override
	public void update() {
		if(game.getGameState() == GameScreen.GAME_OVER_STATE) {
			this.levelUp = 0;
			this.cutOffScore = 100;
		}
		// Reducing the x coordinate position
		if(game.getScore() > this.cutOffScore) {
			this.posX = this.posX - levelUp - 4;
			this.levelUp = this.levelUp + 2;
			this.cutOffScore = this.cutOffScore + 100;
			System.out.println("Cactus " + levelUp);
			System.out.println("Cut off " +this.cutOffScore);
		}
		
		this.posX = this.posX - levelUp - 4;
		// Giving the rectangle dimensions the same as the cactus
		rect.x = this.posX;
		rect.y = this.posY;
		rect.width = (this.getImage()).getWidth();
		rect.height = (this.getImage()).getHeight();
		
	}
	/**
	 * Resetting the game when playing again
	 */
	public void resetGame() {
		this.levelUp = 0;
		this.cutOffScore = 100;
	}
	/**
	 * Retrieving the rectangle for the cactus to check for collision
	 */
	@Override
	public Rectangle getBound() {
		return this.rect;
	}
	
	/**
	 * Drawing the cactus
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(cactusImage, this.posX,this.posY,null);
	}
	// Setters
	public void setCactusImage(BufferedImage image) {
		this.cactusImage = image;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	// Getter
	public BufferedImage getImage() {
		return this.cactusImage;
	}
	@Override
	public boolean isOutOfScreen() {
		return (this.posX + this.cactusImage.getWidth() < 0);
	}
	
	@Override
	public boolean isOver() {
		return (this.character.getX() > this.posX);
	}
	
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	@Override
	public boolean isScoreGot() {
		
		return isScoreGot;
	}
	@Override
	public void setIsScoreGot(boolean isScoreGot) {
		this.isScoreGot = isScoreGot;
	}
	
}
