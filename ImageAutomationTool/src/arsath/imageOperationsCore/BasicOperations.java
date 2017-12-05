package arsath.imageOperationsCore;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.io.IOException;

public class BasicOperations {
	/**
	 * clicks on the image on the screen which matches with the input image
	 * 
	 * @parameters <b>inputFilePath:</b> the image path of the input file.<br>
	 *             <b>maxTimeoutInSeconds:</b> max threshold time the function
	 *             will wait for the image to occur <br>
	 *             <b>offsetX:</b> +ve value moves the mouse to the right and
	 *             -ve value moves to the left of the target image<br>
	 *             <b>offsetY:</b> +ve value moves the mouse down and -ve value
	 *             moves the mouse of the target image
	 * 
	 */
	public static void performClick(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "click", offsetX, offsetY, "");
	}

	/**
	 * Hovers on the image on the screen which matches with the input image
	 * 
	 * @parameters <b>inputFilePath:</b> the image path of the input file.<br>
	 *             <b>maxTimeoutInSeconds:</b> max threshold time the function
	 *             will wait for the image to occur <br>
	 *             <b>offsetX:</b> +ve value moves the mouse to the right and
	 *             -ve value moves to the left of the target image<br>
	 *             <b>offsetY:</b> +ve value moves the mouse down and -ve value
	 *             moves the mouse of the target image
	 */
	public static void performHover(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "hover", offsetX, offsetY, "");
	}

	/**
	 * Clicks and enters text on the target image
	 * 
	 * @parameters <b>inputFilePath:</b> the image path of the input file.<br>
	 *             <b>maxTimeoutInSeconds:</b> max threshold time the function
	 *             will wait for the image to occur <br>
	 *             <b>offsetX:</b> +ve value moves the mouse to the right and
	 *             -ve value moves to the left of the target image<br>
	 *             <b>offsetY:</b> +ve value moves the mouse down and -ve value
	 *             moves the mouse of the target image<br>
	 *             <b>text:</b> the text to be entered
	 */
	public static void sendKeys(String inputFilePath, int maxTimeoutInSeconds, int offsetX, int offsetY, String text)
			throws Exception {
		performAction(inputFilePath, maxTimeoutInSeconds, "send", offsetX, offsetY, text);
	}

	/**
	 * Sends control keys like backspace, enter, alt .. etc
	 * 
	 * @parameters <b>key:</b> the key to be entered. use
	 *             'java.awt.event.KeyEvent' class.
	 */
	public static void sendControlKey(int key) throws Exception {
		Robot rbt = new Robot();
		rbt.keyPress(key);
	}

	/**
	 * Checks if the image is present in the screen currently
	 * 
	 * <b>inputFilePath:</b> the image path of the input file.<br>
	 * <b>maxTimeoutInSeconds:</b> max threshold time the function will wait for
	 * the image to occur <br>
	 */
	public static boolean isImagePresent(String inputFilePath, int maxTimeoutInSeconds) {

		try {
			performAction(inputFilePath, maxTimeoutInSeconds, "hover", 0, 0, "");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Sends control keys like backspace, enter, alt .. etc
	 * 
	 * @parameters <b>key 1:</b> first combination. use
	 *             'java.awt.event.KeyEvent' class.<br>
	 *             <b>key 2:</b> Second combination. use
	 *             'java.awt.event.KeyEvent' class.
	 */
	public static void sendComboKeys(int key1, int key2) throws Exception {
		Robot rbt = new Robot();
		rbt.keyPress(key1);
		rbt.keyPress(key2);
		Thread.sleep(500);
		rbt.keyRelease(key1);
		rbt.keyRelease(key2);

	}

	/**
	 * This method gets the current clipboard content. You can press 'ctrl+A'
	 * and 'ctrl+C'. This will push the content of the text box to system
	 * clipboard
	 */
	public static String getClipboardContent() {
		try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
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
					Thread.sleep(100);
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
			if (code > 96 && code < 123)
				code = code - 32;
			rbt.delay(40);
			rbt.keyPress(code);
			rbt.keyRelease(code);
		}

	}
}
