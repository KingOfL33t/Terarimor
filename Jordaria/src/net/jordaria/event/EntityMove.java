package net.jordaria.event;

import net.jordaria.entity.Direction;
import net.jordaria.entity.Entity;

/*
 * An entity has moved. This event contains the entity that moved, what direction it moved in, and how far
 */
public class EntityMove extends Event{
	private static final HandlerList handlers = new HandlerList();

	public Entity entity;
	public Direction direction;
	public double distanceMoved;

	public EntityMove(Entity entity, Direction direction, double distance){		
		this.entity = entity;
		this.direction = direction;
		this.distanceMoved = distance;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}

}
