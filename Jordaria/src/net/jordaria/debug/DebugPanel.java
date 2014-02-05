package net.jordaria.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.jordaria.Jordaria;
import net.jordaria.event.Error;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventPriority;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;
import net.jordaria.world.Map;
import net.jordaria.world.World;
import net.jordaria.world.WorldGen;

/**
 * This is a very volatile class which contains
 * a JFrame with buttons for use in testing various
 * parts of the game and engines.
 * 
 * @author Ches Burks
 */
public class DebugPanel implements ActionListener, Listener{

	public Jordaria jd;

	public JFrame frame;
	public JButton b_testRandomness;
	public JButton b_testTownGen;
	public JButton b_startItemTester;

	/**
	 * Constructs a new DebugPanel and sets up components.
	 */
	public DebugPanel(){
		frame = new JFrame("Debug Panel");
		frame.setSize(350,250);
		frame.setLayout(new GridLayout(2, 2));
		b_testRandomness = new JButton("Test Random generator");
		b_testRandomness.addActionListener(this);
		b_testTownGen = new JButton("Test townGen");
		b_testTownGen.addActionListener(this);
		b_startItemTester = new JButton("Start Item Tester");
		b_startItemTester.addActionListener(this);
		frame.add(b_testRandomness);
		frame.add(b_testTownGen);
		frame.add(b_startItemTester);
		frame.setVisible(true);
	}
	
	/**
	 * Sets the reference to the main program.
	 * 
	 * @param jordaria A reference to the main program
	 */
	public void setJordariaVar(Jordaria jordaria){
		this.jd = jordaria;
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
		this.jd = null;
		this.frame.removeAll();
		this.frame.setVisible(false);
		this.frame = null;
	}

	/**
	 * Determines what action was performed, and 
	 * executes the requested test
	 * 
	 * @param e The ActionEvent that occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(b_testRandomness)){//test randomness was pressed
			int i;
			int j;
			//int c;
			int size = 624;

			Boolean[][] pixels = new Boolean[size][size];
			//for (c = 0; c<=2; c++){
			for (i = 0; i< size; i++){
				for (j = 0; j< size; j++){
					//System.out.print(jd.rand.nextBoolean()+ " ");
					pixels[i][j] = jd.getRandomGenerator().nextBoolean();
				}
			}
			//}
			JFrame frame = new JFrame("Rand test");
			MyPanel panel = new MyPanel(pixels,size,size);
			frame.add(panel);
			frame.setSize(size,size);
			frame.setVisible(true);
		}
		else if (e.getSource().equals(b_testTownGen)){
			World world = jd.theWorld;
			WorldGen generator = new WorldGen();
			int size = 30;
			Map map = new Map(size, size);
			generator.fillWithTown(map);
			world.setCurrentMap(map);


			JFrame frame = new JFrame("Town test");
			MyPanel panel = new MyPanel(map.getWidth(),map.getHeight(),map);
			frame.add(panel);
			frame.setSize(map.getWidth()*size+30,map.getWidth()*size+60);
			frame.setVisible(true);
		}
		else if (e.getSource().equals(b_startItemTester)){
			DebugItemGenPanel itemPanel = new DebugItemGenPanel();
			try {
				jd.getEventManager().registerEventListeners(itemPanel);
			} catch (Exception e1) {
				jd.getEventManager().fireEvent(new Error("Failed to register itemTest panel"));
			}
		}

	}
	
	/**
	 * A multi-purpose JPanel for showing various graphical versions of data for testing.
	 * 
	 * @author Ches Burks
	 *
	 */
	class MyPanel extends JPanel {
		private static final long serialVersionUID = 2555986089994974943L;
		Boolean[][] floats;
		int w;
		int h;
		int testcase;
		Map map;
		/**
		 * Creates a panel for displaying booleans
		 * @param bools A 2d array of booleans
		 * @param w How wide the array of booleans is
		 * @param h How tall the array of booleans is
		 */
		public MyPanel(Boolean[][] bools,int w, int h) {
			this.testcase = 1;
			this.floats = bools;
			this.w = w;
			this.h = h;
			setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		/**
		 * Creates a panel for showing a map
		 * @param w The width (in tiles) of the map
		 * @param h The height (in tiles) of the map
		 * @param map The map to show
		 */
		public MyPanel(int w, int h, Map map){
			this.testcase = 2;
			this.w = w;
			this.h = h;
			this.map = map;
		}

		/**
		 * Paint the data on the screen.
		 */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (testcase == 1){
				int x;
				int y;
				g.setColor(Color.black);
				for (y=0;y<h;y+=1){
					for (x=0;x<w;x+=1){
						if (floats[x][y]){
							g.drawRect(x, y, 0, 0);
						}
					}
				}
			}
			else if (testcase == 2){
				int x;
				int y;
				int scale_W = map.getWidth();
				int scale_H = map.getHeight();
				for (y=0;y<h*scale_W;y+=scale_W){
					for (x=0;x<w*scale_H;x+=scale_H){
						switch (map.getTile(x/scale_H, y/scale_W).getTileType().getID()) {
						case 1: g.setColor(Color.orange);
						break;
						case 2 : g.setColor(Color.blue);
						break;
						case 3 : g.setColor(Color.green);
						break;
						case 4 : g.setColor(Color.yellow);
						break;
						case 5 : g.setColor(Color.darkGray);
						break;
						case 6 : g.setColor(Color.cyan);
						break;
						case 7 : g.setColor(Color.darkGray);
						break;
						case 8 : g.setColor(Color.gray);
						break;
						case 9 : g.setColor(Color.lightGray);
						break;
						case 10 : g.setColor(Color.magenta);
						break;
						case 11 : g.setColor(Color.pink);
						break;
						case 12 : g.setColor(Color.white);
						break;
						case 13 : g.setColor(Color.getHSBColor(.5f, .8f, .12f));
						break;
						case 14 : g.setColor(Color.getHSBColor(.2f, .8f, .12f));
						break;
						case 15 : g.setColor(Color.getHSBColor(.5f, .1f, .62f));
						break;
						case 16 : g.setColor(Color.getHSBColor(.3f, .8f, .42f));
						break;
						case 17 : g.setColor(Color.getHSBColor(.7f, .1f, .12f));
						break;
						case 18 : g.setColor(Color.getHSBColor(.5f, .1f, .12f));
						break;
						case 19 : g.setColor(Color.getHSBColor(.31f, .7f, .52f));
						break;
						case 20 : g.setColor(Color.getHSBColor(.1f, .5f, .72f));
						break;
						case 21 : g.setColor(Color.getHSBColor(.1f, .83f, .72f));
						break;
						
						case -1 : g.setColor(Color.red);
						break;
						default: g.setColor(Color.black);
						break;
						}
						g.fillRect(x, y, scale_W, scale_H);
					}
				}
			}
		}  
	}


}
