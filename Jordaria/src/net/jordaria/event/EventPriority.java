package net.jordaria.event;

/**
 * An enum of priorities for events. Listeners with higher priorities 
 * are called later, and can override the lower priority listeners.
 */
public enum EventPriority {
	LOWEST(0),
	LOW(1),
	NORMAL(2),
	HIGH(3),
	HIGHEST(4),
	MONITOR(5);

	private final int priority;
	
	/**
	 * Constructs a new {@link EventPriority}.
	 * @param priority
	 */
	private EventPriority(int priority){
		this.priority = priority;
	}
	
	/**
	 * Returns the priority of the event.
	 * 
	 * @return The priority
	 */
	public int getPriority(){
		return priority;
	}
}
