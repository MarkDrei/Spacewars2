package de.rkable.spaceTCG;

import de.rkable.spaceTCG.player.Player;

public interface FightFactory {

	Fight createFight(Player player, Opponent opponent);

}
