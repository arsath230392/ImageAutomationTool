package arsath.test;

import java.awt.event.KeyEvent;

import arsath.imageOperationsCore.App;
import arsath.imageOperationsCore.BasicOperations;

public class Test {
	public static void main(String[] args) {
		try {
			App app = new App();
			app.openApp("C://Program Files (x86)//Google//Chrome//Application//chrome.exe");
			try {
				BasicOperations.performHover("", 10, 0, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
