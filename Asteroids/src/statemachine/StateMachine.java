package statemachine;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import events.KeyPressedEvent;
import events.KeyPressedListener;

public class StateMachine extends State implements KeyPressedListener {
	
	State currState;
	State startState;
	State endState;
	Map<State, Map> states;
	
	public StateMachine(State start, State end){
		this.startState = start;
		this.endState = end;

		this.currState = this.startState;
	
		this.states = new HashMap<State, Map>();
		
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

	@Override
	public void handle(String msgType) {
		
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

	@Override
	public void update() {
		this.currState.update();
	}
	
	public void addState(State state){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("destination", null);
		
		this.states.put(state, map);
		
		return;		
	}
	
	public void addTransition(State source, String msg, State destination){
		Map<String, State> settings = this.states.get(source);
		
		// this way several transitions can be stored for one state
		settings.put(msg, destination);
		this.states.put(source, settings);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * sends message which has to be handled by the state machine
	 */
	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		// TODO Auto-generated method stub
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