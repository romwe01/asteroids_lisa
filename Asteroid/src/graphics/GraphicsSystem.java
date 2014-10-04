package graphics;

import core.Core;
import input.InputManager;
/**
 * Provides methods for setting up the graphic system. Rendering happens in the game states.
 * @author Lisa
 *
 */
public class GraphicsSystem {

	public RenderSystem renderSystem;
	private Core core;
	
	public int width;
	public int height; 
	
	public GraphicsSystem(Core core)
	{
		this.core = core; 
		renderSystem = new RenderSystem();
	}
	
	//for key inputs
	public void addInputManager (InputManager i)
	{
		renderSystem.addKeyListener(i);
	}
	
	public void open(int width, int height, boolean fullscreen)
	{
		this.width = width;
		this.height = height; 
		renderSystem.open(core, width, height, fullscreen);
	}
	
	public void setFrameRate(int fps)
	{
		renderSystem.setFrameRate(fps);
	}
	
	public void close()
	{
		renderSystem.close();
	}
}
