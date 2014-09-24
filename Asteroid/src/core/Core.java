package core;

import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;

import java.awt.event.KeyEvent;

import events.KeyPressedEvent;
import events.KeyPressedListener;
import events.EventManager;


public class Core implements KeyPressedListener{

	public InputManager inputManager;
	public EventManager eventManager;
	//public EntityManager entityManager;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	
	private float dt;
	private boolean runLoop = true;
	
	public boolean isDebug = true;
	
	public Core (GraphicsConfig graphicsConfig){
		//entityManager = new EntityManager(this);
		inputManager = new InputManager(this);
		eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);
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
		eventManager.handleEvents();
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
		eventManager.addListener(this, "KeyPressed");
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		eventManager.removeListener(this);
		closeWindow();
	}

	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.out.println("Beenden");
			runLoop = false;
		}
	}

	
}
