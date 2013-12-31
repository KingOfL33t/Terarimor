package net.jordaria.world;

public enum TileType {
	AIR(0),
	FLOOR(1),
	FLOOR_ROCKY(2),
	FLOOR_CRACKED(3),
	FLOOR_BROKEN(4),
	FLOOR_BLOOD(5),
	FLOOR_PLANT(6),
	WALL_N(7),
	WALL_E(8),
	WALL_S(9),
	WALL_W(10),
	WALL_NE(11),
	WALL_NS(12),
	WALL_NW(13),
	WALL_NEW(14),
	WALL_NES(15),
	WALL_NSW(16),
	WALL_NESW(17),
	WALL_ES(18),
	WALL_EW(19),
	WALL_ESW(20),
	WALL_SW(21),
	LEDGE_N(22),
	LEDGE_NW(23),
	LEDGE_NE(24),
	LEDGE_E(25),
	LEDGE_W(26),
	DOOR_N(27),
	DOOR_E(28),
	DOOR_S(29),
	DOOR_W(30),
	STAIRS_N(31),
	STAIRS_E(32),
	STAIRS_S(33),
	STAIRS_W(34),
	ERROR(-1);
	private int tileID;
	private TileType(int id){
		this.tileID = id;
	}
	public int getID(){
		return tileID;
	}
}
