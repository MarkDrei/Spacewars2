package de.rkable.spaceTCG;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;
import de.rkable.spaceTCG.player.Player;

public class Game implements FightEventListener {

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
	}

	public WorldMap getWorldMap() {
		return worldMap;
	}

	public Player getPlayer() {
		return player;
	}

	public void visit(Waypoint waypoint) {
		if (!worldMap.isReachable(waypoint)) {
			throw new RuntimeException("Waypoint cannot be visited as it is not reachable from current position.");
		}

		worldMap.travel(waypoint);
	}

	public void addGameListener(GameListener gameListener) {
		listeners.add(gameListener);
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
	public void victory() {
		currentFight = null;
	}
	
	@Override
	public void defeat() {
		currentFight = null;
	}

}
