package net.jordaria.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

//Stores handlers per event. Based on lahwran's fevents.
public class HandlerList {

	//Handler array. This field being an array is the key to the system's speed.
	private volatile EventListener[] handlers = null;

	/*
	 * * Dynamic handler lists. These change when register() and unregister() 
	 * and are automatically baked to the handlers array any time they have changed.
	 */
	private final EnumMap<EventPriority, ArrayList<EventListener>> handlerslots;

	//List of all HandlerLists which have been created, for use in bakeAll()

	private static ArrayList<HandlerList> allLists = new ArrayList<HandlerList>();


	/*
	 * * Bake all handler lists. Best used just after all normal event
	 * registration is complete.
	 */
	public static void bakeAll() {
		synchronized (allLists) {
			for (HandlerList h : allLists) {
				h.bake();
			}
		}
	}

	public static void unregisterAll() {
		synchronized (allLists) {
			for (HandlerList h : allLists) {
				synchronized (h) {
					for (List<EventListener> list : h.handlerslots.values()) {
						list.clear();
					}
					h.handlers = null;
				}
			}
		}
	}

	/*
	 * * Unregister a specific listener from all handler lists.
	 * listener is the listener to unregister
	 */
	public static void unregisterAll(Listener listener) {
		synchronized (allLists) {
			for (HandlerList h : allLists) {
				h.unregister(listener);
			}
		}
	}

	/*
	 * * Create a new handler list and initialize using an EventPriority 
	 * TheHandlerList is then added to meta-list for use in bakeAll()
	 */
	public HandlerList() {
		handlerslots = new EnumMap<EventPriority, ArrayList<EventListener>>(EventPriority.class);
		for (EventPriority o : EventPriority.values()) {
			handlerslots.put(o, new ArrayList<EventListener>());
		}
		synchronized (allLists) {
			allLists.add(this);
		}
	}

	/*
	 * * Register a new listener in this handler list
	 */
	public synchronized void register(EventListener listener) {
		if (handlerslots.get(listener.getPriority()).contains(listener))
			throw new IllegalStateException(
					"This listener is already registered to priority "
							+ listener.getPriority().toString());
		handlers = null;
		handlerslots.get(listener.getPriority()).add(listener);
	}

	/*
	 * * Register a collection of new listeners in this handler list
	 */
	public void registerAll(Collection<EventListener> listeners) {
		for (EventListener listener : listeners) {
			register(listener);
		}
	}

	/*
	 * * Remove a listener from a specific order slot
	 */
	public synchronized void unregister(EventListener listener) {
		if (handlerslots.get(listener.getPriority()).remove(listener)) {
			handlers = null;
		}
	}


	/*
	 * * Remove a specific listener from this handler 
	 */
	public synchronized void unregister(Listener listener) {
		boolean changed = false;
		for (List<EventListener> list : handlerslots.values()) {
			for (ListIterator<EventListener> i = list.listIterator(); i.hasNext();) {
				if (i.next().getListener().equals(listener)) {
					i.remove();
					changed = true;
				}
			}
		}
		if (changed)
			handlers = null;
	}

	/*     * Bake HashMap and ArrayLists to 2d array - does nothing if not necessary    */    
	public synchronized void bake() {        
		if (handlers != null) return; // don't re-bake when still valid        
		List<EventListener> entries = new ArrayList<EventListener>();        
		for (Entry<EventPriority, ArrayList<EventListener>> entry : handlerslots.entrySet()) {
			entries.addAll(entry.getValue());
		}
		handlers = entries.toArray(new EventListener[entries.size()]);
	}

	//Get the baked registered listeners associated with this handler list
	public EventListener[] getRegisteredListeners() {        
		EventListener[] handlers;
		while ((handlers = this.handlers) == null) bake(); // This prevents fringe cases of returning null
		return handlers;
	}


	/*Get a list of all handler lists for every event type
	 *returns the list of all handler lists     
	*/   
	@SuppressWarnings("unchecked")
	public static ArrayList<HandlerList> getHandlerLists() {
		synchronized (allLists) {
			return (ArrayList<HandlerList>) allLists.clone();
		}
	}  

}

