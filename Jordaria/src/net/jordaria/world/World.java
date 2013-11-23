package net.jordaria.world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class World {
	public Random rng = new Random();
	
	public List playerEntities = new ArrayList();
	
	 protected Set activeChunkSet = new HashSet();

}
