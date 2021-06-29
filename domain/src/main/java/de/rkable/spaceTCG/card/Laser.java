package de.rkable.spaceTCG.card;

public class Laser extends SingleDamageCard {
	
	public static final CardFactory FACTORY = new CardFactory(
			() -> new Laser("Laser Mk1", 8, 2),
			() -> new Laser("Laser Mk2", 10, 3),
			() -> new Laser("Laser Mk3", 12, 4)
			);

	private Laser(String name, int shieldDamage, int hullDamage) {
		super(name, shieldDamage, hullDamage);
	}

	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires a laser beam, causing " + describeDamageAmount();
	}

}
