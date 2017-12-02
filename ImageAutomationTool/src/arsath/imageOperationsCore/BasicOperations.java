package arsath.imageOperationsCore;

import java.awt.Dimension;
import java.awt.Robot;

public class BasicOperations {
	public static void performClick(String inputFilePath, int maxTimeoutInSeconds) throws Exception {
		Robot rbt = new Robot();
		long currentTimeInMillis = System.currentTimeMillis();
		long maxTimeout = currentTimeInMillis + (maxTimeoutInSeconds * 1000);
		while (currentTimeInMillis < maxTimeout) {
			Dimension d = ImageFinder.getSubImageLocation(inputFilePath);
			if (d != null) {
				rbt.mouseMove(d.width, d.height);
			}
		}
		throw new ImageNotFoundException("IMage Not foundin Screen");
	}
}
