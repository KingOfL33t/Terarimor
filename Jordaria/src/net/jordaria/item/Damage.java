package net.jordaria.item;

import java.util.HashMap;
import java.util.Map;

public class Damage {
	public Map<Element, Integer> damageMap;
	public Damage(){
		damageMap = new HashMap<Element, Integer>();
	}
	public void addMapping(Element element, int damage){
		damageMap.put(element, damage);
	}
	public int getDamage(Element element){
		if (damageMap.containsKey(element)){
			return damageMap.get(element);
		}
		else{
			return 0;
		}
	}
}
