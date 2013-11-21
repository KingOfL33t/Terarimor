package net.jordaria.event;

import net.jordaria.exception.EventException;

public interface EventExecutor {
	public void execute(Listener listener, Event event) throws EventException;
}
