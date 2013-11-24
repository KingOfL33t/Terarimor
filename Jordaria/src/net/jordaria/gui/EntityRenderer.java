package net.jordaria.gui;

import java.util.Random;

import net.jordaria.Jordaria;
import net.jordaria.entity.EntityLiving;
import net.jordaria.entity.EntityPlayer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class EntityRenderer
{

	public static boolean anaglyphEnable;

	/** Anaglyph field (0=R, 1=GB) */
	public static int anaglyphField;

	/** A reference to the Minecraft object. */
	private Jordaria jd;
	private float camRoll;
	private float prevCamRoll;

	/** FOV modifier hand */


	private double cameraZoom = 1.0D;
	private double cameraYaw;
	private double cameraPitch;

	/** Previous frame time in milliseconds */
	private long prevFrameTime = Jordaria.getSystemTime();

	private Random random = new Random();

	public EntityRenderer(Jordaria jord)
	{
		this.jd = jord;
	}

	/**
	 * Updates the entity renderer
	 */
	public void updateRenderer()
	{
		this.prevCamRoll = this.camRoll;
		if (this.jd.renderViewEntity == null)
		{
			this.jd.renderViewEntity = this.jd.thePlayer;
		}

	}

	/**
	 * sets up player's eye (or camera in third person mode)
	 */
	private void orientCamera(float par1)
	{
		EntityLiving renderPlayerEntity = this.jd.renderViewEntity;
		float y = renderPlayerEntity.yOffset - 1.62F;
		GL11.glRotatef(this.prevCamRoll + (this.camRoll - this.prevCamRoll) * par1, 0.0F, 0.0F, 1.0F);

		if (renderPlayerEntity.isPlayerSleeping())
		{
			y = (float)((double)y + 1.0D);
			GL11.glTranslatef(0.0F, 0.3F, 0.0F);
		}
		else
		{
			GL11.glTranslatef(0.0F, 0.0F, -0.1F);
		}
		GL11.glTranslatef(0.0F, y, 0.0F);

	}

	/**
	 * sets up projection, view effects, camera position/rotation
	 */
	private void setupCameraTransform(float par1, int par2)
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		if (this.cameraZoom != 1.0D)
		{
			GL11.glTranslatef((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
			GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
		}

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		this.orientCamera(par1);
	}

	/**
	 * Will update any inputs that effect the camera angle (mouse) and then render the world and GUI
	 */
	public void updateCameraAndRender(float par1)
	{

		boolean displayActive = Display.isActive();

		this.prevFrameTime =Jordaria.getSystemTime();

		if (this.jd.inGameHasFocus && displayActive)
		{
			this.jd.mouseHelper.mouseXYChange();
			float var3 = this.jd.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float var4 = var3 * var3 * var3 * 8.0F;
			float var5 = (float)this.jd.mouseHelper.deltaX * var4;
			float var6 = (float)this.jd.mouseHelper.deltaY * var4;
			byte var7 = 1;

				this.jd.thePlayer.setAngles(var5, var6 * (float)var7);
		}

	}

	public void renderWorld(float par1, long par2)
	{

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		if (this.jd.renderViewEntity == null)
		{
			this.jd.renderViewEntity = this.jd.thePlayer;
		}

		EntityLiving renderEntity = this.jd.renderViewEntity;
		
		for (int i = 0; i < 2; ++i)
		{

			GL11.glViewport(0, 0, this.jd.displayWidth, this.jd.displayHeight);

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glEnable(GL11.GL_CULL_FACE);

			this.setupCameraTransform(par1, i);

			
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDepthMask(true);

			if (this.cameraZoom == 1.0D && renderEntity instanceof EntityPlayer)
			{
			}

		
			GL11.glDisable(GL11.GL_FOG);

		}

		GL11.glColorMask(true, true, true, false);
	}

	/**
	 * Converts performance value (0-2) to FPS (35-200)
	 */
	public static int performanceToFps(int par0)
	{
		short var1 = 200;

		if (par0 == 1)
		{
			var1 = 120;
		}

		if (par0 == 2)
		{
			var1 = 35;
		}

		return var1;
	}

	static Jordaria getRendererJordaria(EntityRenderer entityRenderer)
	{
		return entityRenderer.jd;
	}
}
