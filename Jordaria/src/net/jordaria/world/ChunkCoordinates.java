package net.jordaria.world;

public class ChunkCoordinates {
	public final int posX;
	public final int posY;

	public ChunkCoordinates(int x, int y){
		this.posX = x;
		this.posY = y;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}


	public boolean matchCoordinates(int x, int y){
		if (this.posX == x && this.posY == y){
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
		if (otherCC.matchCoordinates(this.posX, this.posY)){
			return true;
		}
		else{
			return false;
		}
	}
}
