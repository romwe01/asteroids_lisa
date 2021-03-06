package graphics;


import core.Core;
import input.InputManager;

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
	
	/*
	public void render()
	{
		//calculate time
		Graphics2D g = renderSystem.beginUpdate();
		//clear background
		g.clearRect(-width/2, -height/2, width, height);
		core.updateManager.UpdateAll();
		
		//update all the entities
		core.entityManager.updateAllEntites();
		core.entityManager.renderAllEntities(g);
		
		renderSystem.endUpdate();
	}
	*/
	
	public void setFrameRate(int fps)
	{
		renderSystem.setFrameRate(fps);
	}
	
	public void close()
	{
		renderSystem.close();
	}
}
