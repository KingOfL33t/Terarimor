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
		for (x=0; x < size; x++){
			for (y=0; y < size; y++){
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
	public void generateMixedFloor(Map map){
		int x;
		int y;
		int width = map.getWidth();
		int height = map.getHeight();
		int floorChance = 150;//increase this to increase the amount of normal floor (less than 5 and floor blocks dont spawn)
		for (x=0; x < width; x++){
			for (y=0; y < height; y++){
				switch (random.getIntBetween(1, floorChance)){
				case 1: map.setTile(x, y, TileType.FLOOR_ROCKY);
				break;
				case 2: map.setTile(x, y, TileType.FLOOR_CRACKED);
				break;
				case 3: map.setTile(x, y, TileType.FLOOR_PLANT);
				break;
				case 4: map.setTile(x, y, TileType.FLOOR_BROKEN);
				break;
				case 5: map.setTile(x, y, TileType.FLOOR_BLOOD);
				break;
				default: map.setTile(x, y, TileType.FLOOR);
				}
			}
		}
	}
	
	public void fillWithTown(Map map){
		//map.setAllTiles(TileType.FLOOR);
		generateMixedFloor(map);
		setOutsideWalls(map);
	}
	private void setOutsideWalls(Map map){
		int width = map.getWidth();
		int height = map.getHeight();
		int x = 0;
		int y = 0;
		/*for (x=1; x<width-1; x++){
			map.setTile(x, 0, TileType.WALL_S);
			map.setTile(x, height-1, TileType.WALL_N);
		}
		for (y=1; y<height-1; y++){
			map.setTile(0, y, TileType.WALL_E);
			map.setTile(width-1, y, TileType.WALL_W);
		}
		map.setTile(0, 0, TileType.WALL_ES);
		map.setTile(width-1, 0, TileType.WALL_SW);
		map.setTile(0, height-1, TileType.WALL_NE);
		map.setTile(width-1, height-1, TileType.WALL_NW);*/
		for (x=0; x<width; x++){
			map.setTile(x, 0, TileType.WALL);
			map.setTile(x, height-1, TileType.WALL);
		}
		for (y=0; y<height; y++){
			map.setTile(0, y, TileType.WALL);
			map.setTile(width-1, y, TileType.WALL);
		}
	}

}
