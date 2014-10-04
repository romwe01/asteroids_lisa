package core;

import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;
import messenger.Messenger;
import statemachine.EndState;
import statemachine.MenuState;
import statemachine.State;
import statemachine.PlayState;
import statemachine.SplashState;
import statemachine.StateMachine;
import collision.CollisionTester;
import entities.EntityManager;
import events.EventListener.EventType;
import events.EventManager;

public class Core {

	public InputManager inputManager;
	public EntityManager entityManager;
	public EventManager eventManager;
	public CollisionTester cT;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	public Messenger messenger;

	public State splashState;
	public State menuState;
	public State playState;
	public State endState;
	public StateMachine stateMachine;

	// delta time
	private float dt;

	private boolean runLoop = true;
	public boolean isDebug = true;

	/**
	 * is the core of the game, implements all needed pieces for running the
	 * game
	 * 
	 * @param graphicsConfig
	 */
	public Core(GraphicsConfig graphicsConfig) {
		inputManager = new InputManager(this);
		eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);

		// Collision
		cT = new CollisionTester(this);
		this.graphicsConfig = graphicsConfig;

		setGraphicsConfiguration(graphicsConfig);
		graphicsSystem.addInputManager(inputManager);

		this.setupStateMachine();
	}

	/**
	 * opens the window and sets the frame rate
	 * 
	 * @param gc
	 */
	private void setGraphicsConfiguration(GraphicsConfig gc) {
		graphicsSystem.setFrameRate(gc.FPS);
		graphicsSystem.open(gc.Width, gc.Height, gc.Fullscreen);
	}

	public void closeWindow() {
		graphicsSystem.close();
	}

	public void setDT(float dt) {
		this.dt = dt;
	}

	public float getDT() {
		return dt;
	}

	public void requestUpdate() {
		eventManager.handleEvents();

		// update and render each state
		stateMachine.update();
		messenger.update();
	}

	public boolean getRunLoop() {
		return runLoop;
	}

	public GraphicsConfig getGraphicsConfig() {
		return graphicsConfig;
	}

	public String getType() {
		return EventType.KEY.name();
	}

	// public void tearDown() {
	// closeWindow();
	// }

	/**
	 * setup state machine, which manages all game states
	 */
	private void setupStateMachine() {
		// Messenger
		messenger = new Messenger();

		// setup state machine
		playState = new PlayState(messenger, eventManager, this);
		splashState = new SplashState(messenger, this);
		menuState = new MenuState(messenger, this);
		endState = new EndState(messenger);
		stateMachine = new StateMachine(splashState, playState);

		// add states
		// TODO: move initializing start and end state to addState()
		stateMachine.addState(menuState);
		stateMachine.addState(endState);

		// add transitions between game states
		stateMachine.addTransition(splashState, "x", menuState);
		stateMachine.addTransition(menuState, "x", playState);
		stateMachine.addTransition(playState, "m", menuState);
		stateMachine.addTransition(playState, "q", endState);

		messenger.subscribe(() -> runLoop = false, "quit");
		eventManager.addListener(stateMachine, getType());

	}

}
