package net.jordaria.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import net.jordaria.Configuration;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EntityMoveRequest;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventPriority;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.GraphicsSystemStarted;
import net.jordaria.event.Listener;
import net.jordaria.event.PhysicsSystemStarted;
import net.jordaria.event.ShuttingDown;
import net.jordaria.event.SoundSystemStarted;

/**
 * A console for showing processes and events that occur in the program.
 * What is shown is configurable.
 * 
 * @author Ches Burks
 *
 */
public class DebugConsole extends WindowAdapter implements Listener{

	private String window_title = "Debug Console";
	private int width = 300; 
	private int height = 200;

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Constructs a new debug console and sets up components.
	 */
	public DebugConsole(){
		frame = new JFrame(window_title);
		frame.setSize(width, height);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(2, 3, 2));
		textArea.setForeground(new Color(2,200,2));
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);

		frame.setVisible(true);
	}

	/**
	 * Adds the message to the last line of the window and removes the top line if it exceeds 
	 * the maximum line count.
	 * 
	 * @param message The message to append
	 */
	public void appendMessage(String message){
		this.textArea.append(message+"\n");

		if (this.textArea.getLineCount()>=150){
			int end;
			try {
				end = this.textArea.getLineEndOffset(0);
				this.textArea.replaceRange("", 0, end);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Appends the events message to the text area.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDebugMessage(DebugMessage event){
		this.appendMessage(event.getMessage());
	}

	/**
	 * If starting systems are set to display, 
	 * appends a message informing of the startup.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEventSystemStarted(EventSystemStarted event){
		if (Configuration.DEBUG_SHOW_STARTINGSYSTEMS){
			this.appendMessage("Event system started");
		}
	}
	
	/**
	 * If starting systems are set to display, 
	 * appends a message informing of the startup.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onGraphicsSystemStarted(GraphicsSystemStarted event){
		if (Configuration.DEBUG_SHOW_STARTINGSYSTEMS){
			this.appendMessage("Graphics system started");
		}
	}
	
	/**
	 * If starting systems are set to display, 
	 * appends a message informing of the startup.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPhysicsSystemStarted(PhysicsSystemStarted event){
		if (Configuration.DEBUG_SHOW_STARTINGSYSTEMS){
			this.appendMessage("Physics system started");
		}
	}
	
	/**
	 * If starting systems are set to display, 
	 * appends a message informing of the startup.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSoundSystemStarted(SoundSystemStarted event){
		if (Configuration.DEBUG_SHOW_STARTINGSYSTEMS){
			this.appendMessage("Sound system started");
		}
	}
	/**
	 * If moving events are set to display, 
	 * appends a message informing of the movement and direction.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityMoveRequest(EntityMoveRequest event){
		if (Configuration.DEBUG_SHOW_ENTITYMOVE){
		if (event.entity instanceof EntityPlayer){
			String direct = "("+event.direction.getAngle()+")";
			this.appendMessage("Player move requested! "+direct);
		}
		}
	}
	
	/**
	 * Removes all components, hides the frame, 
	 * and removes the reference to the frame for 
	 * garbage collection to clear out.
	 * 
	 * @param event The event that was fired
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onShutdown(ShuttingDown event){
		this.frame.removeAll();
		this.frame.setVisible(false);
		this.frame = null;
	}
}
