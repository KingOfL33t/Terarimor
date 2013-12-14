package net.jordaria.entity;

import net.jordaria.item.Item;
import net.jordaria.item.ItemStack;

public class Inventory {
	public InventorySlot[] slots;
	
	public Inventory(int slots){
		this.slots = new InventorySlot[slots];
	}
	public int getSize(){
		return this.slots.length;
	}
	//this is not safe
	public InventorySlot getSlot(int index){
		return this.slots[index];
	}
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
	public void addItem(Item item){
		for (InventorySlot slot : slots){
			if (slot.getItemStack().getItem().canStackWith(item)){
				slot.getItemStack().addItems(1);
			}
		}
	}
	public void addItemStack(ItemStack items){
		for (InventorySlot slot : slots){
			if (slot.canCombineStacksWith(items)){
				slot.combineItemStacks(items);
			}
		}
	}
}
