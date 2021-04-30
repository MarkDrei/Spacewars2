package de.rkable.spaceTCG;

import java.util.List;

public interface FightEventListener {

	void cardPlayed(Card card, List<GameStatChange> changes);
}
