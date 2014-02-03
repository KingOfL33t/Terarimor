package net.jordaria.item;

/**
 * An item subclass representing a potion with some effect.
 * Potions are stackable.
 * 
 * @author Ches Burks
 *
 */
public class ItemPotion extends ItemStackable{
	public PotionEffect effect;

	/**
	 * Constructs a new {@link ItemPotion potion} with the given 
	 * {@link PotionEffect effect}.
	 * 
	 * @param effect The potions effect
	 */
	public ItemPotion (PotionEffect effect){
		this.effect = effect;
	}

	/**
	 * Returns the potions {@link PotionEffect effect}.
	 * 
	 * @return The effect
	 */
	public PotionEffect getEffect(){
		return this.effect;
	}

	/**
	 * Returns the maximum stack size.
	 * 
	 * @return The stack size
	 */
	@Override
	public int getMaxStackSize(){
		return 64;
	}

	/**
	 * Returns true if the other item is stackable and has the same potion effect.
	 * 
	 * @return True if the items can stack, false otherwise
	 */
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
