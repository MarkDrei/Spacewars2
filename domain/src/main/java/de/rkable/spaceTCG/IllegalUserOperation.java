package de.rkable.spaceTCG;

/**
 * Exception which is used for errors which are caused by clients. 
 *
 */
public class IllegalUserOperation extends Exception {

	private static final long serialVersionUID = -2507652083400922581L;
	
	public IllegalUserOperation(String message) {
		super(message);
	}

}
