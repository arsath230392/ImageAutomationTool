package arsath.imageOperationsCore;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class BasicOperations {
	public static void performClick(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "click", offsetX, offsetY);
	}

	public static void performHover(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "hover", offsetX, offsetY);
	}

	private static void performAction(String inputFilePath, int maxTimeoutInSeconds, String action, int offsetX,
			int offsetY) throws Exception {
		Robot rbt = new Robot();
		long currentTimeInMillis = System.currentTimeMillis();
		long maxTimeout = currentTimeInMillis + (maxTimeoutInSeconds * 1000);
		do {
			currentTimeInMillis = System.currentTimeMillis();
			Dimension d = ImageFinder.getSubImageLocation(inputFilePath);
			if (d != null) {

				int targetX = d.width + offsetX;
				int targetY = d.height + offsetY;
				if (action.equals("click")) {
					rbt.mouseMove(targetX, targetY);
					rbt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					rbt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					return;
				} else if (action.equals("hover")) {
					rbt.mouseMove(targetX, targetY);
					return;
				}

			}
		} while (currentTimeInMillis < maxTimeout);
		throw new ImageNotFoundException(
				"Image '" + inputFilePath + "'not found in Screen.Wait Time:" + maxTimeoutInSeconds);
	}
}
