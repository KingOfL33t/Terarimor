package net.jordaria.math;

import net.jordaria.entity.EntityNPC;
import net.jordaria.world.World;

/**
 * Generates npcs for the game
 * 
 * @author Ches Burks
 */
public class NPCGeneration {
	Random random;
	NameGenerator nameGen;
	
	public NPCGeneration(){
		random = new Random();
		random.initializeGenerator((int) (Math.random()*1337));
		nameGen = new NameGenerator();
	}
	
	public EntityNPC generateNPC(World world){
		EntityNPC npc = new EntityNPC(world);
		npc.setName(nameGen.getCharacterNameNordic());
		npc.setCurrency(random.getIntBetween(0, 50));
		return npc;
	}
}
