package net.jordaria.entity;

import net.jordaria.item.ItemNothing;
import net.jordaria.item.ItemStack;

/**
 * A slot for an {@link ItemStack ItemStack}.
 * 
 * @author Ches Burks
 *
 */
public class InventorySlot {
	public boolean isEmpty;
	public ItemStack itemStack;

	/**
	 * Constructs an empty InventorySlot.
	 */
	public InventorySlot(){
		this.setEmpty();
	}

	/**
	 * Sets the current stack to nothing and returns the old stack if it exists.
	 * 
	 * @return The old stack. This may not contain items.
	 */
	public ItemStack setEmpty(){
		ItemStack oldStack = this.itemStack;
		if (oldStack == null){
			oldStack = new ItemStack(new ItemNothing(),0);//add one nothing to the stack
		}

		this.itemStack = new ItemStack(new ItemNothing(),0);//set this to an empty placeholder
		this.isEmpty = true;

		return oldStack;
	}
	
	/**
	 * Returns true if this is empty, false otherwise.
	 * @return True if this is empty, false otherwise
	 */
	public boolean isEmpty(){
		return this.isEmpty;
	}

	/**
	 * Sets the ItemStack to the specified ItemStack and returns the old stack.
	 * 
	 * @param newStack The stack to replace the old one
	 * @return The stack that was replaced. This may not contain items.
	 */
	public ItemStack setItemStack(ItemStack newStack){
		ItemStack oldStack;//the old stack to be returned
		ItemStack toStore;//the new stack, which may or may not end up the same as newStack

		if (this.isEmpty || this.itemStack == null){//if this is empty then return a placeholder empty stack
			oldStack = new ItemStack(new ItemNothing(),0);
		}
		else{
			oldStack = this.itemStack;
		}

		if (newStack.isEmpty){//if it is empty then use a placeholder empty itemstack
			toStore = new ItemStack(new ItemNothing(), 0);
			this.isEmpty = true;
		}
		else{//if the itemstack is not empty, then use it
			toStore = newStack;
			this.isEmpty = false;
		}

		this.itemStack = toStore;

		return oldStack;
	}

	/**
	 * Returns the pointer to the ItemStack object.
	 * 
	 * @return The current ItemStack
	 */
	public ItemStack getItemStack(){
		return this.itemStack;
	}

	/**
	 * Tries to take the requested amount of items from the stack
	 * and return it in a new stack. If too many items are requested
	 * then it will return as many as the stack currently has. 
	 * The items are removed from this stack after they are taken.
	 * 
	 * @param count The amount of items to take
	 */
	public ItemStack takeItems(int count){
		ItemStack toReturn;
		if (this.isEmpty() || this.itemStack == null){
			toReturn = new ItemStack(new ItemNothing(), 0);
		}
		else if (this.itemStack.getAmount() <= count){
			//if the itemstack is the same size or less than the count requested
			//then return the whole stack and empty this slot
			toReturn = this.itemStack;
			setEmpty();
		}
		else{//the stack is larger than the requested stack size
			//create a new stack of the stacks items
			toReturn = new ItemStack(this.itemStack.getItem(), count);
			//remove the items from the old stack
			this.itemStack.removeItems(count);
		}

		return toReturn;
	}
	
	/**
	 * Try to combine this item stack with another. Returns true if it succeeded, false if not.
	 * 
	 * @param toAdd The stack to combine with this stack
	 * @return True if it succeeded, false otherwise.
	 */
	public boolean combineItemStacks(ItemStack toAdd){
		if (canCombineStacksWith(toAdd)){
			this.itemStack.addItems(toAdd.getAmount());
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the other stack is the same type as the current one 
	 * and the items can be combined into one stack.
	 * 
	 * @param other The stack to test combining with
	 * @return True if the stacks can be combined, false otherwise
	 */
	public boolean canCombineStacksWith(ItemStack other){
		if (!(this.itemStack.getItem().canStackWith(other.getItem()))){
			return false;//the items cant stack
		}
		if (this.itemStack.isFull()){
			return false;//the stack is full
		}
		if ((this.itemStack.getAmount() + other.getAmount()) > this.itemStack.getItem().getMaxStackSize()){
			return false;//the total of the two is larger than the max stack size
		}
		return true;//they can be combined, and the stacks combined is within stack limits
	}
	
}
