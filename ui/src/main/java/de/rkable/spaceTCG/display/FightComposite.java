package de.rkable.spaceTCG.display;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.FightEventListener;
import de.rkable.spaceTCG.GameStateChange;

public class FightComposite extends Composite implements FightEventListener {

	private Fight fight;
	
	private Label opponentHull;
	private Label playerHull;
	private Label energy;
	private Label card1;
	private Label card2;
	private Label card3;
	private Label card4;


	public FightComposite(Composite parent, Fight testFight) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		this.fight = testFight;
		
		opponentHull = new Label(this, SWT.NONE);
		
		playerHull = new Label(this, SWT.NONE);
		
		energy = new Label(this, SWT.NONE);
		
		Label cards = new Label(this, SWT.NONE);
		cards.setText("Cards: ");
		
		card1 = new Label(this, SWT.NONE);
		card2 = new Label(this, SWT.NONE);
		card3 = new Label(this, SWT.NONE);
		card4 = new Label(this, SWT.NONE);
		
		testFight.addFightEventListener(this);
		
		addMouseListenersForCards(testFight);
		
		Composite buttonArea = new Composite(this, SWT.NONE);
		buttonArea.setLayout(new FillLayout(SWT.HORIZONTAL));
		Button endTurn = new Button(buttonArea, SWT.PUSH);
		endTurn.setText("End Turn");
		endTurn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				fight.endTurn();
				updateDisplay();
			}
		});
		
		updateDisplay();
	}
	
	@Override
	public void cardPlayed(Card card, List<GameStateChange> changes) {
		StringBuilder sb = new StringBuilder();
		sb.append("Event [\n");
		sb.append("  played card: "+ card.getName() + "\n");
		for (GameStateChange change : changes) {
			sb.append("  Change: " + change + "\n");
		}
		sb.append("]\n");
		System.out.println(sb.toString());
		updateDisplay();
	}
	
	@Override
	public void dispose() {
		fight.removeFightEventListener(this);
		super.dispose();
	}

	private void addMouseListenersForCards(Fight testFight) {
		card1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("Playing card 1");
				testFight.play(fight.display().deckDisplay.card1);
			}
		});
		card2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("Playing card 2");
				testFight.play(fight.display().deckDisplay.card2);
			}
		});
		card3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("Playing card 3");
				testFight.play(fight.display().deckDisplay.card3);
			}
		});
		card4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("Playing card 4");
				testFight.play(fight.display().deckDisplay.card4);
			}
		});
	}
	
	private void updateDisplay() {
		
		FightDisplay display = fight.display();
		energy.setText("Energy: " + display.energy + "/" + display.maxEnergy);
		card1.setText("Card: " + display.deckDisplay.card1.getName());
		card2.setText("Card: " + display.deckDisplay.card2.getName());
		card3.setText("Card: " + display.deckDisplay.card3.getName());
		card4.setText("Card: " + display.deckDisplay.card4.getName());
		opponentHull.setText("Oppponent Hull: " + display.opponent.hull);
		playerHull.setText("Player Hull: " + display.player.hull);
		
	}

	@Override
	public void victory() {
		// ignore
	}

}
