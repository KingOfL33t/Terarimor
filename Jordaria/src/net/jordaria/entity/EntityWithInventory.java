package net.jordaria.entity;

import net.jordaria.world.World;

public class EntityWithInventory extends Entity{

	public Inventory inventory;
	
	public EntityWithInventory(World world) {
		super(world);
		this.inventory = new Inventory(16);
	}

	@Override
	protected void entityInit() {
		
	}

}
