package net.jordaria.item;

/**
 * An enum of elements. They have an index value and a string name.
 * 
 * @author Ches Burks
 *
 */
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
	
	/**
	 * Constructs a new {@link Element} with the given 
	 * index and name.
	 * 
	 * @param index The index
	 * @param name The name of the element
	 */
	private Element(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	/**
	 * Returns the index of the element.
	 * 
	 * @return The index
	 */
	public int getIndex(){
		return this.index;
	}
	
	/**
	 * Returns the name of the element.
	 * 
	 * @return The name
	 */
	public String getName(){
		return this.name;
	}
}
