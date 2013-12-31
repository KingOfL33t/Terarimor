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
		int floorChance = 15;//increase this to increase the amount of normal floor (less than 5 and floor blocks dont spawn)
		for (x=0; x < chunk.getSize(); x++){
			for (y=0; y < chunk.getSize(); y++){
				switch (random.getIntBetween(1, floorChance)){
				case 1: chunk.setTile(x, y, TileType.FLOOR_ROCKY);
				break;
				case 2: chunk.setTile(x, y, TileType.FLOOR_CRACKED);
				break;
				case 3: chunk.setTile(x, y, TileType.FLOOR_PLANT);
				break;
				case 4: chunk.setTile(x, y, TileType.FLOOR_BROKEN);
				break;
				case 5: chunk.setTile(x, y, TileType.FLOOR_BLOOD);
				break;
				default: chunk.setTile(x, y, TileType.FLOOR);
				}
			}
		}
	}
}
