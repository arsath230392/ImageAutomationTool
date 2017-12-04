package arsath.imageOperationsCore;

import java.io.IOException;

public class App {
	private Runtime runtime = Runtime.getRuntime();
	private Process prcs;

	public void openApp(String appPath) throws IOException {
		prcs = runtime.exec(appPath);
	}

	public void closeApp() {
		prcs.destroy();
	}
}
