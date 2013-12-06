package net.jordaria.event;

/*
 * The Event system has been started and is now running
 */
public class EventSystemStarted extends Event{
private static final HandlerList handlers = new HandlerList();
	
	public EventSystemStarted(){
		
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
