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
		Map map = new Map(jd.getGameSettings().getMaxDungeonSize(), jd.getGameSettings().getMaxDungeonSize());
		map.setAllTiles(TileType.AIR);
		
	}
}
