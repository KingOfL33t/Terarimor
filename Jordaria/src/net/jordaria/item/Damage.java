package net.jordaria.item;

import java.util.HashMap;
import java.util.Map;

/**
 * A map of {@link Element elements} and their respective damage value.
 * 
 * @author Ches Burks
 *
 */
public class Damage {
	public Map<Element, Integer> damageMap;
	
	/**
	 * Constructs a new {@link Damage}
	 */
	public Damage(){
		damageMap = new HashMap<Element, Integer>();
	}
	
	/**
	 * Adds a mapping to the damage map of the given 
	 * {@link Element element} and damgae.
	 * 
	 * @param element The element
	 * @param damage The damage
	 */
	public void addMapping(Element element, int damage){
		damageMap.put(element, damage);
	}
	
	/**
	 * Returns the damage mapped to the given {@link Element element}.
	 * If the element is not assigned to a value, then 
	 * zero is returned.
	 * 
	 * @param element The element for which to return damage
	 * @return The damage
	 */
	public int getDamage(Element element){
		if (damageMap.containsKey(element)){
			return damageMap.get(element);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Returns the {@link Map map} of {@link Element elements} to damage values.
	 * 
	 * @return The Map
	 */
	public Map<Element, Integer> getDamages(){
		return this.damageMap;
	}
}
