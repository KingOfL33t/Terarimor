package net.jordaria.entity;

import net.jordaria.world.World;

public class EntityPlayer extends EntityLiving{

	public float cameraYaw;
	protected final String username;

	public EntityPlayer(World world, String name) {
		super(world);
		this.username = name;
		this.inventory = new Inventory(64);
	}

	public String getUsername(){
		return this.username;
	}

	protected void entityInit() {
		super.entityInit();
	}


}
