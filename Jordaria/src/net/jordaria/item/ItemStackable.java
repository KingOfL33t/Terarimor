package net.jordaria.item;

/**
 * An item which can be placed in {@link ItemStack stacks}.
 * This has methods to be overridden by subclasses for stack size 
 * and stacking with other items.
 * 
 * @author Ches Burks
 *
 */
public class ItemStackable extends Item{
	
	/**
	 * Returns true if the two items can stack together. If 
	 * either is not stackable or they are of a different type, 
	 * they will not stack.
	 * Override this in subclasses.
	 * 
	 * @param other The other item to check
	 * @return True if they can stack, false otherwise
	 */
	@Override
	public boolean canStackWith(Item other){
		if (this.isStackable() && other.isStackable()) {
			if (this.getClass() == other.getClass()){
				return true;
			}
		}
		return false;		
	}
	
	/**
	 * Returns true if this item can be stacked with items of the 
	 * same type.
	 * 
	 * @return True if the item can be stacked
	 */
	@Override
	public boolean isStackable(){
		return true;
	}
	
	/**
	 * Returns The maximum size of an {@link ItemStack} containing the item.
	 * 
	 * 
	 * @return The maximum stack size for the item.
	 */
	@Override
	public int getMaxStackSize(){
		return 128;
	}
}
