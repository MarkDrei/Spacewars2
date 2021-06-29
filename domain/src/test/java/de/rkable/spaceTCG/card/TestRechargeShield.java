package de.rkable.spaceTCG.card;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.StringContains.*;

import org.junit.jupiter.api.Test;

public class TestRechargeShield {
	
	private RechargeShield rechargeShield = new RechargeShield();
	
	@Test
	public void testDescription() {
		String description = rechargeShield.getDescription();
		// TODO complete test and class
		assertThat(description, containsString("Recharges the shield by 8 points"));
	}

}
