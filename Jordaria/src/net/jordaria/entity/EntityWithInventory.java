package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * A subclass of {@link Entity Entity} that contains an inventory.
 * 
 * @author Ches Burks
 *
 */
public class EntityWithInventory extends Entity{

	public Inventory inventory;
	
	/**
	 * Constructs a new EntityWithInventory in the given world.
	 * 
	 * @param world The world to spawn in
	 */
	public EntityWithInventory(World world) {
		super(world);
		this.inventory = new Inventory(16);
	}

	/**
	 * Performs any initialization
	 */
	@Override
	protected void entityInit() {
		
	}

}
