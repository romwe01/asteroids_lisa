package events;

public interface EventListener {

	public String getType();
	void setUp();
	void tearDown();
	
	public enum EventType {
		KEY
	}
}
