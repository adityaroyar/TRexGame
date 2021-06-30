package hw9;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class declaring the clouds for the background
 * @author royar
 *
 */
public class Clouds {
	/**
	 * Declaring a variable for the cloud image
	 */
	private BufferedImage cloudImage;
	/**
	 * Declaring a list for the various clouds
	 */
	private List<singleCloud> cloudList;
	/**
	 * Constructor of the class
	 */
	public Clouds() {
		// Setting the cloud image
		cloudImage = Resource.getResourceImage("data/cloud.png");
		// Setting the cloudList to an ArrayList
		cloudList = new ArrayList<singleCloud>();
		// Creating a single cloud
		singleCloud cloud1 = new singleCloud();
		// Setting the x and y coordinates of the clouds
		cloud1.posX = 100;
		cloud1.posY = 50;
		singleCloud cloud2 = new singleCloud();
		cloud1.posX = 200;
		cloud1.posY = 30;
		
		singleCloud cloud3 = new singleCloud();
		cloud1.posX = 300;
		cloud1.posY = 80;
		
		singleCloud cloud4 = new singleCloud();
		cloud1.posX = 450;
		cloud1.posY = 50;
		
		singleCloud cloud5 = new singleCloud();
		cloud1.posX = 600;
		cloud1.posY = 60;
		// Adding the clouds to the arraylist of clouds
		cloudList.add(cloud1);
		cloudList.add(cloud2);
		cloudList.add(cloud3);
		cloudList.add(cloud4);
		cloudList.add(cloud5);
	}
	/**
	 * Updates the coordinate values of the clouds to give the appearance of movement
	 */
	public void update() {
		// Decreasing the x coordinate of the cloud to give the illusion of movement
		for(singleCloud cloud: cloudList) {
			cloud.posX--;
		}
		// If the first cloud is behind the game screen window then the x coordinate of the cloud
		// is set to the start of the window
		singleCloud firstCloud = cloudList.get(0);
		if(firstCloud.posX + cloudImage.getWidth() < 0) {
			firstCloud.posX = 600;
			// Removes the element corresponding to the first cloud
			cloudList.remove(firstCloud);
			// Adding it once again
			cloudList.add(firstCloud);
		}
	}
	/**
	 * Drawing each cloud in the cloud list at given x and y coordinates
	 * @param g
	 */
	public void draw(Graphics g) {
		for(singleCloud cloud: cloudList) {
			g.drawImage(cloudImage,(int)cloud.posX,(int)cloud.posY,null);
		}
		
	}
	/**
	 * Declaring another class for a single cloud to be added to the list of clouds
	 * @author royar
	 *
	 */
	private class singleCloud {
		/**
		 * Variable storing the x coordinate of the cloud
		 */
		float posX;
		/**
		 * Variable storing the y coordinate of the cloud
		 */
		float posY;
	}
}
