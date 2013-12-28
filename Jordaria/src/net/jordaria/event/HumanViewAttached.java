package net.jordaria.event;

/*
 * A human view has been attached, and is now working
 */
public class HumanViewAttached extends Event{
	private static final HandlerList handlers = new HandlerList();

	public HumanViewAttached(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
