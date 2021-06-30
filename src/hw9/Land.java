package hw9;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.image.BufferedImage;
/**
 * Class for the land images
 * Different land images are added using an arrayList
 * @author royar
 *
 */
public class Land {
	private List<LandImages> listImage;

	/**
	 * Creating objects for the land images to be used in the background
	 */

	private BufferedImage imageLand1;

	private BufferedImage imageLand2;

	private BufferedImage imageLand3;
	/**
	 * Random for random land images
	 */
	private Random random;
	/**
	 * Object for game
	 */
	private GameScreen game;
	/**
	 * Variable for cutoff score(int)
	 */
	private int cutOffScore;
	/**
	 * Variable for leveling up
	 */
	private int levelUp;
	/**
	 * Constructor for the land
	 * @param game
	 */
	public Land(GameScreen game) {
		this.game = game;
		this.cutOffScore = 100;
		this.levelUp = 0;
		// Getting all the land images into objects
		this.imageLand1 = Resource.getResourceImage("data/land1.png");
		this.imageLand2 = Resource.getResourceImage("data/land2.png");
		this.imageLand3 = Resource.getResourceImage("data/land3.png");
		// Assigning the empty arraylist to the declared object
		listImage = new ArrayList<LandImages>();
		// Creating the random object
		random = new Random();
		// Calculating the number of images needed to fill the screen [the + 2 factor is for continuity]
		int numberOfLandImages = 600 / imageLand1.getWidth()+2;
		// Looping over the number of times the land image is required to fill the screen
		for(int i = 0; i < numberOfLandImages; i++) {
			// Declaring a new land image to be added to the arraylist
			LandImages landImage = new LandImages();
			// Setting the x coordinate of the land image
			landImage.setPosX((int)(i * this.imageLand1.getWidth()));
			// Setting the image of the object
			landImage.setImage(this.getRandomLandImage());
			// Adding the image to the arraylist
			listImage.add(landImage);
		}
	}
	/**
	 * Method for updating the ArrayList to give the impression of infinite land
	 */
	public void update() {
		// Decreasing the x coordinate of each image over a loop to give the impression of movement of the land
		for(LandImages imageLand: listImage) {
			
			if(game.getScore() > this.cutOffScore) {
				imageLand.posX = imageLand.posX -levelUp - 4;
				levelUp = levelUp + 2;
				this.cutOffScore = this.cutOffScore + 100;
			}
			imageLand.posX = imageLand.posX - levelUp - 4;	
		}
		// Creating a loop of land images to give the impression of infinite land
		// The first element is assigned to an object
		LandImages firstElement = listImage.get(0);
		// If the image is behind the frame[less than zero]
		if(firstElement.posX + imageLand1.getWidth() < 0) {
			// The first element x coordinate is updated as the value of the final image x coordinate plus the width of the land image used
			firstElement.posX = listImage.get(listImage.size()-1).posX + imageLand1.getWidth();

			listImage.add(firstElement);
			listImage.remove(0);
		}
	}
	/**
	 * Resetting the game when playing again
	 */
	public void resetGame() {
		this.levelUp = 0;
		this.cutOffScore = 100;
	}
	/**
	 * Drawing the land images present in the array list
	 * @param graphics
	 */

	public void draw(Graphics g) {

		for(LandImages  imageLand:listImage) {
			g.drawImage(imageLand.image,imageLand.posX,(int)GameScreen.GROUNDY - 15,null);
		}
	}
	/**
	 * Getting random land image for the background
	 * Used in the array list
	 * @return buffered image of land
	 */
	private BufferedImage getRandomLandImage() {
		// Getting a random land image
		int i = random.nextInt(3);
		// Using a switch conditional for adding a random land image
		switch(i) {
			case 0: return imageLand1;
			case 1: return imageLand2;
			default: return imageLand3;
		}
	}
	/**
	 * Declaring another class having the x coordinate and the image associated with each element of the array list
	 * @author royar
	 *
	 */
	private class LandImages{
		/**
		 * The variable for the x coordinate of an element in the array list
		 */
		private int posX;
		/**
		 * The image associated with each element of the array list 
		 */
		private BufferedImage image;
		//Getters and setters
		public int getPosX() {
			return posX;
		}
		public void setPosX(int posX) {
			this.posX = posX;
		}
		public BufferedImage getImage() {
			return image;
		}
		public void setImage(BufferedImage image) {
			this.image = image;
		}
	}
}
