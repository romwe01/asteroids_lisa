package core;

import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;

import java.awt.event.KeyEvent;

import collision.CollisionTester;

import entities.Entity;
import entities.EntityManager;
import events.EventManager;
import events.KeyPressedEvent;
import events.KeyPressedListener;


public class Core implements KeyPressedListener{

	public InputManager inputManager;
	public EntityManager entityManager;
	public EventManager eventManager;
	public CollisionTester cT;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	
	// delta time
	private float dt;
	
	private boolean runLoop = true;	
	public boolean isDebug = true;
	
	/**
	 * is the core of the game, implements all needed pieces for running the game
	 * @param graphicsConfig
	 */
	public Core (GraphicsConfig graphicsConfig){
		entityManager = new EntityManager();
		inputManager = new InputManager(this);
		eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);
		cT = new CollisionTester(this);
		this.graphicsConfig = graphicsConfig;
		
		setGraphicsConfiguration(graphicsConfig);
		graphicsSystem.addInputManager(inputManager);
		setUp();
	}
	
	/**
	 * opens the window and sets the frame rate
	 * @param gc
	 */
	private void setGraphicsConfiguration(GraphicsConfig gc){
		graphicsSystem.setFrameRate(gc.FPS);
		graphicsSystem.open(gc.Width, gc.Height, gc.Fullscreen);
	}
	
	
	public void addEntity(Entity e){
		entityManager.addEntity(e);
	}
	
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
		return EventType.KEY.name();
	}

	@Override
	public void setUp() {
		eventManager.addListener(this, getType());
		eventManager.addListener(entityManager, getType());
	}

	@Override
	public void tearDown() {
		eventManager.removeListener(this);
		eventManager.removeListener(entityManager);
		closeWindow();
	}

	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.out.println("Beenden");
			runLoop = false;
			break;
			/*
		case KeyEvent.VK_P:
			System.out.println("Pause");
			break;
			*/
		}
	}

	
}
