package core;

import java.awt.event.KeyEvent;

import messenger.Messenger;
import collider.CollisionTester;
import entities.Entity;
import entities.EntityManager;
import events.EventListener.EventType;
import events.EventManager;
import events.KeyPressedEvent;
import events.KeyPressedListener;
import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;
import statemachine.EndState;
import statemachine.MenuState;
import statemachine.PlayState;
import statemachine.SplashState;
import statemachine.State;
import statemachine.StateMachine;
import update.UpdateManager;

public class Core{

	private float dt;
	private boolean runLoop = true;
	
	public UpdateManager updateManager;
	public InputManager inputManager;
	public EntityManager entityManager;
	public EventManager eventManager;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	public CollisionTester collisionT;
	public Messenger messenger;
	
	public State splashState;
	public State menuState;
	public State playState;
	public State endState;
	public StateMachine stateMachine;
	
	/**
	 * is the core of the game, implements all needed pieces for running the
	 * game
	 * 
	 * @param graphicsConfig
	 */
	public Core(GraphicsConfig graphicsConfig) {
		updateManager = new UpdateManager();
		inputManager = new InputManager(this);
		
		eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);
		
		this.graphicsConfig = graphicsConfig;
		
		setGraphicsConfiguration(this.graphicsConfig);
		graphicsSystem.addInputManager(inputManager);
		
		
		this.setupStateMachine();
	}

	public float getDT() {
		return dt;
	}
	
	public void setDT(float dt){
		this.dt = dt;
	}
	
	public void closeWindow() {
		graphicsSystem.close();
	}
	
	public void requestUpdate(){
		eventManager.handleEvents();
		//update and render each state
		stateMachine.update();
		messenger.update();
	}
	
	public void addEntity(Entity e){
		entityManager.addEntity(e);
	}

	
	/**
	 * opens the window and sets the frame rate
	 * 
	 * @param gc
	 */
	private void setGraphicsConfiguration(GraphicsConfig g){
		graphicsSystem.setFrameRate(g.FPS);
		graphicsSystem.open(g.Width, g.Height, g.Fullscreen);
	}

	public boolean isRunLoop() {
		return runLoop;
	}

	public void setRunLoop(boolean runLoop) {
		this.runLoop = runLoop;
	}

	/**
	 * setup state machine, which manages all game states
	 */
	private void setupStateMachine(){
		messenger = new Messenger();
		
		//setup state machine
		playState = new PlayState(messenger, eventManager, this);
		splashState = new SplashState(messenger, this);
		menuState = new MenuState(messenger, this);
		endState = new EndState(messenger);
		stateMachine = new StateMachine(splashState, playState);
		
		//add states
		stateMachine.addState(menuState);
		stateMachine.addState(endState);
		
		//add transitions between game states
		stateMachine.addTransition(splashState, "x", menuState);
		stateMachine.addTransition(menuState, "x", playState);
		stateMachine.addTransition(playState, "m", menuState);
		stateMachine.addTransition(playState, "q", endState);
		
		messenger.subscribe(() ->runLoop = false, "quit");
		eventManager.addListener(stateMachine, getType());
	}

	public String getType() {
		return EventType.KEY.name();
	}
}
