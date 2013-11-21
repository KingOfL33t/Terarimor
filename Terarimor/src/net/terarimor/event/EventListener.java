package net.terarimor.event;

import net.terarimor.exception.EventException;

public class EventListener {
	private final Listener listener;
	private final EventPriority priority;
	private final EventExecutor executor;
	private final boolean ignoreCancelled;

	public EventListener(final Listener listener, final EventExecutor executor, final EventPriority priority, final boolean ignoreCancelled) {
		this.listener = listener;
		this.priority = priority;
		this.executor = executor;
		this.ignoreCancelled = ignoreCancelled;
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
		if (event instanceof Cancellable) {
			if (((Cancellable) event).isCancelled() && isIgnoringCancelled()) {
				return;
			}
		}
		executor.execute(listener, event);
	}


	 //Whether this listener accepts cancelled events. returns true when ignoring cancelled events

	public boolean isIgnoringCancelled() {
		return ignoreCancelled;
	}

}
