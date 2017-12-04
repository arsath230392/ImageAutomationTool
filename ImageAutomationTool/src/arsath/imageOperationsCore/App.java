package arsath.imageOperationsCore;

import java.io.IOException;

public class App {
	private Runtime runtime = Runtime.getRuntime();
	private Process prcs;

	/** opens a executable program */
	public void openApp(String appPath) throws IOException {
		prcs = runtime.exec(appPath);
	}

	/** closes the opened process */
	public void closeApp() {
		prcs.destroyForcibly();
	}
}
