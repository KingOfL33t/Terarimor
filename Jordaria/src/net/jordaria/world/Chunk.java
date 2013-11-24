package net.jordaria.world;

import java.util.ArrayList;
import java.util.List;

import net.jordaria.Configuration;
import net.jordaria.Jordaria;
import net.jordaria.block.Block;
import net.jordaria.entity.Entity;

public class Chunk {
	private Block[][][] blocks;
	public ChunkCoordinates coordinates;
	private World world;
	public List<Entity> entitylist;

	public boolean isChunkLoaded;
	public boolean isEmpty;
	public boolean hasEntities;

	public void update(){

	}

	public Chunk(World theWorld, int xPos, int yPos, int zPos){
		world = theWorld;
		entitylist = new ArrayList<Entity>();
		this.coordinates = new ChunkCoordinates(xPos, yPos, zPos);
		blocks = new Block[Configuration.getCHUNK_SIZE()][Configuration.getCHUNK_SIZE()][Configuration.getCHUNK_SIZE()];
		for (int x = 0; x < Configuration.getCHUNK_SIZE(); x++){
			for (int y = 0; y< Configuration.getCHUNK_SIZE(); y++){
				for (int z = 0; z < Configuration.getCHUNK_SIZE(); z++){
					if (Math.random() < .5){
						blocks[x][y][z] = Block.stone;
					}
					else{
						blocks[x][y][z] = Block.grass;
					}

				}
			}
		}
		this.isChunkLoaded = true;

	}
	public Chunk makeEmptyChunk(){
		for (int x = 0; x < Configuration.getCHUNK_SIZE(); x++){
			for (int y = 0; y< Configuration.getCHUNK_SIZE(); y++){
				for (int z = 0; z < Configuration.getCHUNK_SIZE(); z++){
					blocks[x][y][z] = Block.air;
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
		boolean remove = false;
		if (this.entitylist.contains(tmpEntity)){
			remove = true;
		}
		if (remove){
			this.entitylist.remove(tmpEntity);
		}

	}

	public void addEntity(Entity theEntity)
	{
		this.hasEntities = true;
		int x = (int)Math.floor(theEntity.posX/Jordaria.config.CHUNK_SIZE);
		int y = (int)Math.floor(theEntity.posY/Jordaria.config.CHUNK_SIZE);
		int z = (int)Math.floor(theEntity.posZ/Jordaria.config.CHUNK_SIZE);

		if (x != this.coordinates.xPos || z != this.coordinates.zPos)
		{
			return;
		}


		if (y < 0)
		{
			y = 0;
		}


		theEntity.addedToChunk = true;
		theEntity.chunkCoordX = this.coordinates.xPos;
		theEntity.chunkCoordY = this.coordinates.yPos;
		theEntity.chunkCoordZ = this.coordinates.zPos;
		this.entitylist.add(theEntity);
	}


}
