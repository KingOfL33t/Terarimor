package net.jordaria.debug;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import net.jordaria.Jordaria;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventPriority;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;

/*
 * This is a very volitile class which contains
 * A jframe with buttons on for use testing various
 * parts of the game and engines
 */
public class DebugPanel implements ActionListener, Listener{

	public Jordaria jd;

	public JFrame frame;
	public JButton b_testRandomness;


	public DebugPanel(){
		frame = new JFrame("Debug Panel");
		frame.setSize(350,250);
		b_testRandomness = new JButton("Test Random generator");
		b_testRandomness.addActionListener(this);
		frame.add(b_testRandomness);
		frame.setVisible(true);
	}
	public void setJordariaVar(Jordaria jordaria){
		this.jd = jordaria;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onShutdown(ShuttingDown event){
		this.jd = null;
		this.frame.removeAll();
		this.frame.setVisible(false);
		this.frame = null;
	}
	
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
						pixels[i][j] = jd.rand.nextBoolean();
					}
				}
			//}
			JFrame frame = new JFrame("Rand test");
			MyPanel panel = new MyPanel(pixels,size,size);
			frame.add(panel);
			frame.setSize(size,size);
			frame.setVisible(true);
		}

	}
	class MyPanel extends JPanel {
		Boolean[][] floats;
		int w;
		int h;
		public MyPanel(Boolean[][] bools,int w, int h) {
			this.floats = bools;
			this.w = w;
			this.h=h;
			setBorder(BorderFactory.createLineBorder(Color.black));

		}

		public Dimension getPreferredSize() {
			return new Dimension(654,654);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x;
			int y;
			g.setColor(Color.black);
			for (y=0;y<h;y+=1){
				for (x=0;x<w;x+=1){
					//g.setColor(new Color(floats[0][x][y], floats[1][x][y], floats[2][x][y]));
					if (floats[x][y]){
					g.drawRect(x, y, 0, 0);
					}

				}
			}
		}  
	}


}
