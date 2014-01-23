package net.jordaria.item;

public enum PotionEffect {
	NOTHING(0, "Potion of Nothing"),
	DAMAGE(1, "Damage Potion"),
	HEAL(2, "Health Potion"),
	POISON(3, "Poison"),
	CURE_POISON(4, "Antidote"),
	MANA(5, "Mana Potion");
	
	private int index;
	private String name;
	private PotionEffect(int index, String effectName){
		this.index = index;
		this.name = effectName;
	}
	public int getIndex(){
		return this.index;
	}
	public String getName(){
		return this.name;
	}
}
