package arsath.imageOperationsCore;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFinder {
	private static BufferedImage inputImage;
	private static BufferedImage screenImage;

	/**
	 * This is a core image finder code which finds the sub image from current
	 * screen view
	 */
	static int xScreen;
	static int yScreen;
	static Boolean matched;

	public static Dimension getSubImageLocation(String inputImagePath) throws AWTException, IOException {
		Robot rbt = new Robot();

		inputImage = ImageIO.read(new File(inputImagePath));
		screenImage = rbt.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		matched = false;
		int x = 0;
		int y = 0;

		for (xScreen = 0; xScreen < Toolkit.getDefaultToolkit().getScreenSize().getWidth()
				- inputImage.getWidth(); xScreen++) {
			for (yScreen = 0; yScreen < Toolkit.getDefaultToolkit().getScreenSize().getHeight()
					- inputImage.getHeight(); yScreen++) {
				if (match(xScreen, yScreen)) {
					matched = true;
					x = xScreen;
					y = yScreen;
					break;
				}
			}

		}
		if (matched) {
			return new Dimension(x + (inputImage.getWidth()) / 2, y + (inputImage.getHeight()) / 2);
		}
		return null;

	}

	static int xInput;
	static int yInput;

	/**
	 * This is the method that will compare the pixels from the input image to
	 * the screen image.
	 */
	private static boolean match(int xScreen, int yScreen) {

		for (xInput = 0; xInput < inputImage.getWidth(); xInput++) {
			for (yInput = 0; yInput < inputImage.getHeight(); yInput++) {
				if (screenImage.getRGB(xScreen + xInput, yScreen + yInput) != inputImage.getRGB(xInput, yInput)) {
					return false;
				}
			}
		}
		return true;
	}

}
