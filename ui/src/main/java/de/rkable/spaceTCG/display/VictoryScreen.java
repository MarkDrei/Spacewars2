package de.rkable.spaceTCG.display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class VictoryScreen extends Composite {

	public VictoryScreen(Composite parent, BackToMapListener backToMapListener) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		Label label = new Label(this, SWT.NONE);
		label.setText("Victory!");
		label.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				backToMapListener.goBackToMap();
			}
		});
	}


}
