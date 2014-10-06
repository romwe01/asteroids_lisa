package statemachine;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import events.KeyPressedEvent;
import events.KeyPressedListener;

/**
 * manages setup and transition of states
 * @author Lisa
 *
 */
public class StateMachine extends State implements KeyPressedListener {
	
	private State currState;		// currently activated state
	private State startState;
	private State endState;
	private Map<State, Map> states;	// stores all states including transition settings
	
	/**
	 * setup with start and end state
	 * @param start
	 * @param end
	 */
	public StateMachine(State start, State end){
		this.startState = start;
		this.endState = end;
		this.currState = this.startState;
	
		// dictionary with all states and their transition settings
		this.states = new HashMap<State, Map>();
		
		// add states with dictionaries to the state machine
		this.addState(this.startState);
		this.addState(this.endState);
		this.currState.activate();
	}
	
	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	/**
	 * calls the current state to handle a message
	 * @param msgType
	 */
	@Override
	public void handle(String msgType) {
		
		// transition details
		Map settings = states.get(currState);
		boolean isTransition = settings.containsKey(msgType);
		
		// change state if needed
		if (isTransition){
  			this.currState.deactivate();
			this.currState = (State) settings.get(msgType);
			this.currState.activate();
		}
		
		this.currState.handle(msgType);
	}

	/**
	 * updates the current state
	 */
	@Override
	public void update() {
		this.currState.update();
	}
	
	/**
	 * adds a state to the state machine
	 * @param state
	 */
	public void addState(State state){
		
		// dictionary for transition details
		Map<String, Object> map = new HashMap<String, Object>();
		
		this.states.put(state, map);
		
		return;		
	}
	
	/**
	 * stores information for transitioning between two states in a dictonary
	 * @param source state before transition
	 * @param msg for transition between states
	 * @param destination state after transition
	 */
	public void addTransition(State source, String msg, State destination){
		Map<String, State> settings = this.states.get(source);
		
		// this way several transitions can be stored for one state
		settings.put(msg, destination);
		this.states.put(source, settings);
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void setUp() {
		
	}

	@Override
	public void tearDown() {
	}

	/**
	 * translates key code to message, which is handled by the state machine
	 */
	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: 
			this.handle("up");
			break;
		case KeyEvent.VK_DOWN:
			this.handle("down");
			break;
		case KeyEvent.VK_LEFT:
			this.handle("left");
			break;
		case KeyEvent.VK_RIGHT:
			this.handle("right");
			break;
		case KeyEvent.VK_SPACE:
			this.handle("space");
			break;
		case KeyEvent.VK_ENTER:
			this.handle("enter");
			break;
		case KeyEvent.VK_M:
			this.handle("m");
			break;
		case KeyEvent.VK_Q:
			this.handle("q");
			break;
		case KeyEvent.VK_X:
			this.handle("x");
			break;
		case KeyEvent.VK_ESCAPE:
			this.handle("quit");
			break;
		}
		
		return;
	}
}