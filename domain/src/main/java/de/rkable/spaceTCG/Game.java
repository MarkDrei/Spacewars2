package de.rkable.spaceTCG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;
import de.rkable.spaceTCG.player.Player;

public class Game implements FightEventListener {
	
	private Optional<List<Card>> cardsThatCanBeChosenAsAReward = Optional.empty();

	private List<GameListener> listeners = new ArrayList<>();
	private Fight currentFight = null;
	private WorldMap worldMap = null;
	private Player player;
	private FightFactory fightFactory;
	
	public Game(Player player, FightFactory fightFactory) {
		this.player = player;
		this.fightFactory = fightFactory;
	}

	public void setWorldMap(WorldMap worldMap) {
		this.worldMap = worldMap;
		for(GameListener listener : new ArrayList<>(listeners)) {
			listener.mapChanged(worldMap);
		}
	}

	public WorldMap getWorldMap() {
		return worldMap;
	}

	public Player getPlayer() {
		return player;
	}

	public void visit(Waypoint waypoint) throws IllegalUserOperation {
		if (!worldMap.isReachable(waypoint)) {
			throw new IllegalUserOperation("Waypoint cannot be visited as it is not reachable from current position.");
		}

		worldMap.travel(waypoint);
	}

	public void addGameListener(GameListener gameListener) {
		listeners.add(gameListener);
	}
	
	public void removeGameListener(GameListener gameListener) {
		listeners.remove(gameListener);
	}

	public void fightStarted(Opponent opponent) {
		if (currentFight != null) {
			throw new RuntimeException("Previous fight is still in progress, cannot start a new one.");
		}
		currentFight = fightFactory.createFight(player, opponent);
		currentFight.addFightEventListener(this);
		for (GameListener listener : new ArrayList<>(listeners)) {
			listener.fightInitiated(currentFight);
		}
	}
	
	@Override
	public void victory(List<Card> rewardOptions) {
		cardsThatCanBeChosenAsAReward = Optional.of(rewardOptions);
		currentFight = null;
	}
	
	@Override
	public void defeat() {
		currentFight = null;
	}

	public void pickRewardCard(Card card) throws IllegalUserOperation {
		if (cardsThatCanBeChosenAsAReward.isEmpty() || !cardsThatCanBeChosenAsAReward.get().contains(card)) {
			throw new IllegalUserOperation("Cannot pick a card that was not a valid reward");
		}
		player.getDeck().addCard(card);
		cardsThatCanBeChosenAsAReward = Optional.empty();
	}

}
