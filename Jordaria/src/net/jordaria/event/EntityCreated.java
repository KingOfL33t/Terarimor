package net.jordaria.event;

import net.jordaria.entity.Entity;
import net.jordaria.entity.Location;

public class EntityCreated extends Event{
private static final HandlerList handlers = new HandlerList();
	
	Entity entity;
	Location location;
	
	public EntityCreated(Entity entity, Location loc){
		this.entity = entity;
		this.location = loc;
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
