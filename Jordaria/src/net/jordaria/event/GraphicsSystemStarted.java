package net.jordaria.event;

/*
 * The graphics system has been initialized and is now running
 */
public class GraphicsSystemStarted extends Event{
private static final HandlerList handlers = new HandlerList();
	
	public GraphicsSystemStarted(){
		
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
