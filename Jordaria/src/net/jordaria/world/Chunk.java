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
	public int size;

	public boolean isChunkLoaded;
	public boolean isEmpty;
	public boolean hasEntities;

	public Chunk(World theWorld, int xPos, int yPos){
		world = theWorld;
		entitylist = new ArrayList<Entity>();
		this.coordinates = new ChunkCoordinates(xPos, yPos);
		tiles = new Tile[Configuration.CHUNK_SIZE][Configuration.CHUNK_SIZE];
		this.size = Configuration.CHUNK_SIZE;
		int x;
		int y;
		for (x = 0; x < Configuration.CHUNK_SIZE; x++){
			for (y = 0; y < Configuration.CHUNK_SIZE; y++){
				tiles[x][y] = new Tile();
			}
		}
		this.isChunkLoaded = true;

	}
	public Chunk makeEmptyChunk(){
		int x;
		int y;
		for (x = 0; x < Configuration.CHUNK_SIZE; x++){
			for (y = 0; y < Configuration.CHUNK_SIZE; y++){
				tiles[x][y].setTileType(TileType.AIR);
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
	
	public void setAllTiles(TileType type){
		int x;
		int y;
		for (x = 0; x < Configuration.CHUNK_SIZE; x++){
			for (y = 0; y < Configuration.CHUNK_SIZE; y++){
				this.setTile(x, y, type);
			}
		}
	}
	public void setTile(int x, int y, TileType type){
		tiles[x][y].setTileType(type);;
	}
	public int getSize(){
		return size;
	}

}
