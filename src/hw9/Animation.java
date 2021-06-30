package hw9;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class for the animation of the character using a list of frames
 * @author royar
 *
 */
public class Animation {
	/**
	 * Declaring the List of frame
	 */
	private List<BufferedImage> frames;
	/**
	 * Used for updating the animation
	 */
	private int frameIndex = 0;
	/**
	 * Time in milliseconds for animation
	 */
	private long previousTime;
	/**
	 * Change in time for animation
	 */
	private int deltaTime;
	/**
	 * Constructor for animation class
	 * @param deltaTime
	 */
	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		this.frames = new ArrayList<BufferedImage>();
	}

	/**
	 * Updating the time for movement of the frames
	 * If the current time is more than the previous time by an amount delta
	 * the frame index will change for the image
	 */
	public void update() {

		if(System.currentTimeMillis() - this.previousTime > this.deltaTime) {
			this.frameIndex++;
			if(this.frameIndex >= this.frames.size()) {
				// Resetting the frame index to zero to prevent going out of bounds
				this.frameIndex =0;
			}
			// Updating the previous time
			this.previousTime = System.currentTimeMillis();
		}

	}
	/**
	 * Adding the frames for animation
	 * @param imageToBeAdded
	 */
	public void addFrame(BufferedImage imageToBeAdded) {
		frames.add(imageToBeAdded);
	}
	/**
	 * Return the image from the frames list
	 * @return
	 */
	public BufferedImage getFrame() {
		if(frames.size() > 0) {
			return frames.get(this.frameIndex);
		}
		return null;
	}

}
