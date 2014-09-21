package core;

import java.awt.event.KeyEvent;

import entities.Entity;
import enumeration.Enumerations;
//import events.EventManager;
import events.GameEvent;
import events.KeyPressedEvent;
import events.KeyPressedListener;
import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;
import update.UpdateManager;

public class Core implements KeyPressedListener{

	public InputManager inputManager;
	//public EventManager eventManager;
	//public EntityManager entityManager;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	public UpdateManager updateManager;
	
	private float dt;
	private boolean runLoop = true;
	
	public boolean isDebug = true;
	
	public Core (GraphicsConfig graphicsConfig){
		//entityManager = new EntityManager(this);
		inputManager = new InputManager(this);
		//eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);
		//updateManager = new UpdateManager();
		this.graphicsConfig = graphicsConfig;
		setGraphicsConfiguration(graphicsConfig);
		graphicsSystem.addInputManager(inputManager);
		setUp();
	}
	
	private void setGraphicsConfiguration(GraphicsConfig gc){
		graphicsSystem.setFrameRate(gc.FPS);
		graphicsSystem.open(gc.Width, gc.Height, gc.Fullscreen);
	}
	
	/*
	public void addEntity(Entity e){
		entityManager.addEntity(e);
	}
	*/
	public void closeWindow(){
		graphicsSystem.close();
	}
	
	public void setDT(float dt){
		this.dt = dt;
	}
	
	public float getDT(){
		return dt;
	}
	
	public void requestUpdate(){
		//eventManager.handleEvents();
		graphicsSystem.render();
	}
	
	public boolean getRunLoop(){
		return runLoop;
	}
	
	public GraphicsConfig getGraphicsConfig(){
		return graphicsConfig;
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		//eventManager.addListener(this, "KeyPressed");
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		//eventManager.removeListener(this);
		closeWindow();
	}

	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			runLoop = false;
		}
	}

	
}
