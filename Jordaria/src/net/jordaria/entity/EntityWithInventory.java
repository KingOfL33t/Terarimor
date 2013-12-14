package net.jordaria.entity;

import net.jordaria.world.World;

public class EntityWithInventory extends Entity{

	public Inventory inventory;
	
	public EntityWithInventory(World par1World) {
		super(par1World);
		this.inventory = new Inventory(16);
	}

	@Override
	protected void entityInit() {
		
	}

}
