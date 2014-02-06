package net.jordaria.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.jordaria.Jordaria;
import net.jordaria.event.Error;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;
import net.jordaria.event.Tick;

/**
 * The main window for the program. 
 * This is the swing version, as opposed to the opengl window.
 * 
 * @author Ches Burks
 *
 */
public class SwingMainWindow extends WindowAdapter implements Listener, KeyListener, ActionListener{
	//This windows frame
	JFrame frame;
	//The menu bar for the window
	JMenuBar menuBar;
	JMenu menuFile;//The file menu
	JMenuItem menuItemFileExit;//File.exit menu item.
	JMenu menuSettings;//The settings menu
	JMenu menuDebug;//The debug menu. Disabled if debug is not active
	//The text area
	ScrollingTextArea textArea;
	//The area for displaying the map
	MapArea mapArea;
	//A reference to the main jordaria class
	Jordaria jordaria;


	/**
	 * Constructs a new {@link SwingMainWindow} with a reference 
	 * to the {@link Jordaria main program}.
	 * 
	 * @param jd The reference to the main program
	 */
	public SwingMainWindow(Jordaria jd){
		this.jordaria = jd;
		this.init();
	}
	public void init(){
		//frame
		frame = new JFrame(Jordaria.config.getWindow_title());
		frame.setSize(Jordaria.config.getWindow_width(), Jordaria.config.getWindow_height());
		frame.setLayout(new GridBagLayout());
		
		//menus
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuSettings = new JMenu("Settings");
		menuDebug = new JMenu("Debug");
		menuItemFileExit = new JMenuItem("Exit");
		menuItemFileExit.addActionListener(this);

		menuDebug.setEnabled(Jordaria.config.getDebugActive());

		menuBar.add(menuFile);
		menuBar.add(menuSettings);
		menuBar.add(menuDebug);
		menuFile.add(menuItemFileExit);
		
		textArea = new ScrollingTextArea();
		textArea.setMaxHistory(50);
		textArea.setBorder(BorderFactory.createEtchedBorder());
		
		mapArea = new MapArea(jordaria);
		mapArea.setBorder(BorderFactory.createEtchedBorder());
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .5;
		c.weighty = GridBagConstraints.REMAINDER;
		frame.add(menuBar, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = .5;
		frame.add(mapArea, c);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		frame.add(textArea, c);
		
		frame.setVisible(true);

	}
	
	/**
	 * Disposes of the frame and components
	 * 
	 * @param event The event that occurred
	 */
	@EventHandler
	public void onEventShutdown(ShuttingDown event){
		frame.dispose();
	}
	
	@EventHandler
	public void onTick(Tick event){
		this.textArea.appendMessage("Ticked!");
	}
	
	/**
	 * Registers listeners once the event system is running.
	 * This prevents possible issues with registering when the 
	 * Event system is not running.
	 * 
	 * @param event The event that occurred
	 */
	@EventHandler
	public void onEventSystemStarted(EventSystemStarted event){
		registerEventListeners();
	}
	/**
	 * Registers listeners with the event manager.
	 */
	private void registerEventListeners(){
		try {
			jordaria.getEventManager().registerEventListeners(mapArea);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
