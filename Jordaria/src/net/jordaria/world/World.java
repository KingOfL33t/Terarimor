package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.event.EventManager;
import net.jordaria.event.events.MapChanged;
import net.jordaria.math.Random;

/**
 * A virtual world containing a generator and entities.
 * 
 * @author Ches Burks
 *
 */
public class World {
	public Random rng = new Random();

	public List<Object> playerEntities = new ArrayList<Object>();
	public List<Object> loadedEntityList = new ArrayList<Object>();

	public String worldName;
	
	public Map currentMap;
	
	public WorldGen worldGen;
	
	public EventManager eventManager;

	/**
	 * Construct a new world with the given name and {@link EventManager manager}.
	 * 
	 * @param name The name of the world
	 * @param manager The event manager to use
	 */
	public World(String name, EventManager manager){
		this.eventManager = manager;
		this.worldName = name;
		this.rng = new Random();
		rng.initializeGenerator((int)(Math.random()*1337));
		this.worldGen = new WorldGen();
	}
	
	/**
	 * Returns the current {@link Map map}.
	 * 
	 * @return The current map.
	 */
	public Map getCurrentMap(){
		return this.currentMap;
	}
	
	/**
	 * Sets the current map and fires a {@link MapChanged MapChanged} event.
	 * 
	 * @param newMap The new map to use
	 */
	public void setCurrentMap(Map newMap){
		this.currentMap = newMap;
		this.eventManager.fireEvent(new MapChanged(newMap, newMap.getWidth(), newMap.getHeight()));
	}
	
	/**
	 * Returns the {@link WorldGen world generator}.
	 * 
	 * @return The world generator
	 */
	public WorldGen getWorldGenerator(){
		return worldGen;
	}
	

}
