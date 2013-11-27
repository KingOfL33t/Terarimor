package net.jordaria.event;

import java.lang.reflect.Method;

import net.jordaria.exception.EventException;

public class EventManager {

	public void registerEvent(final Class<? extends Event> type, Listener listener) throws Exception{
		EventExecutor tmpExecutor = new EventExecutor() {
			public void execute(Listener listener, Event event) throws EventException {
				try {
					if (!type.isAssignableFrom(event.getClass())) {
						return;
					}
				} catch (Throwable t) {
					throw new EventException(t);
				}
			}
		};
		getEventListeners(type).register(new EventListener(listener, tmpExecutor, EventPriority.NORMAL));
	}

	private HandlerList getEventListeners(Class<? extends Event> type) throws Exception {
		try {
			Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");//get the gethandlerlist method
			method.setAccessible(true);//eh, security? we have a try/catch for that :P
			return (HandlerList) method.invoke(null);//get the handler list from the class
		} catch (Exception e) {
			throw new Exception(e.getMessage(),e.getCause());//oops
		}
	}


	private Class<? extends Event> getRegistrationClass(Class<? extends Event> eventClass) throws Exception {
		try {
			eventClass.getDeclaredMethod("getHandlerList");
			return eventClass;
		} catch (NoSuchMethodException e) {
			if (eventClass.getSuperclass() != null//super is not null
					&& !eventClass.getSuperclass().equals(Event.class)//and its an event subclass
					&& Event.class.isAssignableFrom(eventClass.getSuperclass())) {//and it should be assignable
				return getRegistrationClass(eventClass.getSuperclass().asSubclass(Event.class));
			} else {
				throw new Exception("Unable to find handler list for event " + eventClass.getName());//they dont exist
			}
		}
	}

	public void fireEvent(Event event){
		HandlerList handlers = event.getHandlers();
		EventListener[] listeners = handlers.getRegisteredListeners();
		for (EventListener registration : listeners) {
			try {
				registration.callEvent(event);
			} catch (EventException e) {
				;//should probably do something here, but its the event system. 
				//soooo if this is broken we are screwed for reporting errors.
				//perhaps implement a log system for these cases
			}
		}
	}

}
