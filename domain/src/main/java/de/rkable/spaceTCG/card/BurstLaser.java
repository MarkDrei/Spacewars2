package de.rkable.spaceTCG.card;

public class BurstLaser extends MultiDamageCard {

	public static final CardFactory FACTORY = new CardFactory(
				() -> new BurstLaser("Burst Laser Mk1", 1, 3, 3),
				() -> new BurstLaser("Burst Laser Mk2", 1, 3, 4),
				() -> new BurstLaser("Burst Laser Mk3", 1, 3, 5)
			);
	
	private BurstLaser(String name, int hullDamage, int shieldDamage, int burstSize) {
		super(name, hullDamage, shieldDamage, burstSize);
	}
	
	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires " + burstSize + " laser beams, each causing " + describeDamageAmount();
	}

}
