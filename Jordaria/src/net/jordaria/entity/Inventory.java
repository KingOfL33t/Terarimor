package net.jordaria.entity;

import net.jordaria.item.Item;
import net.jordaria.item.ItemStack;

/**
 * Contains slots for items and methods for modifying the contents.
 * 
 * @author Ches Burks
 *
 */
public class Inventory {
	public InventorySlot[] slots;
	
	/**
	 * Constructs a new Inventory with the given amount of slots.
	 * 
	 * @param slots The number of slots the inventory will have
	 */
	public Inventory(int slots){
		this.slots = new InventorySlot[slots];
	}
	
	/**
	 * Returns how many slots the inventory contains.
	 * 
	 * @return The size of the slot array
	 */
	public int getSize(){
		return this.slots.length;
	}

	/**
	 * Returns the {@link InventorySlot InventorySlot} in the given index, if it exists.
	 * 
	 * @param index The index of the slot to retrieve
	 * @return The slot in the given index
	 */
	public InventorySlot getSlot(int index){
		return this.slots[index];
	}
	
	/**
	 * Returns true if the inventory has no empty slots, false otherwise.
	 * 
	 * @return True if the inventory has no empty slots, false otherwise.
	 */
	public boolean isFull(){
		boolean empty = false;
		for (InventorySlot slot : slots){
			if (slot.isEmpty()){
				empty = true;
				break;
			}
		}
		return !empty;
	}
	
	/**
	 * Adds the item to the first available {@link InventorySlot InventorySlot}, if possible.
	 * 
	 * @param item The item to add
	 */
	public void addItem(Item item){
		for (InventorySlot slot : slots){
			if (slot.getItemStack().getItem().canStackWith(item)){
				slot.getItemStack().addItems(1);
				break;
			}
		}
	}
	
	/**
	 * Combines the {@link ItemStack ItemStack} with the first available stack, if possible.
	 * 
	 * @param items The itemstack to add
	 */
	public void addItemStack(ItemStack items){
		for (InventorySlot slot : slots){
			if (slot.canCombineStacksWith(items)){
				slot.combineItemStacks(items);
				break;
			}
		}
	}
}
