package net.jordaria.item;

import java.util.Map;

public class Protection {
	public Map<Element, Integer> protectionMap;
	
	public void addMapping(Element element, int protection){
		protectionMap.put(element, protection);
	}
	public int getProtection(Element element){
		if (protectionMap.containsKey(element)){
			return protectionMap.get(element);
		}
		else{
			return 0;
		}
	}
	public Map<Element, Integer> getProtections(){
		return this.protectionMap;
	}
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
