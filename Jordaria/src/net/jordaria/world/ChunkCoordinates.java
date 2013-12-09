package net.jordaria.world;

public class ChunkCoordinates {
	public final int posX;
	public final int posY;
	public final int posZ;

	public ChunkCoordinates(int x, int y, int z){
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getPosZ() {
		return posZ;
	}

	public boolean matchCoordinates(int x, int y, int z){
		if (this.posX == x && this.posY == y && this.posZ == z){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null){ 
			return false;
		}
		if (other == this){ 
			return true;
		}
		if (!(other instanceof ChunkCoordinates)){
			return false;
		}
		ChunkCoordinates otherCC = (ChunkCoordinates)other;
		if (otherCC.matchCoordinates(this.posX, this.posY, this.posZ)){
			return true;
		}
		else{
			return false;
		}
	}
}
