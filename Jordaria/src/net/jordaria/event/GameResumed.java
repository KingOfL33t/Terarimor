package net.jordaria.event;

/*
 * The game has been unpaused
 */
public class GameResumed  extends Event{
private static final HandlerList handlers = new HandlerList();
	
	public GameResumed(){
		
	}
	
	@Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
