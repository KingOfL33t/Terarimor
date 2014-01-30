package net.jordaria.entity;

import net.jordaria.world.World;

/**
 * A subclass of {@link EntityLiving EntityLiving} that contains a username.
 * 
 * @author Ches Burks
 *
 */
public class EntityPlayer extends EntityLiving{

	protected final String username;

	/**
	 * Constructs a new EntityPlayer in the given world with the 
	 * specified username.
	 * 
	 * @param world The world to spawn in
	 * @param name The username of the player
	 */
	public EntityPlayer(World world, String name) {
		super(world);
		this.username = name;
		this.inventory = new Inventory(64);
	}

	/**
	 * Returns the players username.
	 * 
	 * @return The username
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Performs any initialization
	 */
	protected void entityInit() {
		super.entityInit();
	}


}
