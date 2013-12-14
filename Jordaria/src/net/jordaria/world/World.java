package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.math.Random;

public class World {
	public Random rng = new Random();

	public List<Object> playerEntities = new ArrayList<Object>();
	public List<Object> loadedEntityList = new ArrayList<Object>();

	public ChunkManager chunkManager;

	public String worldName;
	
	public Map mainMap;

	public World(String name){
		this.worldName = name;
		this.chunkManager = new ChunkManager(this);
		this.rng = new Random();
		rng.initializeGenerator((int)(Math.random()*1337));
	}

	public void updateEntities(){
	}
	public Chunk getChunkFromChunkCoords(int xPos, int yPos, int zPos)
	{
		return this.chunkManager.provideChunk(xPos, yPos, zPos);
	}
	

}
