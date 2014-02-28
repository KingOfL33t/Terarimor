package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * An subclass of {@link EntityWithInventory EntityWithInventory} with health and a name.
 * 
 * @author Ches Burks
 *
 */
public class EntityLiving extends EntityWithInventory{
	public int health;
	public int maxHealth = 100;
	
	/**
	 * Constructs a new EntityLiving in the given world.
	 * 
	 * @param world The world to spawn in
	 */
	public EntityLiving(World world){
		super(world);
		health = maxHealth;
	}
	
	/**
	 * Performs any initialization
	 */
	@Override
	protected void entityInit() {
		
	}
	
	/**
	 * Sets the health to the given value. 
	 * If it is set to more than the maximum health value, it is set to the 
	 * max value. If it is below zero, it is set to zero.
	 * 
	 * @param health The new health value
	 */
	public void setEntityHealth(int health) {
		if (health>=maxHealth){
			health = maxHealth;
		}
		if (health<= 0){
			health = 0;
		}
		this.health = health;
	}
	
	/**
	 * Sets the current health to the maximum health.
	 */
	public void setHealthToMax(){
		this.health = this.maxHealth;
	}
	
	
	
}
