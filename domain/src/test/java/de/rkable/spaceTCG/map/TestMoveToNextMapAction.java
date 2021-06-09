package de.rkable.spaceTCG.map;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.Opponent;

@ExtendWith(MockitoExtension.class)
public class TestMoveToNextMapAction {
	
	@Mock
	private Game game;
	@Mock
	private Opponent opponent;
	@Mock
	private WorldMap worldMap;	
	
	@InjectMocks
	private MoveToNextMapAction action;
	
	@Test
	public void triggerVisit_registersGameListener() {
		action.triggerVisit();
		InOrder inOrder = inOrder(game);
		inOrder.verify(game).addGameListener(action);
		inOrder.verify(game).fightStarted(opponent);
	}
	
	@Test
	public void fightInitiated_registersFightListener() {
		Fight fight = mock(Fight.class);
		action.fightInitiated(fight);
		verify(fight).addFightEventListener(action);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void victory_setsNewMapOnTheGame() {
		action.victory(mock(List.class));
		verify(game).setWorldMap(worldMap);
	}

}
