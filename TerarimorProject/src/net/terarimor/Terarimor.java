package net.terarimor;

import java.awt.Canvas;
import javax.swing.JFrame;

public class Terarimor extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337968212257060894L;
	public static int height = 400;//The height of the jFrame -King
	public static int width = 600;//The width of the jFrame -King
	public static String title = "Terarimor!";//The title of the window -King
	public boolean running = true;//A boolean used to show whether or not the game is running -King
	public Thread thread;
	
	public static void main(String args[]){
		Terarimor game = new Terarimor();
		JFrame mainFrame = new JFrame();
		
		mainFrame.setSize(width, height);
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.add(game);
		
		game.start();
	}

	public void start(){
		if (this.running)
		{
			return;//if the game is already in progress, dont start it again -King
		}
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	public void stop(){
		if (!this.running){
			return;
		}
	}
	public void run(){
		while (this.running){
			System.out.println("Ticked");
		}
	}
}
