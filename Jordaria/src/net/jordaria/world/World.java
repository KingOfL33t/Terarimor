package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.event.EventManager;
import net.jordaria.event.MapChanged;
import net.jordaria.math.Random;

public class World {
	public Random rng = new Random();

	public List<Object> playerEntities = new ArrayList<Object>();
	public List<Object> loadedEntityList = new ArrayList<Object>();

	public ChunkManager chunkManager;

	public String worldName;
	
	public Map currentMap;
	
	public WorldGen worldGen;
	
	public EventManager eventManager;

	public World(String name, EventManager manager){
		this.eventManager = manager;
		this.worldName = name;
		this.chunkManager = new ChunkManager(this);
		this.rng = new Random();
		rng.initializeGenerator((int)(Math.random()*1337));
		this.worldGen = new WorldGen();
	}

	public void updateEntities(){
	}
	public Chunk getChunkFromChunkCoords(int xPos, int yPos)
	{
		return this.chunkManager.provideChunk(xPos, yPos);
	}
	public Map getCurrentMap(){
		return this.currentMap;
	}
	public void setCurrentMap(Map newMap){
		this.currentMap = newMap;
		this.eventManager.fireEvent(new MapChanged(newMap.getWidth(), newMap.getHeight()));
	}
	public WorldGen getWorldGenerator(){
		return worldGen;
	}
	

}
