package net.jordaria;

import net.jordaria.event.EventManager;
import net.jordaria.event.events.Tick;

/**
 * A thread that constantly fires tick events.
 * 
 * @author Ches Burks
 *
 */
public class Heart extends Thread {

	private EventManager evtManager;
	private boolean running;

	/**
	 * Constructs a new {@link Heart} with the given 
	 * {@link EventManager} to tick.
	 * 
	 * @param eventManager The event manager
	 */
	public Heart(EventManager eventManager){
		this.evtManager = eventManager;
		this.running = true;
	}

	/**
	 * Called automatically. If the thread is running, it will 
	 * call {@link #beat}.
	 */
	public void run() {
		while (running){
			beat();
		}
	}

	/**
	 * Fires a {@link Tick} event and then waits for a set time.
	 */
	public void beat(){
		evtManager.fireEvent(new Tick());
		try {
			Thread.sleep(16L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the thread from executing any code. 
	 * This should be called before stopping the thread 
	 * for thread safety.
	 */
	public void terminate(){
		running = false;
	}
}
