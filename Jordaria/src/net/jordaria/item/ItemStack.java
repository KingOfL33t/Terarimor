package net.jordaria.item;

public class ItemStack {
	public Item item;
	public int amount;
	public boolean isEmpty;

	public ItemStack(Item item){
		this.item = item;
		this.setAmount(1);
	}
	public ItemStack(Item item, int amount){
		this.item = item;
		this.setAmount(amount);
	}
	public Item getItem(){
		return this.item;
	}
	public void addItems(int count){
		this.amount += count;
		if (this.amount >= 1){
			this.isEmpty = false;
		}
	}
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
		}
	}
	public int getAmount(){
		return this.amount;
	}
	public boolean isEmpty(){
		return this.isEmpty;
	}
	public boolean isFull(){
		return (this.amount == this.item.getMaxStackSize());
	}
	public ItemStack clone(){
		return new ItemStack(this.item, this.amount);
	}
}
