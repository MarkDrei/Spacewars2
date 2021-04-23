package de.rkable.spaceTCG.display;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.SpaceTcgMain;

public class Main {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		
		Shell shell = new Shell(display);
		shell.setSize(300, 600);
		shell.setLocation(100, 100);
		shell.setText("Space Mock UI");
		shell.open();
		
		Fight testFight = SpaceTcgMain.getTestFight();
		new FightComposite(shell, testFight);
		shell.setLayout(new FillLayout());
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		

	}

}
