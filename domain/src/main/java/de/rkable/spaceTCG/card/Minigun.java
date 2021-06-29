package de.rkable.spaceTCG.card;

public class Minigun extends MultiDamageCard {
	
	public static final CardFactory FACTORY = new CardFactory(
				() -> new Minigun("Minigun Mk1", 3, 1, 3),
				() -> new Minigun("Minigun Mk2", 3, 1, 4),
				() -> new Minigun("Minigun Mk3", 3, 1, 5)
			);

	public static Minigun createTier1() {
		return new Minigun("Minigun Mk1", 3, 1, 3);
	}
	
	public static Minigun createTier2() {
		return new Minigun("Minigun Mk2", 3, 1, 4);
	}
	
	public static Minigun createTier3() {
		return new Minigun("Minigun Mk3", 3, 1, 5);
	}

	protected Minigun(String name, int hullDamage, int shieldDamage, int burstSize) {
		super(name, hullDamage, shieldDamage, burstSize);
	}

	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires " + burstSize
				+ " ballistic bullets, each causing " + describeDamageAmount();
	}

}
