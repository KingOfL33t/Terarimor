package net.jordaria.gui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import net.jordaria.Jordaria;
import net.jordaria.KeyBind;
import net.jordaria.entity.Direction;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EntityMoveRequest;
import net.jordaria.event.EventHandler;
import net.jordaria.event.Listener;
import net.jordaria.event.MapChanged;
import net.jordaria.event.ShuttingDown;
import net.jordaria.event.Tick;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class MainWindow implements Listener{
	Jordaria jd;
	DisplayMode displayMode;
	int height = 100;
	int width = 100;
	float r = 0;
	float g = 0;
	float b = 0;

	float cameraX = 0;
	float cameraY = 0;
	float cameraSpeed = 20;
	
	private int[] texIds = new int[] {0, 0};
	TextureManager textureManager = new TextureManager();
	
	public MainWindow(Jordaria jordaria){
		this.jd = jordaria;
	}
	public void createWindow() throws Exception{
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (int i = 0; i < d.length; i++){
			if (d[i].getWidth() == Jordaria.config.getWindow_width()
					&& d[i].getHeight() == Jordaria.config.getWindow_height() 
					&& d[i].getBitsPerPixel() == Jordaria.config.getDisplay_bitsPerPixel()){
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle(Jordaria.config.getWindow_title());
		Display.create();
		this.InitGL();
		
		textureManager.loadTexture("assets\\textures\\floor.png");

	}
	
	private void fillRect(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		// top right
		GL11.glVertex2f(posX+width, posY);

		// bottom right
		GL11.glVertex2f(posX+width, posY+height);

		// bottom right
		GL11.glVertex2f(posX+width, posY+height);

		//bottom left
		GL11.glVertex2f(posX, posY+height);

		//top left
		GL11.glVertex2f(posX, posY);

		GL11.glEnd();
		
		GL11.glColor3f(1,1,1);
	}
	private void fillTriangleTop(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		// top right
		GL11.glVertex2f(posX+width, posY);

		// bottom right
		GL11.glVertex2f(posX+width, posY+height);

		GL11.glEnd();
	}
	private void fillTriangleBottom(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// bottom right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY+height);

		//bottom left
		GL11.glVertex2f(posX, posY+height);

		//top left
		GL11.glVertex2f(posX, posY);

		GL11.glEnd();
	}
	public void handleKeyboard(){
		while (Keyboard.next()){
			KeyBind.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
			if (Keyboard.getEventKeyState())
			{
				KeyBind.onTick(Keyboard.getEventKey());
			}
			while (jd.getGameSettings().KEYBIND_MOVE_FORWARD.isPressed())
			{
				this.cameraY-=cameraSpeed;
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_FORWARD.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_BACKWARD.isPressed())
			{
				this.cameraY+=cameraSpeed;
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(180))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_BACKWARD.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_LEFT.isPressed())
			{
				this.cameraX+=cameraSpeed;
				if (jd.getGameSettings().KEYBIND_MOVE_LEFT.isPressed()){
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed"));
				}
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(270))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_LEFT.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_RIGHT.isPressed())
			{
				this.cameraX-=cameraSpeed;
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(90))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_RIGHT.keyDescription+")"));
			}
		}
	}

	private void InitGL(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);//enable mapping textures to faces or quads
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glShadeModel(GL11.GL_SMOOTH);//makes surfaces prettier :3
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//Sets the background color
		GL11.glClearDepth(1.0);//the depth used when the depth buffer is cleared
		GL11.glEnable(GL11.GL_DEPTH_TEST);//allows depth testing, makes rendering more efficient
		GL11.glDepthFunc(GL11.GL_LEQUAL);//what kind of depth testing to use

		GL11.glMatrixMode(GL11.GL_PROJECTION);//modify pixel projection matrix
		GL11.glLoadIdentity();

		//set up the camera
		//this is a 3d camera
		//GLU.gluPerspective(45.0f, (float)displayMode.getWidth()/ (float)displayMode.getHeight(), 0.1f, 10000.0f);
		//this is an orthographic camera
		GL11.glOrtho(-10, width, height, -10, -1, 5);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);//modify the orientation and location matrix
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

		Display.sync(60);

		Keyboard.enableRepeatEvents(true);
	}
	@EventHandler
	public void onShutdown(ShuttingDown shutdown){
		Display.destroy();
	}
	@EventHandler
	public void onTick(Tick event){
		this.tick();
	}
	
	@EventHandler
	public void onMapChanged(MapChanged event){
		width = jd.getWorld().getCurrentMap().getWidth();
		height = jd.getWorld().getCurrentMap().getHeight();
		jd.getWorld().eventManager.fireEvent(new DebugMessage("width:"+this.width + " height:"+this.height));
		//GL11.glViewport(0, 0, width, height);
		this.cameraX = 0;
		this.cameraY = 0;
		GL11.glOrtho(0, width, height, 0, -1, 5);
	}

	public void tick(){
		this.handleKeyboard();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		int x,y;
		for (x=0; x<width; x++){
			for (y=0; y<height; y++){
				switch (jd.getWorld().getCurrentMap().getTile(x, y).getTileType().getID()){
				case -1: setColors(.8f, .21f, .43f);
				break;
				case 0: setColors(.01f, .01f, .05f);
				break;
				case 1: setColors(.7f, .3f, .1f);
				break;
				case 2: setColors(.7f, .3f, .3f);
				break;
				case 3: setColors(.7f, .3f, .4f);
				break;
				case 4: setColors(.7f, .3f, .6f);
				break;
				case 5: setColors(.7f, .3f, .8f);
				break;
				case 6: setColors(.7f, .5f, .8f);
				break;
				case 7: setColors(.2f, .2f, .2f);
				break;
				case 8: setColors(.2f, .4f, .4f);
				break;
				case 9: setColors(.4f, .4f, .4f);
				break;
				case 10: setColors(.4f, .2f, .2f);
				break;
				case 12: setColors(.4f, .2f, .4f);
				break;
				case 13: setColors(.4f, .4f, .2f);
				break;
				case 14: setColors(.3f, .3f, .3f);
				break;
				case 15: setColors(.4f, .2f, .3f);
				break;
				case 16: setColors(.4f, .8f, .5f);
				break;
				case 17: setColors(.5f, .4f, .8f);
				break;
				case 18: setColors(.8f, .4f, .1f);
				break;
				case 19: setColors(.2f, .7f, .3f);
				break;
				case 20: setColors(.2f, .7f, .3f);
				break;
				case 21: setColors(.3f, .2f, .8f);
				break;
				case 22: setColors(.1f, .9f, .9f);
				break;
				case 23: setColors(.1f, .7f, .9f);
				break;
				case 24: setColors(.1f, .5f, .9f);
				break;
				case 25: setColors(.1f, .3f, .9f);
				break;
				case 26: setColors(.6f, .2f, .9f);
				break;
				case 27: setColors(.2f, .2f, .8f);
				break;
				case 28: setColors(.2f, .8f, .8f);
				break;
				case 29: setColors(.8f, .8f, .2f);
				break;
				case 30: setColors(.8f, .8f, .8f);
				break;
				case 31: setColors(.5f, .1f, .1f);
				break;
				case 32: setColors(.1f, .5f, .5f);
				break;
				case 33: setColors(.5f, .6f, .5f);
				break;
				case 34: setColors(.5f, .6f, .6f);
				break;

				}
				//jd.getEventManager().fireEvent(new DebugMessage("X:"+x+" Y:"+y));
				fillRect(x*width+cameraX, y*height+cameraY, width, height, r, g, b);
			}
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 20, 20, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, textureManager.getTextureBuffer("floor.png"));
		}

		Display.update();

		if (Display.isCloseRequested()){
			jd.getEventManager().fireEvent(new ShuttingDown());
		}
	}
	private void setColors(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

}