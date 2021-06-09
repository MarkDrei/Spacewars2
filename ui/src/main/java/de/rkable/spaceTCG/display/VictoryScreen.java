package de.rkable.spaceTCG.display;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.IllegalUserOperation;

public class VictoryScreen extends Composite {

	public VictoryScreen(Composite parent, GameStateListener backToMapListener, Game game, List<Card> rewardOptions) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		Label label = new Label(this, SWT.NONE);
		label.setText("Victory!");
		
		for (Card card : rewardOptions)	{
			Label cardDescription = new Label(this, SWT.NONE);
			cardDescription.setText(card.getName() + "\n" + card.getDescription());
			cardDescription.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					try {
						game.pickRewardCard(card);
						backToMapListener.goBackToMap();
					} catch (IllegalUserOperation e1) {
						System.err.println("Cannot pick that card: " + e1.getMessage());
						e1.printStackTrace();
					}
				}
			});
			
		}
		
		
	}


}
