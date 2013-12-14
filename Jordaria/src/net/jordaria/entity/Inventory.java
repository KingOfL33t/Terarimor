package net.jordaria.entity;

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
}
