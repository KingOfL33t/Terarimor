package net.jordaria.item;

import net.jordaria.text.ColoredText;

/**
 * A base item with a colored name. By default they do not stack.
 * 
 * @author Ches Burks
 *
 */
public class Item {
	public ColoredText itemName;
	
	/**
	 * Returns true if this item can be stacked with items of the 
	 * same type.
	 * 
	 * @return True if the item can be stacked
	 */
	public boolean isStackable(){
		return false;
	}
	
	/**
	 * Returns true if the two items can stack together. If 
	 * either is not stackable or they are of a different type, 
	 * they will not stack.
	 * 
	 * @param other The other item to check
	 * @return True if they can stack, false otherwise
	 */
	public boolean canStackWith(Item other){
		return false;		
	}
	
	/**
	 * Returns The maximum size of an {@link ItemStack} containing the item.
	 * 
	 * 
	 * @return The maximum stack size for the item.
	 */
	public int getMaxStackSize(){
		return 1;
	}
	
	/**
	 * Sets the name of the item to the given {@link ColoredText colored text}.
	 * 
	 * @param name The new name
	 */
	public void setItemName(ColoredText name){
		this.itemName = name;
	}
	
	/**
	 * Returns the {@link ColoredText} representing the name of the item.
	 * 
	 * @return The name
	 */
	public ColoredText getItemName(){
		return this.itemName;
	}
}
