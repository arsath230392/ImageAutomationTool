package arsath.imageOperationsCore;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class BasicOperations {
	public static void performClick(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "click", offsetX, offsetY, "");
	}

	public static void performHover(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "hover", offsetX, offsetY, "");
	}

	public static void sendKeys(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY, String text)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "send", offsetX, offsetY, text);
	}

	private static void performAction(String inputFilePath, int maxTimeoutInSeconds, String action, int offsetX,
			int offsetY, String text) throws Exception {
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
				} else if (action.equals("send")) {
					rbt.mouseMove(targetX, targetY);
					rbt.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					rbt.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					type(text);
					return;
				}

			}
		} while (currentTimeInMillis < maxTimeout);
		throw new ImageNotFoundException(
				"Image '" + inputFilePath + "'not found in Screen.Wait Time:" + maxTimeoutInSeconds);
	}

	private static void type(String text) throws AWTException {
		Robot rbt = new Robot();
		byte[] bytes = text.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			rbt.delay(40);
			rbt.keyPress(code);
			rbt.keyRelease(code);
		}

	}
}
