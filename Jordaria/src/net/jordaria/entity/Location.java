package net.jordaria.entity;

import net.jordaria.world.Chunk;
import net.jordaria.world.ChunkCoordinates;

public class Location {
	
	//The positions relative to the chunk
	public double posX;
	public double posY;
	public double posZ;
	
	public ChunkCoordinates chunkLocation;//The chunk location
	
	public Location(ChunkCoordinates chunkLoc, double xPos, double yPos, double zPos){
		this.chunkLocation = chunkLoc;
		this.posX = xPos;
		this.posY = yPos;
		this.posZ = zPos;
	}
	
	public Location (Chunk chunk, double xPos, double yPos, double zPos){
		this(chunk.coordinates,xPos,yPos,zPos);
	}
	
	public Location (int chunkPosX, int chunkPosY, int chunkPosZ, double xPos, double yPos, double zPos){
		this(new ChunkCoordinates(chunkPosX, chunkPosY, chunkPosZ),xPos,yPos,zPos);
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getPosZ() {
		return posZ;
	}

	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}

	public ChunkCoordinates getChunkLocation() {
		return chunkLocation;
	}

	public void setChunkLocation(ChunkCoordinates chunkLocation) {
		this.chunkLocation = chunkLocation;
	}
	public void setPositions(double posX, double posY, double posZ){
		this.posX = posZ;
		this.posY = posY;
		this.posZ = posZ;
	}
	
}
