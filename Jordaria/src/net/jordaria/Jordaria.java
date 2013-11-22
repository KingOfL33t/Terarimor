package net.jordaria;

import java.awt.Canvas;

import net.jordaria.gui.GuiIngame;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Jordaria extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337968212257060894L;
	public boolean running;//A boolean used to show whether or not the game is running -King
	public static Configuration config;
	public Thread thread;
	public boolean wireframe;

	public GuiIngame ingameGui;
	DisplayMode displayMode;

	public static void main(String args[]){
		config = new Configuration();
		Jordaria game = new Jordaria();
		game.start();
	}

	public void start(){
		if (this.running)
		{
			return;//if the game is already in progress, dont start it again -King
		}
		this.running = true;
		try{
			wireframe = false;
			ingameGui= new GuiIngame(this);
			createWindow();
			InitGL();
			run();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void run(){
		while (this.running && !Display.isCloseRequested()){
			try{
				Render();
				Display.update();
				Display.sync(60);
			}
			catch(Exception e){
				;
			}
		}
		Display.destroy();
	}

	public void createWindow() throws Exception{
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (int i = 0; i < d.length; i++){
			if (d[i].getWidth() == config.getWindow_width()
					&& d[i].getHeight() == config.getWindow_height() 
					&& d[i].getBitsPerPixel() == config.getDisplay_bitsPerPixel()){
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle(config.getWindow_title());
		Display.create();
	}

	private void InitGL(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);//enable mapping textures to faces or quads
		GL11.glShadeModel(GL11.GL_SMOOTH);//makes surfaces prettier :3
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//Sets the background color
		GL11.glClearDepth(1.0);//the depth used when the depth buffer is cleared
		GL11.glEnable(GL11.GL_DEPTH_TEST);//allows depth testing, makes rendering more efficient
		GL11.glDepthFunc(GL11.GL_LEQUAL);//what kind of depth testing to use

		GL11.glMatrixMode(GL11.GL_PROJECTION);//modify pixel projection matrix
		GL11.glLoadIdentity();
		
		//set up the camera
		GLU.gluPerspective(45.0f, (float)displayMode.getWidth()/ (float)displayMode.getHeight(), 0.1f, 100.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);//modify the orientation and location matrix
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}

	private void Render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();

		GL11.glTranslatef(-3f,0.0f,-20f);             
		GL11.glRotatef(45f,0.4f,1.0f,0.1f);               
		GL11.glColor3f(0.5f,0.5f,1.0f);  
		for (int x = 0; x<3; x++){
			for (int y = 0; y<3; y++){
				for (int z = 0; z<3; z++){
					ingameGui.RenderCube();
					GL11.glTranslatef(0.0f, 0.0f, 2f);
				}
				GL11.glTranslatef(0.0f, 2f, -6f);
			}
			GL11.glTranslatef(2f, -6f, 0.0f);
		}

	}
}
