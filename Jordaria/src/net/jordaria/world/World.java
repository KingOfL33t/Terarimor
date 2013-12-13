package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
	public Random rng = new Random();

	public List<Object> playerEntities = new ArrayList<Object>();
	public List<Object> loadedEntityList = new ArrayList<Object>();//Entities loaded in the world
	protected List<Object> unloadedEntityList = new ArrayList<Object>();

	public ChunkManager chunkManager;

	public String worldName;

	public World(String name){
		this.worldName = name;
		this.chunkManager = new ChunkManager(this);
	}

	public void updateEntities(){
	}
	public Chunk getChunkFromChunkCoords(int xPos, int yPos, int zPos)
	{
		return this.chunkManager.provideChunk(xPos, yPos, zPos);
	}
	

}
