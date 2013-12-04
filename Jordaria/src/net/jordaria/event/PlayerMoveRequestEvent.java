package net.jordaria.event;

import net.jordaria.entity.EntityPlayer;

public class PlayerMoveRequestEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	
	public EntityPlayer player;
	
	public PlayerMoveRequestEvent(EntityPlayer player){//TODO add a direction
		this.player = player;
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

}
