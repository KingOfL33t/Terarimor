package net.jordaria.item;

public enum Element {
	NORMAL(0,"element.normal"),
	FIRE(1,"element.fire"),
	WATER(2,"element.water"),
	STONE(3,"element.stone"),
	POISON(4,"element.poison"),
	ICE(5,"element.ice"),
	MAGIC(6,"element.magic"),
	LIGHT(7,"element.light"),
	DARK(8,"element.dark");
	
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
