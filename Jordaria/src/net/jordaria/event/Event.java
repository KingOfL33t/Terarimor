package net.jordaria.event;

/**
 * An abstract event to be extended.
 */
public abstract class Event {
	private String name;
	private final boolean async;
	
	/**
	 * Constructs a new synchronous {@link Event Event}.
	 */
	public Event() {
		this(false);
	}

	/**
	 * Constructs a new {@link Event Event}.
	 * 
	 * @param isAsync Whether the event is asynchronous
	 */
	public Event(boolean isAsync) {
		this.async = isAsync;
	}
	
	/**
	 * Returns the name of the event.
	 * 
	 * @return The events name (simple class name if no name is specified)
	 */
	public String getEventName() {
		if (name == null) {
			name = getClass().getSimpleName();
		}
		return name;
	}
	
	/**
	 * Returns the {@link HandlerList handler list}.
	 * 
	 * @return The handler list.
	 * 
	 */
	public abstract HandlerList getHandlers();

	/**
	 * Returns true if the event is asynchronous, otherwise false.
	 * 
	 * @return If the event is asynchronous
	 */
	public final boolean isAsynchronous() {
		return async;
	}

}
