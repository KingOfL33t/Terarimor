package net.jordaria.event;

/**
 * A listener that handles events.
 */
public class EventListener {
	private final Listener listener;
	private final EventPriority priority;
	private final EventExecutor executor;

	/**
	 * Constructs a new {@link EventListener}.
	 * 
	 * @param listener The listener to call
	 * @param executor The executor for the events
	 * @param priority The priority of the listener
	 */
	public EventListener(final Listener listener, final EventExecutor executor, final EventPriority priority) {
		this.listener = listener;
		this.priority = priority;
		this.executor = executor;
	}

	/**
	 * Returns the {@link Listener listener}.
	 * 
	 * @return The listener
	 */
	public Listener getListener() {
		return listener;
	}

	/**
	 * Returns the {@link EventPriority priority}.
	 * 
	 * @return The priority
	 */
	public EventPriority getPriority() {
		return priority;
	}

	/**
	 * Calls the event executor.
	 * 
	 * @param event The event to execute
	 * @throws EventException If an error occurs during execution
	 */
	public void callEvent(final Event event) throws EventException {
		executor.execute(listener, event);
	}

}
