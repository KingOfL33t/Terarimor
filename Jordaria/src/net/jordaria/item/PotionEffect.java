package net.jordaria.item;

public enum PotionEffect {
	NOTHING(0, "potion.effect.nothing"),
	DAMAGE(1, "potion.effect.damage"),
	HEAL(2, "potion.effect.heal"),
	POISON(3, "potion.effect.poison"),
	CURE_POISON(4, "potion.effect.curepoison"),
	MANA(5, "potion.effect.mana");
	
	private int index;
	private String name;
	private PotionEffect(int index, String effectName){
		
	}
	public int getIndex(){
		return this.index;
	}
	public String getName(){
		return this.name;
	}
}
