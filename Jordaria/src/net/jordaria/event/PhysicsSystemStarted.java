package net.jordaria.event;

/**
 * Fired when the physics system has been initialized and is now working.
 * 
 * @author Ches Burks
 */
public class PhysicsSystemStarted extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link PhysicsSystemStarted}.
	 */
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
