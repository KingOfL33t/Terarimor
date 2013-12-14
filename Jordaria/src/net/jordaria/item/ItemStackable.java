package net.jordaria.item;

public class ItemStackable extends Item{
	
	//Override this in subclasses.
	@Override
	public boolean canStackWith(Item other){
		if (this.isStackable() && other.isStackable()) {
			if (this.getClass() == other.getClass()){
				return true;
			}
		}
		return false;		
	}
	
	@Override
	public boolean isStackable(){
		return true;
	}
	
	@Override
	public int getMaxStackSize(){
		return 128;
	}
}
