package net.jordaria.event;

import net.jordaria.entity.Direction;
import net.jordaria.entity.Entity;

/**
 * Fired when an {@link Entity entity} has moved. This event contains the entity that moved, 
 * what direction it moved in, and how far.
 * 
 * @author Ches Burks
 */
public class EntityMove extends Event{
	private static final HandlerList handlers = new HandlerList();

	public Entity entity;
	public Direction direction;
	public double distanceMoved;

	/**
	 *  Constructs a new {@link EntityMove EntityMove} with the given 
	 * {@link Direction direction} and distance.
	 * 
	 * @param entity The entity that moved
	 * @param direction The direction it moved in
	 * @param distance How far it moved
	 */
	public EntityMove(Entity entity, Direction direction, double distance){		
		this.entity = entity;
		this.direction = direction;
		this.distanceMoved = distance;
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
