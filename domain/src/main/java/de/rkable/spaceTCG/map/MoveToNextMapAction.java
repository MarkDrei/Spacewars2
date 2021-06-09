package de.rkable.spaceTCG.map;

import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.FightEventListener;
import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.GameListener;
import de.rkable.spaceTCG.Opponent;

public class MoveToNextMapAction extends VisitOpponentAction implements GameListener, FightEventListener {

	private WorldMap worldMap;
	private Fight fight;

	public MoveToNextMapAction(Game game, Opponent opponent, WorldMap worldMap) {
		super(game, opponent);
		this.worldMap = worldMap;
	}

	@Override
	public void triggerVisit() {
		game.addGameListener(this);
		super.triggerVisit();
		
	}

	@Override
	public void fightInitiated(Fight fight_) {
		this.fight = fight_;
		fight.addFightEventListener(this);
	}
	
	@Override
	public void victory(List<Card> rewardOptions) {
		game.setWorldMap(worldMap);
		if (fight != null) {
			fight.removeFightEventListener(this);
		}
		// TODO remove from game as listener
		// TODO test removal
	}

	@Override
	public void mapChanged(WorldMap map) {
		// TODO Auto-generated method stub
		
	}

}
