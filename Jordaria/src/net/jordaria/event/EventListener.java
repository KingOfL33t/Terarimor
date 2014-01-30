package net.jordaria.event;


public class EventListener {
	private final Listener listener;
	private final EventPriority priority;
	private final EventExecutor executor;

	public EventListener(final Listener listener, final EventExecutor executor, final EventPriority priority) {
		this.listener = listener;
		this.priority = priority;
		this.executor = executor;
	}

	public Listener getListener() {
		return listener;
	}

	public EventPriority getPriority() {
		return priority;
	}

	/**
	 * Calls the event executor throws EventException If an event handler throws an exception.
	 */
	public void callEvent(final Event event) throws EventException {
		executor.execute(listener, event);
	}

}
