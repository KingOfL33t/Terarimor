package net.terarimor.event;

import net.terarimor.exception.EventException;

public interface EventExecutor {
	public void execute(Listener listener, Event event) throws EventException;
}
