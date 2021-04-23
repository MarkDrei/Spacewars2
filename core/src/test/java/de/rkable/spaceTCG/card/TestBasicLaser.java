package de.rkable.spaceTCG.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestBasicLaser {
	
	@Test
	public void name() {
		assertEquals("Laser", new BasicLaser().getName());
	}

}
