package net.jordaria.item;

public enum ArmorSlot {
	HELMET(0),
	CHESTPLATE(1),
	LEGGINGS(2),
	BOOTS(3),
	RING(4),
	NECKLACE(5),
	CAPE(6);
	
	private int index;
	private ArmorSlot(int index){
		this.index = index;
	}
	public int getIndex(){
		return index;
	}
}
