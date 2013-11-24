package net.jordaria.entity;

import net.jordaria.world.World;

public class EntityLiving extends Entity{
	public int health;
	public String name;
	protected boolean isJumping;
	
	public EntityLiving(World world){
		super(world);
	}
	public void setJumping(boolean jumping) {
		this.isJumping = jumping;
	}
	
	@Override
	protected void entityInit() {
		
	}
	
}
