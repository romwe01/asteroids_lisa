package events;

public abstract class Event {

	public abstract String getType();
	public abstract void processEvent(EventListener listener);
	
}
