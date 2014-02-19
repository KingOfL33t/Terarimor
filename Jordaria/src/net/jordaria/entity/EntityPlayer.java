package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * A subclass of {@link EntityLiving EntityLiving} that contains a username.
 * 
 * @author Ches Burks
 *
 */
public class EntityPlayer extends EntityLiving implements CurrencyHolder{

	protected final String username;
	private int currency = 0;
	/**
	 * Constructs a new EntityPlayer in the given world with the 
	 * specified username.
	 * 
	 * @param world The world to spawn in
	 * @param name The username of the player
	 */
	public EntityPlayer(World world, String name) {
		super(world);
		this.username = name;
		this.inventory = new Inventory(64);
	}

	/**
	 * Returns the players username.
	 * 
	 * @return The username
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Performs any initialization
	 */
	protected void entityInit() {
		super.entityInit();
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
