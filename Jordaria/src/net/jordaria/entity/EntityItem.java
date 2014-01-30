package net.jordaria.entity;

import net.jordaria.item.ItemStack;
import net.jordaria.world.World;


/**
 * An item that has been dropped.
 * 
 * @author Ches Burks
 */
public class EntityItem extends EntityWithInventory{

	/**
	 * Constructs a new EntityItem in the given world.
	 * 
	 * @param world The world to spawn in
	 */
	public EntityItem(World world) {
		super(world);
		this.inventory = new Inventory(1);
	}
	
	/**
	 * Constructs a new EntityItem in the given world 
	 * containing an item stack.
	 * 
	 * @param world The world to spawn in
	 * @param items The item stack to be stored in the item
	 */
	public EntityItem(World world, ItemStack items){
		super(world);
		this.inventory.addItemStack(items);
	}
	
}
