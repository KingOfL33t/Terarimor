package net.jordaria.event;

/*
 * The program is shutting down
 */
public class ShuttingDown extends Event{

	private static final HandlerList handlers = new HandlerList();

	public ShuttingDown(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
