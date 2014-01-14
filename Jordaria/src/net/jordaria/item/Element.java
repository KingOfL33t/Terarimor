package net.jordaria.item;

public enum Element {
	NORMAL(0,"Normal"),
	FIRE(1,"Fire"),
	WATER(2,"Water"),
	STONE(3,"Stone"),
	POISON(4,"Poison"),
	ICE(5,"Ice"),
	MAGIC(6,"Magic"),
	LIGHT(7,"Light"),
	DARK(8,"Dark");
	
	private int index;
	private String name;
	private Element(int index, String name){
		this.index = index;
		this.name = name;
	}
	public int getIndex(){
		return this.index;
	}
	public String getName(){
		return this.name;
	}
}
