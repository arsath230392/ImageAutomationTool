package arsath.imageOperationsCore;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFinder {
	static BufferedImage inputImage;
	static BufferedImage bf;

	public static void main(String[] args) throws AWTException, IOException {
		Robot rbt = new Robot();
		bf = rbt.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		inputImage = ImageIO.read(new File("E:\\workspace\\ImageReading\\Sample image\\Capture.PNG"));
		Boolean matched = false;
		int x = 0;
		int y = 0;
		for (int xScreen = 0; xScreen < Toolkit.getDefaultToolkit().getScreenSize().getWidth()
				- inputImage.getWidth(); xScreen++) {
			for (int yScreen = 0; yScreen < Toolkit.getDefaultToolkit().getScreenSize().getHeight()
					- inputImage.getHeight(); yScreen++) {

				if (match(xScreen, yScreen)) {
					matched = true;

					x = xScreen;
					y = yScreen;
					xScreen = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
					break;

				}
			}

		}
		if (matched) {
			rbt.mouseMove(x + (inputImage.getWidth()) / 2, y + (inputImage.getHeight()) / 2);
		}
		System.out.println(matched);
	}

	private static boolean match(int xScreen, int yScreen) {

		for (int xInput = 0; xInput < inputImage.getWidth(); xInput++) {
			for (int yInput = 0; yInput < inputImage.getHeight(); yInput++) {
				// System.out.println(xScreen + " " + yScreen);
				Color desktop = new Color(bf.getRGB(xScreen + xInput, yScreen + yInput));
				Color input = new Color(inputImage.getRGB(xInput, yInput));
				if (Math.abs(desktop.getBlue() - input.getBlue()) > 10) {

					return false;
				}
				if (Math.abs(desktop.getRed() - input.getRed()) > 10) {

					return false;
				}
				if (Math.abs(desktop.getGreen() - input.getGreen()) > 10) {

					return false;
				}

			}
		}
		return true;
	}

}
