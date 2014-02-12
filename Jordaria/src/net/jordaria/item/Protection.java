package net.jordaria.item;

import java.util.HashMap;
import java.util.Map;

/**
 * A map of {@link Element elements} and their respective protection value.
 * 
 * @author Ches Burks
 *
 */
public class Protection {
	public Map<Element, Integer> protectionMap;
	
	/**
	 * Constructs a new {@link Protection}
	 */
	public Protection(){
		this.protectionMap = new HashMap<Element, Integer>();
	}
	
	/**
	 * Adds a mapping to the protection map of the given 
	 * {@link Element element} and protection.
	 * 
	 * @param element The element
	 * @param protection The protection
	 */
	public void addMapping(Element element, int protection){
		protectionMap.put(element, protection);
	}
	
	/**
	 * Returns the protection mapped to the given {@link Element element}.
	 * If the element is not assigned to a value, then 
	 * zero is returned.
	 * 
	 * @param element The element for wich to return protection
	 * @return The protection against that element
	 */
	public int getProtection(Element element){
		if (protectionMap.containsKey(element)){
			return protectionMap.get(element);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Returns the {@link Map map} of {@link Element elements} to protection values.
	 * 
	 * @return The Map
	 */
	public Map<Element, Integer> getProtections(){
		return this.protectionMap;
	}
	
	/**
	 * Takes all values for elements this protects against and subtract the respective 
	 * value from that element in the supplied {@link Damage}. Any damage 
	 * that has not been fully negated (is still above zero) will be returned in the new damage.
	 * 
	 * @param damage The damage to modify
	 * @return The new damage
	 */
	public Damage modifyDamage(Damage damage){
		Damage output = new Damage();
		int elementalDamage;
		for (Element e: damage.getDamages().keySet()){
			elementalDamage = damage.getDamage(e) - this.getProtection(e);
			if (elementalDamage>0){
				damage.addMapping(e, elementalDamage);
			}
		}
		return output;
	}
}
