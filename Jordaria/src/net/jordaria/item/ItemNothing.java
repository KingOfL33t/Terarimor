package net.jordaria.item;

import net.jordaria.text.ColoredText;

/**
 * An {@link Item} subclass that represents nothing. Used 
 * as a placeholder value.
 * 
 * @author Ches Burks
 *
 */
public class ItemNothing extends Item{
	
	/**
	 * Constructs a new {@link ItemNothing}.
	 */
	public ItemNothing(){
		this.itemName = new ColoredText("");
	}
}
