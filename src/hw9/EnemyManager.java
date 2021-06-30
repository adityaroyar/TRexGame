package hw9;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Takes care of the obstacles in the game
 * Adds a cactus or 3 cacti to an arrayList
 * This is implemented using Random
 * @author royar
 *
 */
public class EnemyManager {
	/**
	 * Declaring a list of enemies
	 */
	private List<Enemy> enemies;
	/**
	 * Declaring a variable for random
	 */
	private Random random;
	/**
	 * Declaring images for the various types of Cacti
	 */
	private BufferedImage imageCactus1;
	private BufferedImage imageCactus2;
	/**
	 * Declaring a rectangle for the obstacles
	 */
	private Rectangle rect;
	/**
	 * Declaring a character object
	 */
	private Character character;
	/**
	 * Declaring a game screen object
	 */
	private GameScreen gameScreen;
	/**
	 * Constructor
	 * @param character
	 */
	
	public EnemyManager(Character character, GameScreen gamescreen) {
		this.gameScreen = gamescreen;
		this.character = character;
		// Assigning the variable to an ArrayList
		enemies = new ArrayList<Enemy>();
		random = new Random();
		rect = new Rectangle();
		// Adding random cacti to the list of enemies
		
		imageCactus1 = Resource.getResourceImage("data/cactus1.png");
		imageCactus2 = Resource.getResourceImage("data/cactus2.png");
		enemies.add(this.getRandomCactus());
	}
	/**
	 * Method for updating the score, coordinates of the obstacles
	 * Also, checks if the character collides with the obstacle
	 * This method also adds cacti if they go outside the game screen
	 */
	public void update() {
		for(Enemy en: enemies) {
			en.update();
			if(en.isOver() && !en.isScoreGot()) {
				this.gameScreen.setScore(20);
				en.setIsScoreGot(true);
			}
			if(en.getBound().intersects(this.character.getBound())) {
				this.character.setAlive(false);
			}
			
			
		}
		Enemy firstEnemy = enemies.get(0);
		// If the cactus is out of the screen then the first cactus is removed
		// Another cactus is added
		if(firstEnemy.isOutOfScreen()) {
			this.enemies.remove(firstEnemy);
			this.enemies.add(this.getRandomCactus());
		}
		
	}
	/**
	 * Drawing each obstacle
	 * @param graphics
	 */
	public void draw(Graphics g) {
		for(Enemy en: enemies) {
			en.draw(g);
		}
	}
	/**
	 * Method for resetting the obstacle parameters
	 * The list is cleared and one cactus is added in case of game replay
	 */
	public void resetGame() {
		// Clearing the contents of the list
		this.enemies.clear();
		// Adding at least one cactus for replay
		this.enemies.add(getRandomCactus());
	}
	/**
	 * Method for returning a cactus or cacti with random.nextBoolean()
	 * @return cactus
	 */
	private Cactus getRandomCactus() {
		Cactus cactus;
		cactus = new Cactus(this.character,this.gameScreen);
		// Setting the x coordinate of the cactus at the edge of the window
		cactus.setPosX(600);
		// Setting the image of the cactus as either single or triple
		if(random.nextBoolean()) {
			cactus.setCactusImage(imageCactus1);
		}else {
			cactus.setCactusImage(imageCactus2);
		}
		return cactus;
	}
}
