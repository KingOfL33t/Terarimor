package net.jordaria.event;

/**
 * Fired when a human view has been attached, and is now working.
 * 
 * @author Ches Burks
 */
public class HumanViewAttached extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link HumanViewAttached}.
	 */
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
