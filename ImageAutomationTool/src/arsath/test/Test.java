package arsath.test;

import java.awt.event.KeyEvent;

import arsath.imageOperationsCore.BasicOperations;

public class Test {
	public static void main(String[] args) {
		try {
			BasicOperations.sendComboKeys(
					"E:\\workspace\\GIT\\ImageAutomationTool\\ImageAutomationTool\\Sample image\\1.PNG", 10, 300, 0,
					KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			BasicOperations.sendKeys(
					"E:\\workspace\\GIT\\ImageAutomationTool\\ImageAutomationTool\\Sample image\\1.PNG", 10, 300, 0,
					"Firs Name");
			BasicOperations.sendKeys(
					"E:\\workspace\\GIT\\ImageAutomationTool\\ImageAutomationTool\\Sample image\\2.PNG", 10, 300, 0,
					"Middle name");
			BasicOperations.sendKeys(
					"E:\\workspace\\GIT\\ImageAutomationTool\\ImageAutomationTool\\Sample image\\3.PNG", 10, 300, 0,
					"2");
			BasicOperations.sendKeys(
					"E:\\workspace\\GIT\\ImageAutomationTool\\ImageAutomationTool\\Sample image\\4.PNG", 10, 300, 0,
					"2");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
