package arsath.imageOperationsCore;

public class ImageNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageNotFoundException(String string) throws Exception {
		throw new Exception(string);
	}

}
