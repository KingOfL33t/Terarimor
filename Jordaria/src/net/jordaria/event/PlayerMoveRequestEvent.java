package net.jordaria.event;

import net.jordaria.Direction;
import net.jordaria.entity.EntityPlayer;

public class PlayerMoveRequestEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	
	public EntityPlayer player;
	public Direction direction;
	
	public PlayerMoveRequestEvent(EntityPlayer player, Direction direction){
		this.player = player;
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
