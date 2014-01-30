package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * A subclass of {@link EntityLiving EntityLiving}
 * 
 * @author Ches Burks
 */
public class EntityNPC extends EntityLiving{

	/**
	 * Constructs a new EntityNPC in the given world.
	 * 
	 * @param world The world to spawn in
	 */
	public EntityNPC(World world) {
		super(world);
	}

}
