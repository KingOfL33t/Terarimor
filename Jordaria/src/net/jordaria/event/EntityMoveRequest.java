package net.jordaria.event;

import net.jordaria.entity.Direction;
import net.jordaria.entity.Entity;

/*
 * The user interface requested that the player move. The direction is the actual direction to move,
 * in reference to the world, not the direction relative to the players current position
 */
public class EntityMoveRequest extends Event{
	private static final HandlerList handlers = new HandlerList();
	
	
	public Entity entity;
	public Direction direction;
	
	public EntityMoveRequest(Entity entity, Direction direction){		
		this.entity = entity;
		this.direction = direction;
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
