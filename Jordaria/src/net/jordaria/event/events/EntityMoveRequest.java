package net.jordaria.event.events;

import net.jordaria.entity.Direction;
import net.jordaria.entity.Entity;
import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when an interface requests that an {@link Entity entity} move. The direction is the actual direction to move,
 * in reference to the world.
 * 
 * @author Ches Burks
 */
public class EntityMoveRequest extends Event{
	private static final HandlerList handlers = new HandlerList();


	public Entity entity;
	public Direction direction;

	/**
	 * Constructs a new {@link EntityMoveRequest EntityMoveRequest} 
	 * for the given {@link Entity entity} and {@link Direction direction}.
	 * @param entity
	 * @param direction
	 */
	public EntityMoveRequest(Entity entity, Direction direction){		
		this.entity = entity;
		this.direction = direction;
	}
	
	/**
	 * Returns the {@link Direction direction} to move in
	 * 
	 * @return The direction
	 */
	public Direction getDirection(){
		return this.direction;
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
