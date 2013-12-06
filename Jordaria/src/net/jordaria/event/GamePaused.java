package net.jordaria.event;

/*
 * The game has been paused
 */
public class GamePaused extends Event{
private static final HandlerList handlers = new HandlerList();
	
	public GamePaused(){
		
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
