package de.rkable.spaceTCG.display;

import org.eclipse.swt.widgets.Display;

public class Main {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		
		SpaceShell spaceShell = new SpaceShell(display);
		
		while (!spaceShell.getShell().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
