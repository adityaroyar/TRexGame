package hw9;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class for the enemy classes
 * @author royar
 *
 */
public abstract class Enemy {
	/**
	 * Abstract method for the rectangle of the surrounding the obstacle or enemy
	 * @return rectangle with the dimensions of the cactus
	 */
	public abstract Rectangle getBound();
	/**
	 * Abstract method for drawing the enemy
	 * @param graphics
	 */
	public abstract void draw(Graphics g);
	/**
	 * Abstract method for updating the x coordinate of the obstacle
	 */
	public abstract void update();
	/**
	 * Abstract method to check if the obstacle is out of the screen
	 * @return boolean variable indicating if the obstacle is outside the screen width
	 */
	public abstract boolean isOutOfScreen();
	/**
	 * Abstract method for checking if the character was able to jump over the obstacle
	 * If the character was able to jump over the obstacle, the method returns true
	 * This would lead to an increase in score
	 * @return boolean value indicating if the character was able to jump over the obstacle
	 */
	public abstract boolean isOver();
	/**
	 * Abstract method for checking if all the cacti are jumper over and only incrementing the score once
	 */
	public abstract boolean isScoreGot();
	/**
	 * Abstract setter method
	 */
	public abstract void setIsScoreGot(boolean isScoreGot);
}
