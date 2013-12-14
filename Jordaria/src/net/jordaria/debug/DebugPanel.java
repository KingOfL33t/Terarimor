package net.jordaria.debug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.jordaria.Jordaria;

/*
 * This is a very volitile class which contains
 * A jframe with buttons on for use testing various
 * parts of the game and engines
 */
public class DebugPanel implements ActionListener{

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
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(b_testRandomness)){//test randomness was pressed
			int i;
			int j;
		
			for (i = 0; i< 10; i++){
				for (j = 0; j< 10; j++){
				System.out.print(jd.rand.getIntBetween(-2, 18)+ " ");
				}
				System.out.println();
			}
			
		}

	}

}
