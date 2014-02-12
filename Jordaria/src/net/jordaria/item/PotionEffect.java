package net.jordaria.item;

/**
 * An enum of potion effects, their index and names.
 * 
 * @author Ches Burks
 *
 */
public enum PotionEffect {
	NOTHING(0, "Potion of Nothing"),
	DAMAGE(1, "Damage Potion"),
	HEAL(2, "Health Potion"),
	POISON(3, "Poison"),
	CURE_POISON(4, "Antidote"),
	MANA(5, "Mana Potion");
	
	private int index;
	private String name;
	/**
	 * Constructs a new {@link PotionEffect} with the given index 
	 * and name.
	 * 
	 * @param index The index of the effect
	 * @param effectName The effects name
	 */
	private PotionEffect(int index, String effectName){
		this.index = index;
		this.name = effectName;
	}
	/**
	 * Returns the index value of the effect.
	 * 
	 * @return The index of the effect
	 */
	public int getIndex(){
		return this.index;
	}
	/**
	 * Returns the name of the effect.
	 * 
	 * @return The effects name
	 */
	public String getName(){
		return this.name;
	}
}
