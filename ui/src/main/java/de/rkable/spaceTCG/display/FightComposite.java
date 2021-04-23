package de.rkable.spaceTCG.display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.rkable.spaceTCG.Fight;

public class FightComposite extends Composite{

	public FightComposite(Composite parent, Fight testFight) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());
		
		FightDisplay display = testFight.display();
		
		Label opponentHull = new Label(this, SWT.NONE);
		opponentHull.setText("Oppponent Hull: " + display.opponent.hull);
		
		Label playerHull = new Label(this, SWT.NONE);
		playerHull.setText("Player Hull: " + display.player.hull);
		
		Label cards = new Label(this, SWT.NONE);
		cards.setText("Cards: ");
		
		Label card1 = new Label(this, SWT.NONE);
		card1.setText("Cards: " + display.deckDisplay.card1.getName());
		Label card2 = new Label(this, SWT.NONE);
		card2.setText("Cards: " + display.deckDisplay.card2.getName());
		Label card3 = new Label(this, SWT.NONE);
		card3.setText("Cards: " + display.deckDisplay.card3.getName());
		Label card4 = new Label(this, SWT.NONE);
		card4.setText("Cards: " + display.deckDisplay.card4.getName());
		
	}

}
