package net.jordaria.math;

import net.jordaria.item.ArmorSlot;
import net.jordaria.item.Damage;
import net.jordaria.item.Element;
import net.jordaria.item.Item;
import net.jordaria.item.ItemArmor;
import net.jordaria.item.ItemPotion;
import net.jordaria.item.ItemWeapon;
import net.jordaria.item.Lore;
import net.jordaria.item.PotionEffect;
import net.jordaria.item.Protection;
import net.jordaria.item.Quality;
import net.jordaria.text.Color;
import net.jordaria.text.ColoredText;

public class ItemGeneration {
	Random random;
	public ItemGeneration(){
		random = new Random();
		random.initializeGenerator((int)(Math.random()*1337));
	}
	public Item generateRandomItem(){
		switch (random.getIntBetween(0, 2)){
		case 0: return generateWeapon();
		case 1: return generateArmor();
		case 3: return generatePotion();
		default: return generateWeapon();
		}
	}
	public ItemWeapon generateWeapon(){
		ItemWeapon weapon = new ItemWeapon();
		weapon.setQuality(getRandomQuality());
		weapon.setDamage(generateDamage(weapon));
		weapon.setLore(generateLore());
		weapon.setItemName(generateWeaponName(weapon));
		return weapon;
	}

	public ItemArmor generateArmor(){
		ItemArmor armor = new ItemArmor();
		armor.setSlot(getRandomSlot());
		armor.setQuality(getRandomQuality());
		armor.setLore(generateLore());
		armor.setProtection(generateProtection(armor));
		armor.setItemName(generateArmorName(armor));
		return armor;
	}

	public ItemPotion generatePotion(){
		ItemPotion potion = new ItemPotion(getRandomPotionEffect());
		potion.setItemName(new ColoredText(potion.getEffect().getName(), Quality.NORMAL.getColor()));
		return potion;
	}

	//generate a random name for a weapon
	private ColoredText generateWeaponName(ItemWeapon weapon){
		//TODO finish me
		String name = "";
		
		name += "Sword of ";
		Element highestElement = getHighestElement(weapon.getDamage());
		name +=highestElement.getName();
		return new ColoredText(name, weapon.getQuality().getColor());
	}

	//generate a random name for an armor piece, based on the 
	//armor slot and elements
	//Input must have an armor slot picked or it will not generate accurately
	private ColoredText generateArmorName(ItemArmor item){
		String slotName = "Armor";
		String name = "";

		//prefix
		if (item.getSlot()==ArmorSlot.BOOTS){
			slotName = "Boots";
		}
		if (item.getSlot()==ArmorSlot.CAPE){
			slotName = "Cape";
		}
		if (item.getSlot()==ArmorSlot.CHESTPLATE){
			slotName = "Chestplate";
		}
		if (item.getSlot()==ArmorSlot.HELMET){
			slotName = "Helmet";
		}
		if (item.getSlot()==ArmorSlot.LEGGINGS){
			slotName = "Leggings";
		}
		if (item.getSlot()==ArmorSlot.NECKLACE){
			slotName = "Necklace";
		}
		if (item.getSlot()==ArmorSlot.RING){
			slotName = "Ring";
		}

		name += slotName;
		name += " of ";

		Element highestElement = getHighestElement(item.getProtection());
		
		name +=highestElement.getName();

		return new ColoredText(name, item.getQuality().getColor());
	}

	//Creates random lore
	private Lore generateLore(){
		//TODO finish me
		Lore lore = new Lore("Test", getRandomColor());
		return lore;
	}
	private Color getRandomColor(){
		Color[] colors = Color.values();
		return colors[random.getIntBetween(0, colors.length-1)];
	}
	private Quality getRandomQuality(){
		Quality[] qualities = Quality.values();
		return qualities[random.getIntBetween(0, qualities.length-1)];
	}
	private Element getRandomElement(){
		Element[] elements = Element.values();
		return elements[random.getIntBetween(0, elements.length-1)];
	}
	private ArmorSlot getRandomSlot(){
		ArmorSlot[] slots = ArmorSlot.values();
		return slots[random.getIntBetween(0, slots.length)];
	}
	private PotionEffect getRandomPotionEffect(){
		PotionEffect[] effects = PotionEffect.values();
		return effects[random.getIntBetween(0, effects.length)];
	}
	//create a random damage, based on quality of the weapon
	private Damage generateDamage(ItemWeapon weapon){
		Damage damage = new Damage();
		int types = 0;//how many types of damage to add to the weapon

		if (random.nextBoolean(weapon.getQuality().getFirstTypeChance())){
			types++;
			if (random.nextBoolean(weapon.getQuality().getSecondTypeChance())){
				types++;
				if (random.nextBoolean(weapon.getQuality().getThirdTypeChance())){
					types++;
					if (random.nextBoolean(weapon.getQuality().getFourthTypeChance())){
						types++;//Messy nested ifs, but it works
					}
				}
			}
		}
		damage.addMapping(Element.NORMAL, random.getIntBetween(1, weapon.getQuality().getNormalPower()));//add the normal damage
		for (int i = 0; i<=types; i++){//add the elemental damages
			damage.addMapping(getRandomElement(), random.getIntBetween(1, weapon.getQuality().getNormalPower()));
		}
		return damage;
	}
	//create a random protection, based on the quality of the armor
	private Protection generateProtection(ItemArmor armor){
		Protection protection = new Protection();
		int types = 0;//how many types of damage to add to the weapon

		if (random.nextBoolean(armor.getQuality().getFirstTypeChance())){
			types++;
			if (random.nextBoolean(armor.getQuality().getSecondTypeChance())){
				types++;
				if (random.nextBoolean(armor.getQuality().getThirdTypeChance())){
					types++;
					if (random.nextBoolean(armor.getQuality().getFourthTypeChance())){
						types++;//Messy nested ifs, but it works
					}
				}
			}
		}
		protection.addMapping(Element.NORMAL, armor.getQuality().getNormalPower());//add the normal damage
		for (int i = 0; i<=types; i++){//add the elemental damages
			protection.addMapping(getRandomElement(), random.getIntBetween(1, armor.getQuality().getNormalPower()));
		}
		return protection;
	}
	private Element getHighestElement(Protection protection){
		Element highest = Element.NORMAL;
		int highestDamage = 0;
		for (Element e: protection.getProtections().keySet()){
			if (protection.getProtection(e)>highestDamage){
				highestDamage = protection.getProtection(e);
				highest = e;
			}
		}
		return highest;
	}
	private Element getHighestElement(Damage damage){
		Element highest = Element.NORMAL;
		int highestDamage = 0;
		for (Element e: damage.getDamages().keySet()){
			if (damage.getDamage(e)>highestDamage){
				highestDamage = damage.getDamage(e);
				highest = e;
			}
		}
		return highest;
	}
}
