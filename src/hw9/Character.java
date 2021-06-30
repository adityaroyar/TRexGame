package hw9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 * Class responsible for the main character(T-Rex)
 * @author royar
 *
 */
public class Character {
	/**
	 * Variable for the x coordinate
	 */
	private float x = 0;
	/**
	 * Variable for the y coordinate
	 */
	private float y = 0;
	/**
	 * Variable for speed in Y axis
	 */
	private float speedY = 0;
	/**
	 * Object for the images to show the character running
	 */
	private Animation characterRun;
	/**
	 * Rectangle for character
	 */
	private Rectangle rect;
	/**
	 * Declaring a boolean for checking whether the character is alive or dead
	 */
	private boolean isAlive = true;

	/**
	 * Constructor for the class
	 */
	public Character() {
		// Creating a characterRun object with delayTime as a constructor
		characterRun = new Animation(100);
		// Adding frames for the character running
		characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
		characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
		//characterRun = Resource.getResourceImage("data/main-character1.png");
		rect = new Rectangle();
	}
	/**
	 * Method for updating the character object values
	 * This updates the speed of the object and the y coordinate position when jumping
	 */
	public void valueUpdate() {
		// Updating the image of the character running
		characterRun.update();
		if(this.y>=GameScreen.GROUNDY - characterRun.getFrame().getHeight()) {
			this.speedY = 0;
			this.y = GameScreen.GROUNDY - characterRun.getFrame().getHeight();
		}else {
			//this.x = this.x + 1;
			this.speedY += GameScreen.GRAVITY;
			this.y = this.y + speedY;
		}
		rect.x = (int) this.x;
		rect.y = (int) this.y;
		rect.width = characterRun.getFrame().getWidth();
		rect.height = characterRun.getFrame().getHeight();
	}
	/**
	 * Method for drawing the character object
	 * @param g
	 */
	public void drawObject(Graphics g) {
		// Setting object color
		g.setColor(Color.black);

		// Drawing the rectangle with the given dimensions after casting to int
		//g.drawRect((int)this.x, (int)this.y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
		// Drawing the main character 
		g.drawImage(characterRun.getFrame(), (int)this.x, (int)this.y, null);


	}
	public void characterJump() {
		// Setting the speed to -4 to counteract the gravity
		this.speedY = - 4;
		// Adding this value to the y coordinate
		this.y = this.y + this.speedY;
	}

	// Getters and Setters
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public Rectangle getBound() {
		return this.rect;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
}
