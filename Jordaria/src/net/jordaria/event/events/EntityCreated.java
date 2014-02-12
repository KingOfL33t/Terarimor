package net.jordaria.event.events;

import net.jordaria.entity.Entity;
import net.jordaria.entity.Location;
import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when an {@link Entity entity} is created.
 * 
 * @author Ches Burks
 */
public class EntityCreated extends Event{
	private static final HandlerList handlers = new HandlerList();

	Entity entity;
	Location location;

	/**
	 * Constructs a new {@link EntityCreated} with the given 
	 * {@link Entity entity} and {@link Location location}.
	 * 
	 * @param entity The entity created
	 * @param loc Where the entity is
	 */
	public EntityCreated(Entity entity, Location loc){
		this.entity = entity;
		this.location = loc;
	}

	/**
	 * Returns the {@link HandlerList handler list}.
	 * 
	 * @return The handler list.
	 * 
	 */
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Returns the {@link HandlerList handler list}.
	 * 
	 * @return The handler list.
	 * 
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
