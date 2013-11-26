package net.jordaria.debug;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class DebugConsole extends JPanel{
	
	private static final long serialVersionUID = 133789912946195636L;
	private String window_title = "Debug Console";
	
	public DebugConsole(){
		super();
		this.init();
	}

	private void init(){
		setSize(300,200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setName(window_title);
		setVisible(true);
	}
}
