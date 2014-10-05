package statemachine;

public abstract class State {
	
	public abstract void activate();
	
	public abstract void deactivate();
	
	public abstract void handle(String msgType);
	
	public abstract void update();
}