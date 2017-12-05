package arsath.imageOperationsCore;

import java.awt.AWTException;
import java.awt.Color;
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

	static Dimension getSubImageLocation(String inputImagePath) throws AWTException, IOException {
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
	 * the screen image. 5% of the pixels in equal interval from the input image
	 * will be compared against the current screen to find the best match.
	 */
	private static boolean match(int xScreen, int yScreen) {
		Color inputImageColor;
		Color screenImageColor;
		for (xInput = 0; xInput < inputImage.getWidth(); xInput = xInput + ((int) (0.05 * inputImage.getWidth()))) {
			for (yInput = 0; yInput < inputImage.getHeight(); yInput = yInput
					+ ((int) (0.05 * inputImage.getWidth()))) {
				screenImageColor = new Color(screenImage.getRGB(xScreen + xInput, yScreen + yInput));
				inputImageColor = new Color(inputImage.getRGB(xInput, yInput));
				if (screenImageColor.getRed() != inputImageColor.getRed()) {
					return false;
				}
				if (screenImageColor.getBlue() != inputImageColor.getBlue()) {
					return false;
				}
				if (screenImageColor.getAlpha() != inputImageColor.getAlpha()) {
					return false;
				}

				if (screenImageColor.getGreen() != inputImageColor.getGreen()) {
					return false;
				}

			}
		}
		return true;
	}

}
