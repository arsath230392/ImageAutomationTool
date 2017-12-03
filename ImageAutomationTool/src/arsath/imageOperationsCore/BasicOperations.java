package arsath.imageOperationsCore;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class BasicOperations {
	public static void performClick(String inputFilePath, int maxTimeoutInSeconds) throws Exception {
		Robot rbt = new Robot();
		long currentTimeInMillis = System.currentTimeMillis();
		long maxTimeout = currentTimeInMillis + (maxTimeoutInSeconds * 1000);
		do {
			currentTimeInMillis = System.currentTimeMillis();
			Dimension d = ImageFinder.getSubImageLocation(inputFilePath);
			if (d != null) {
				rbt.mouseMove(d.width, d.height);
				rbt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				rbt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				return;
			}
		} while (currentTimeInMillis < maxTimeout);
		throw new ImageNotFoundException("Image '" + inputFilePath + "'not found in Screen.Wait Time:" + maxTimeoutInSeconds);
	}
}
