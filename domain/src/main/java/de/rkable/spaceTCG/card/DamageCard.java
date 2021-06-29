package de.rkable.spaceTCG.card;

import de.rkable.spaceTCG.Card;

public abstract class DamageCard implements Card {

	protected String name;
	protected int shieldDamage;
	protected int hullDamage;

	protected DamageCard(String name, int shieldDamage, int hullDamage) {
		this.name = name;
		this.shieldDamage = shieldDamage;
		this.hullDamage = hullDamage;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	protected String describeDamageAmount() {
		return shieldDamage + " damage to shields and "
				+ hullDamage + " damage to the hull.";
	}
}
