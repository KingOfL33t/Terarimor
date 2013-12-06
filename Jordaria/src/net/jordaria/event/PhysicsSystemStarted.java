package net.jordaria.event;

/*
 * The physics system has been initialized and is now working
 */
public class PhysicsSystemStarted extends Event{
private static final HandlerList handlers = new HandlerList();
	
	public PhysicsSystemStarted(){
		
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
