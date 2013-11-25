package net.jordaria.event;

public abstract class Event {
	private String name;
	private final boolean async;//Whether or not the event is asynchronous

	public Event() {
		this(false);
	}

	public Event(boolean isAsync) {
		this.async = isAsync;
	}

	public String getEventName() {
		if (name == null) {
			name = getClass().getSimpleName();
		}
		return name;
	}

	public abstract HandlerList getHandlers();

	public final boolean isAsynchronous() {
		return async;
	}

}
