package net.jordaria.item;

/**
 * Contains an {@link Item item} and an amount representing the 
 * quantity of the item in the stack.
 * 
 * @author Ches Burks
 */
public class ItemStack {
	public Item item;
	public int amount;
	public boolean isEmpty;

	/**
	 * Constructs a new {@link ItemStack} for the given {@link Item item}.
	 * The stack defaults to a size of one.
	 * 
	 * @param item The item the stack contains
	 */
	public ItemStack(Item item){
		this.item = item;
		this.setAmount(1);
	}
	
	/**
	 * Constructs a new {@link ItemStack} for the given {@link Item item} 
	 * and the given amount.
	 * 
	 * @param item The item the stack contains
	 * @param amount How many of the item are in the stack
	 */
	public ItemStack(Item item, int amount){
		this.item = item;
		this.setAmount(amount);
	}
	
	/**
	 * Returns the {@link Item item} this stack holds.
	 * 
	 * @return The item
	 */
	public Item getItem(){
		return this.item;
	}
	
	/**
	 * Adds the specified amount of the items to the stack. 
	 * The stack will be set to non-empty if the stack size is 
	 * above zero. This will not increase the amount above the 
	 * {@link Item item's} maximum stack size.
	 * 
	 * @param count How many items to add
	 */
	public void addItems(int count){
		this.amount += count;
		if (this.amount >= 1){
			this.isEmpty = false;
			if (this.amount > this.item.getMaxStackSize()){
				this.amount = this.item.getMaxStackSize();
			}
		}
	}
	
	/**
	 *
	 * Removes the specified amount of the items to the stack.
	 * The amount will not decrease below zero.
	 * The stack will be set to empty if the stack size is 
	 * zero.
	 * 
	 * @param count How many items to remove
	 */
	public void removeItems(int count){
		this.amount -= count;
		if (this.amount < 0){
			this.amount = 0;
			this.isEmpty = true;
		}
		else if (this.amount == 0){
			this.isEmpty = true;
		}
		else{
			this.isEmpty = false;
		}
	}
	
	/**
	 * Sets the stack size to the given amount. The stack will be set to 
	 * empty or non-empty accordingly. Any negative size will be set to zero. 
	 * Any value above the {@link Item item's} maximum stack size will be set to 
	 * the maximum stack size.
	 * 
	 * @param amount How many items the stack should contain
	 */
	public void setAmount(int amount){
		this.amount = amount;
		if (this.amount < 0){
			this.amount = 0;
			this.isEmpty = true;
		}
		else if (this.amount == 0){
			this.isEmpty = true;
		}
		else{
			this.isEmpty = false;
			if (this.amount > this.item.getMaxStackSize()){
				this.amount = this.item.getMaxStackSize();
			}
		}
	}
	
	/**
	 * Returns the amount of items in the stack.
	 * 
	 * @return How many items are held in the stack
	 */
	public int getAmount(){
		return this.amount;
	}
	
	/**
	 * Returns true if the stack is empty, false otherwise.
	 * 
	 * @return True if the stack is empty, false otherwise.
	 */
	public boolean isEmpty(){
		return this.isEmpty;
	}
	
	/**
	 * If the amount of items in this stack is the same as 
	 * the {@link Item item's} maximum stack size, returns true.
	 * Otherwise returns false.
	 * 
	 * @return True if the stack is full, false otherwise
	 */
	public boolean isFull(){
		return (this.amount == this.item.getMaxStackSize());
	}
	
	/**
	 * Returns a new {@link ItemStack} with the same item and size as this one.
	 * 
	 * @return The new itemstack
	 */
	public ItemStack clone(){
		return new ItemStack(this.item, this.amount);
	}
}
