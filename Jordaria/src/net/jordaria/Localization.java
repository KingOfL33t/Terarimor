package net.jordaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import net.jordaria.event.Error;
import net.jordaria.event.LanguageChanged;

public class Localization {

	private Jordaria jd;

	private HashMap<String,String> mappings = new HashMap<String,String>();
	public boolean lockedForChanging = false;

	public Localization(Jordaria jordaria){
		this.jd = jordaria;
	}

	public void loadLanguage(){
		this.lockedForChanging = true;//lock it so it will not be accessed
		
		mappings.clear();
		
		BufferedReader reader = jd.fileIO.getBufferedReaderForFile(jd.gameSettings.homeDirectory.concat(jd.gameSettings.language+".lang"));
		if (reader!=null){
			String currentLine = "";
			String[] parts;
			try {
				while (reader.ready()){
					currentLine = reader.readLine();
					parts = currentLine.split("=");//split the line around the equals sign
					mappings.put(parts[0], parts[1]);//the left side is the universal and the right the translation
				}
			} catch (IOException e) {
				jd.eventManager.fireEvent(new Error("Error loading language"));
			}
		}
		this.lockedForChanging = false;//allow it to be accessed
		jd.eventManager.fireEvent(new LanguageChanged(jd.gameSettings.language));
	}
	public String getLocalizedName(String name){
		if (isLocked()){
			return "NULL";//the mappings is locked for
		}
		if (!mappings.containsKey(name)){
			return name;//the string does not exist in the mapping, so used the name given as a placeholder
		}
		return mappings.get(name);//the string exists, so return it
	}
	public boolean isLocked(){
		return lockedForChanging;
	}
}
