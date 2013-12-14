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
}
