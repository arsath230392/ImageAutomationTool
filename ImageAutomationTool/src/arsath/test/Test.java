package arsath.test;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import arsath.imageOperationsCore.App;
import arsath.imageOperationsCore.BasicOperations;

public class Test {
	public static void main(String[] args) {
		App app = new App();
		try {
			app.openApp("C://Program Files (x86)//Google//Chrome//Application//chrome.exe");
			JOptionPane.showMessageDialog(null, "Click ok to Start");
			String imageFolderPath = System.getProperty("user.dir") + "//Sample image//";
			System.out.println(BasicOperations.getClipboardContent());
			System.out.println(BasicOperations.isImagePresent(imageFolderPath + "searchtxtbox.PNG", 10));
			BasicOperations.sendKeys(imageFolderPath + "searchtxtbox.PNG", 10, 0, 0, "wiki");
			BasicOperations.sendControlKey(KeyEvent.VK_ENTER);
			BasicOperations.performClick(imageFolderPath + "wiki.PNG", 10, -50, -20);
			BasicOperations.sendKeys(imageFolderPath + "wikisearch.PNG", 10, -100, 0, "cats1@#$#$..>");
			BasicOperations.sendComboKeys(KeyEvent.VK_CONTROL,KeyEvent.VK_A);	
			BasicOperations.sendControlKey(KeyEvent.VK_ENTER);
					
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.closeApp();
		}

	}
}
