package net.jordaria.entity;

import net.jordaria.world.World;

public class EntityLiving extends EntityWithInventory{
	public int health;
	public int maxHealth = 100;
	public String name;
	
	public EntityLiving(World world){
		super(world);
	}
	
	@Override
	protected void entityInit() {
		
	}
	public void setEntityHealth(int health) {
		if (health>=maxHealth){
			health = maxHealth;
		}
		if (health<= 0){
			health = 0;
		}
		this.health = health;
	}
	public void setHealthToMax(){
		this.health = this.maxHealth;
	}
	
}
