package graphics;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import core.Core;

public class RenderSystem extends Frame
{
	private static final long serialVersionUID = 1L;
	private long loopStart; 
	private long dt, duration = 0;
	private long TimeFrameN0;
	
	private Screen screen;
	private Core core;
	
	public void open(Core c, int width, int height, boolean fullscreen)
	{
		//to open on default screen if multiple screens are available
		GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		//if device cannot support fullscreen mode
		if (!graphicsDevice.isFullScreenSupported()){
			fullscreen = false;
		}
		
		if (fullscreen){
			screen = new Fullscreen(this);
		}
		else{
			screen = new WindowScreen(this);
		}
		
		core = c;
		screen.open(width, height);
	}
	
	public void close(){
		screen.close();
	}
	
	//calculate time for game loop
	public Graphics2D beginUpdate()
	{
		loopStart = System.nanoTime();
		core.setDT((float)(loopStart - TimeFrameN0) /1000000000L);
		TimeFrameN0 = System.nanoTime();
		return screen.beginUpdate();
	}
	
	public void endUpdate()
	{
		screen.endUpdate();
		
		dt = System.nanoTime() - loopStart;
		long diff = duration - dt;
		if (diff > 0){
			try {
				Thread.sleep(diff/1000000L);
			} catch(InterruptedException e){
			}
		}
	}
	
	public void setFrameRate(int fps){
		duration = 1000000L * (long)(1000 * 1.0f / fps);
	}
	
	
}
