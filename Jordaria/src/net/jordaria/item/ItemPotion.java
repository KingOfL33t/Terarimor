package net.jordaria.item;

public class ItemPotion extends ItemStackable{
	public PotionEffect effect;

	public ItemPotion (PotionEffect effect){
		this.effect = effect;
	}

	public PotionEffect getEffect(){
		return this.effect;
	}

	@Override
	public int getMaxStackSize(){
		return 64;
	}

	@Override
	public boolean canStackWith(Item other){
		if (this.isStackable() && other.isStackable()) {
			//you know that this is a potion so only check if the other item is
			if (other instanceof ItemPotion){
				if (this.getEffect().getIndex() == ((ItemPotion)other).getEffect().getIndex()){
					return true;//must have same effect to stack
				}
			}
		}
		return false;		
	}
}
