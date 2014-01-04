package net.jordaria.world;

import net.jordaria.math.Random;

public class WorldGen {
	Random random;
	public WorldGen(){
		random = new Random();
		random.initializeGenerator((int)(Math.random()*1337));
	}
	public void generateSolidFloor(Chunk chunk){
		chunk.setAllTiles(TileType.FLOOR);
	}
	public void generateMixedFloor(Chunk chunk){
		int x;
		int y;
		int size = chunk.getSize();
		int floorChance = 150;//increase this to increase the amount of normal floor (less than 5 and floor blocks dont spawn)
		//Tile[][] tiles = chunk.getTiles();
		//boolean[][] hasNeighbor = new boolean[size][size];
		boolean[][] isSpecial = new boolean[size][size];
		for (x=0; x < size; x++){
			for (y=0; y < size; y++){
				switch (random.getIntBetween(1, floorChance)){
				case 1: chunk.setTile(x, y, TileType.FLOOR_ROCKY);
				isSpecial[x][y]=true;
				break;
				case 2: chunk.setTile(x, y, TileType.FLOOR_CRACKED);
				isSpecial[x][y]=true;
				break;
				case 3: chunk.setTile(x, y, TileType.FLOOR_PLANT);
				isSpecial[x][y]=true;
				break;
				case 4: chunk.setTile(x, y, TileType.FLOOR_BROKEN);
				isSpecial[x][y]=true;
				break;
				case 5: chunk.setTile(x, y, TileType.FLOOR_BLOOD);
				isSpecial[x][y]=true;
				break;
				default: chunk.setTile(x, y, TileType.FLOOR);
				isSpecial[x][y]=false;
				}
			}
		}

		/*for (x=0; x < size; x++){
			for (y=0; y < size; y++){
				if (isSpecial[x][y]){
					if (x==0&&y==0){//upper left corner
						if (isSpecial[x+1][y]||isSpecial[x][y+1]||isSpecial[x+1][y+1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (x==0&&y==size-1){//lower left corner
						if (isSpecial[x+1][y]||isSpecial[x][y-1]||isSpecial[x+1][y-1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (x==size-1&&y==0){//upper right corner
						if (isSpecial[x-1][y]||isSpecial[x][y+1]||isSpecial[x-1][y+1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (x==size-1&&y==size-1){//lower right corner
						if (isSpecial[x-1][y]||isSpecial[x][y-1]||isSpecial[x-1][y-1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (x==0){//left edge, not a corner
						if (isSpecial[x][y-1]||isSpecial[x][y+1]||isSpecial[x+1][y]||isSpecial[x+1][y+1]||isSpecial[x+1][y-1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (x==size-1){//right edge, not a corner
						if (isSpecial[x][y-1]||isSpecial[x][y+1]||isSpecial[x-1][y]||isSpecial[x-1][y+1]||isSpecial[x-1][y-1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (y==0){//top edge, not a corner
						if (isSpecial[x-1][y]||isSpecial[x+1][y]||isSpecial[x][y+1]||isSpecial[x+1][y+1]||isSpecial[x-1][y+1]){
							hasNeighbor[x][y]=true;
						}
					}
					else if (y==size-1){//bottom edge, not a corner
						if (isSpecial[x-1][y]||isSpecial[x+1][y]||isSpecial[x][y-1]||isSpecial[x+1][y-1]||isSpecial[x-1][y-1]){
							hasNeighbor[x][y]=true;
						}
					}
					else{//somewhere in the middle, not an edge or corner
						if (isSpecial[x-1][y]||isSpecial[x+1][y]||isSpecial[x][y-1]||isSpecial[x][y+1]||isSpecial[x-1][y-1]||isSpecial[x-1][y+1]||isSpecial[x+1][y-1]||isSpecial[x+1][y+1]){
							hasNeighbor[x][y]=true;
						}
						else{
							hasNeighbor[x][y]=false;
						}
					}
				}
			}
		}
		for (x=0; x < size; x++){
			for (y=0; y < size; y++){
				if (hasNeighbor[x][y]){
					chunk.setTile(x, y, TileType.FLOOR);
				}
			}
		}*/

	}

}
