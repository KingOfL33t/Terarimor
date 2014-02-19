package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * A subclass of {@link EntityLiving EntityLiving}
 * 
 * @author Ches Burks
 */
public class EntityNPC extends EntityLiving implements CurrencyHolder{

	private int currency = 0;
	/**
	 * Constructs a new EntityNPC in the given world.
	 * 
	 * @param world The world to spawn in
	 */
	public EntityNPC(World world) {
		super(world);
	}
	
	@Override
	public int getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(int amount) {
		this.currency = amount;
	}
	
	@Override
	public void addCurency(int amount) {
		this.currency += amount;
	}

	@Override
	public void removeCurrency(int amount) {
		this.currency -= amount;
		if (this.currency < 0){
			this.currency = 0;
		}
	}

	@Override
	public void transferCurrencyFrom(CurrencyHolder other, int amount) {
		other.removeCurrency(amount);
		addCurency(amount);
	}

	@Override
	public void transferCurrencyTo(CurrencyHolder other, int amount) {
		removeCurrency(amount);
		other.addCurency(amount);
	}

}
