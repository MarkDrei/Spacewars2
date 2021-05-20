package de.rkable.spaceTCG.display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DefeatScreen extends Composite {

	public DefeatScreen(Composite parent, GameStateListener gameStateListener) {
		super(parent, SWT.NONE);
		
		setLayout(new FillLayout(SWT.VERTICAL));
		
		Label label = new Label(this, SWT.NONE);
		label.setText("Defeat!");
		label.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				gameStateListener.startOver();
			}
		});
	
	}

}
