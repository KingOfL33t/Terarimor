package net.jordaria.world;

import net.jordaria.Jordaria;

public class Dungeon {
	public String name;
	public int depth;
	public Jordaria jd;
	
	public Dungeon(Jordaria jordaria, int depth){
		this.depth = depth;
		this.jd = jordaria;
	}
	
	public void generateLevel(int level){
		Map map = new Map(jd.gameSettings.getMaxDungeonSize(), jd.gameSettings.getMaxDungeonSize());
		map.setAllTiles(TileType.AIR);
		
	}
}
