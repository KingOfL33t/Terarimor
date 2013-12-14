package net.jordaria.entity;

import net.jordaria.item.ItemStack;
import net.jordaria.world.World;


/*
 * An item that has been dropped
 */
public class EntityItem extends EntityWithInventory{

	public EntityItem(World world) {
		super(world);
		this.inventory = new Inventory(1);
	}
	public EntityItem(World world, ItemStack items){
		super(world);
		this.inventory.addItemStack(items);
	}
	
}
