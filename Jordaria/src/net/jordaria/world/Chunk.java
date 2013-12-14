package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.Configuration;
import net.jordaria.entity.Entity;

public class Chunk {
	private Tile[][] tiles;
	public ChunkCoordinates coordinates;
	private World world;
	public List<Entity> entitylist;

	public boolean isChunkLoaded;
	public boolean isEmpty;
	public boolean hasEntities;

	public Chunk(World theWorld, int xPos, int yPos, int zPos){
		world = theWorld;
		entitylist = new ArrayList<Entity>();
		this.coordinates = new ChunkCoordinates(xPos, yPos, zPos);
		tiles = new Tile[Configuration.CHUNK_SIZE][Configuration.CHUNK_SIZE];
		this.isChunkLoaded = true;

	}
	public Chunk makeEmptyChunk(){
		for (int x = 0; x < Configuration.CHUNK_SIZE; x++){
			for (int y = 0; y< Configuration.CHUNK_SIZE; y++){
				for (int z = 0; z < Configuration.CHUNK_SIZE; z++){
					tiles[x][y].setTileType(TileType.AIR);
				}
			}
		}
		this.isChunkLoaded = true;
		this.isEmpty = true;
		return this;
	}
	public boolean isEmpty(){
		return isEmpty;
	}

	public void onChunkUnload() {

	}

	public void removeEntity(Entity tmpEntity) {
		if (this.entitylist == null){
			return;
		}
		if (this.entitylist.contains(tmpEntity)){
			this.entitylist.remove(tmpEntity);
		}
		if (this.entitylist.size()==0){
			this.hasEntities = false;
		}

	}

	public void addEntity(Entity theEntity)
	{
		this.hasEntities = true;
		this.entitylist.add(theEntity);
	}


}
