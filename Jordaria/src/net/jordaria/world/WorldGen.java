package net.jordaria.world;

import net.jordaria.math.Random;
import net.jordaria.physics.AxisAlignedBoundingBox;

/**
 * A class for generating maps and worlds.
 * 
 * @author Ches Burks
 *
 */
public class WorldGen {
	Random random;

	/**
	 * Constructs a new WorldGen and initializes the {@link Random RNG}.
	 */
	public WorldGen(){
		random = new Random();
		random.initializeGenerator((int)(Math.random()*1337));
	}

	/**
	 * Sets the {@link Map map's} floor to a mixed floor.
	 * 
	 * @param map The map to use
	 */
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

	/**
	 * Fills the {@link Map map} with a town.
	 * 
	 * @param map The map to use
	 */
	public void fillWithTown(Map map){
		generateMixedFloor(map);
		setOutsideWalls(map);
		int area = map.getWidth()*map.getHeight();
		int density = 20;//density in percent
		
		for (int i = 0; i < (area*density)/(100*28); i++){//28 is max area of a house
			if (!generateHouse(map)){
				i--;
			}
		}
	}

	/**
	 * Surrounds the outside of the {@link Map map} with walls.
	 * 
	 * @param map The map to use
	 */
	private void setOutsideWalls(Map map){
		int width = map.getWidth();
		int height = map.getHeight();
		int x = 0;
		int y = 0;
		for (x=0; x<width; x++){
			map.setTile(x, 0, TileType.WALL);
			map.setTile(x, height-1, TileType.WALL);
		}
		for (y=0; y<height; y++){
			map.setTile(0, y, TileType.WALL);
			map.setTile(width-1, y, TileType.WALL);
		}
	}

	/**
	 * Attempts to generate a house of a random size in the {@link Map map}. 
	 * Returns true if it succeeds, false otherwise.
	 * 
	 * @param map The map to use
	 * @return True on success, false otherwise
	 */
	private boolean generateHouse(Map map){
		Structure house = new Structure();

		int width = random.getIntBetween(3,7);
		while (width % 2 == 0){
			width = random.getIntBetween(3,7);
		}
		int height = random.getIntBetween(3,4);
		while (height % 2 != 0){
			height = random.getIntBetween(3,4);
		}

		Tile[][] houseTiles = new Tile[height][width];
		int x,y;

		for (y = 0; y < height; y++){
			for (x = 0; x < width; x++){
				houseTiles[y][x] = new Tile();
				if (y<=(height/2)){
					houseTiles[y][x].setTileType(TileType.ROOF);
				}
				else {
					houseTiles[y][x].setTileType(TileType.WALL);
				}
			}
		}
		houseTiles[height-1][width/2].setTileType(TileType.DOOR_N);

		house.setTiles(houseTiles);

		int locX = random.getIntBetween(0, map.getWidth());
		int locY = random.getIntBetween(0, map.getHeight());

		if (isValidPosition(map, house, locX, locY, true)){
			map.addStructure(house, locX, locY);
			return true;
		}


		return false;
	}

	/**
	 * Tests if the structure is valid in the given position. If it requires a perimeter, then 1 tile on 
	 * each side will need to be available as well.
	 * 
	 * @param map The map to test
	 * @param struct The structure to be tested
	 * @param posX The x pos in the map of the structures upper left corner
	 * @param posY The y pos in the map of the structures upper left corner
	 * @param requiresPerimeter If there should be a 1 tile perimeter
	 * @return If it is a valid position
	 */
	private boolean isValidPosition(Map map, Structure struct, int posX, int posY, boolean requiresPerimeter){
		//create an AABB with the maps dimensions
		AxisAlignedBoundingBox mapAABB = new AxisAlignedBoundingBox(0, 0, 0, map.getWidth()-1, map.getHeight()-1, 0);

		AxisAlignedBoundingBox structAABB = new AxisAlignedBoundingBox(posX, posY, 0, posX+struct.getWidth(), posY+struct.getHeight(), 0);

		if (requiresPerimeter){
			structAABB.expand(2, 2, 0);
		}

		if (!(structAABB.isInside(mapAABB))){
			return false;//Outside the map
		}

		for (AxisAlignedBoundingBox aabb : map.getCollisionBoxes()){
			if (aabb.intersectsWith(structAABB)){
				return false;//It intersected with something in the map
			}
		}
		return true;
	}

}
