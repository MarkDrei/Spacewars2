package de.rkable.spaceTCG.card;

public class Cannon extends SingleDamageCard {
	
	public static final CardFactory FACTORY = new CardFactory(
				() -> new Cannon("Cannon Mk1", 2, 8),
				() -> new Cannon("Cannon Mk2", 3, 10),
				() -> new Cannon("Cannon Mk3", 4, 12)
			);

	protected Cannon(String name, int shieldDamage, int hullDamage) {
		super(name, shieldDamage, hullDamage);
	}

	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires a ballistic bullet, causing " + describeDamageAmount();
	}

}
