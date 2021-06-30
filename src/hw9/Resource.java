package hw9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class which is used to obtain the images from the data folder
 * @author royar
 *
 */
public class Resource {
	/**
	 * Obtains the required images from the specified path
	 * This image will be used to draw the main Character
	 * @param path
	 * @return image to be assigned to an object attribute
	 */
	public static BufferedImage getResourceImage(String path) {
		BufferedImage img = null;
		try {
			//Using the static method read to return a buffered image
			img = ImageIO.read(new File(path));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}
}
