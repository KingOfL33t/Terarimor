package net.jordaria.world;

/**
 * An enum of different types of tiles and the associated id.
 * 
 * @author Ches Burks
 *
 */
public enum TileType {
	AIR(0,"air"),
	FLOOR(1,"floor"),
	FLOOR_ROCKY(2,"floorRocks"),
	FLOOR_CRACKED(3,"floorCracked"),
	FLOOR_BROKEN(4,"floorBroken"),
	FLOOR_BLOOD(5,"floorBloody"),
	FLOOR_PLANT(6,"floorPlant"),
	WALL(7,"walls", true),
	LEDGE_N(8,"ledgeN", true),
	LEDGE_NW(9,"ledgeNW", true),
	LEDGE_NE(10,"ledgeNE", true),
	LEDGE_E(11,"ledgeE", true),
	LEDGE_W(12,"ledgeW", true),
	DOOR_N(13,"doorN"),
	DOOR_E(14,"doorE"),
	DOOR_S(15,"doorS"),
	DOOR_W(16,"doorW"),
	STAIRS_N(17,"stairsN"),
	STAIRS_E(18,"stairsE"),
	STAIRS_S(19,"stairsS"),
	STAIRS_W(20,"stairsW"),
	ROOF(21,"roof", true),
	ERROR(-1,"error");
	
	private int tileID;
	private boolean isSolid;
	private String name;
	
	/**
	 * Constructs a new {@link TileType} with the given ID.
	 * 
	 * @param id The ID to use
	 * @param name The name of the tile
	 */
	private TileType(int id, String name){
		this.tileID = id;
		this.isSolid = false;
		this.name = name;
	}
	
	/**
	 * Constructs a new {@link TileType} with the given ID. 
	 * The tile may also be solid.
	 * 
	 * @param id The ID to use
	 * @param name The name of the tile
	 * @param isSolid If the tile causes collision
	 */
	private TileType(int id, String name, boolean isSolid){
		this.tileID = id;
		this.isSolid = isSolid;
		this.name = name;
	}
	
	/**
	 * Returns the ID of the tile.
	 * 
	 * @return The ID
	 */
	public int getID(){
		return tileID;
	}
	
	/**
	 * Returns true if the tile causes collision.
	 * 
	 * @return True if it is solid, false otherwise
	 */
	public boolean isSolid(){
		return isSolid;
	}
	
	/**
	 * Returns the name of the tile (same as the texture name).
	 * 
	 * @return The tiles name
	 */
	public String getName(){
		return name;
	}
	
	
}
