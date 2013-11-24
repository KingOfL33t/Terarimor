package net.jordaria.world;

public class ChunkCoordinates {
	public final int xPos;
	public final int yPos;
	public final int zPos;

	public ChunkCoordinates(int x, int y, int z){
		this.xPos = x;
		this.yPos = y;
		this.zPos = z;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public int getzPos() {
		return zPos;
	}

	public boolean matchCoordinates(int x, int y, int z){
		if (this.xPos == x && this.yPos == y && this.zPos == z){
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
		if (otherCC.matchCoordinates(this.xPos, this.yPos, this.zPos)){
			return true;
		}
		else{
			return false;
		}
	}
}
