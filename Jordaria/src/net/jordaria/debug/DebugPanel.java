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

			HashMap<Float, Integer> randomness = new HashMap<Float, Integer>();
			float randValue = 0.0f;

			for (int i = 0; i <1000; i++){
				randValue = jd.rand.nextFloat();
				if (randomness.containsKey(randValue)){
					randomness.put(randValue, randomness.get(randValue)+1);
				}
				else{
					randomness.put(randValue, 1);
				}
			}
			int zero,one,two,three,four,five,six,seven,eight,nine;
			zero = 0;
			nine=eight=seven=six=five=four=three=two=one=zero;
			for (float f : randomness.keySet()){
				if (f<.1){
					one++;
				}
				else if (f<.2){
					two++;
				}
				else if (f<.3){
					three++;
				}
				else if (f<.4){
					four++;
				}
				else if (f<.5){
					five++;
				}
				else if (f<.6){
					six++;
				}
				else if (f<.7){
					seven++;
				}
				else if (f<.8){
					eight++;
				}
				else {
					nine++;
				}
			}
			System.out.println("1:"+one);
			System.out.println("2:"+two);
			System.out.println("3:"+three);
			System.out.println("4:"+four);
			System.out.println("5:"+five);
			System.out.println("6:"+six);
			System.out.println("7:"+seven);
			System.out.println("8:"+eight);
			System.out.println("9:"+nine);
			randomness.clear();
		}

	}

}
