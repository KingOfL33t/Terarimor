package net.jordaria.gui;

import java.util.ArrayList;

import net.jordaria.event.EventManager;
import net.jordaria.event.Listener;

/**
 * An object in the gui. These have children and can be rendered. 
 * All have a reference to the main event system and their parents/children.
 * 
 * @author Ches Burks
 *
 */
public class GuiObject implements Listener{

	private ArrayList<GuiObject> children;
	private GuiObject parent;
	private EventManager eventManager;
	
	/**
	 * Preforms initialization of the gui object. 
	 * This should be overwritten.
	 */
	public void init(){
		
	}
	
	public void setEventManager(EventManager eventManager){
		this.eventManager = eventManager;
	}
	
	/**
	 * Initializes the child object with the event manager, calls its 
	 * init method, and registers it as a listener with the event manager.
	 * It is then added to the children list.
	 * 
	 * @param child The child object to add
	 */
	public void addChild(GuiObject child){
		child.setEventManager(eventManager);
		child.init();
		try {
			eventManager.registerEventListeners(child);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.children.add(child);
	}
	
	
}
