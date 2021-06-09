package de.rkable.spaceTCG;

import de.rkable.spaceTCG.map.WorldMap;

public interface GameListener {

	void fightInitiated(Fight fight);
	
	void mapChanged(WorldMap map);
	
}
