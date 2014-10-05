package events;

import java.util.ArrayList;

public class EventManager {

	private ArrayList<EventListener> eventListener;
	private ArrayList<Event> pendingEvent;
	
	public EventManager(){
		setUpLists();
	}
	
	private void setUpLists(){
		eventListener = new ArrayList<EventListener>();
		pendingEvent = new ArrayList<Event>();
	}
	
	public void addListener(EventListener e, String type){
		eventListener.add(e);
	}
	
	public void addEvent(Event e){
		pendingEvent.add(e);
	}
	
	public void removeListener(EventListener e){
		if (eventListener.contains(e)){
			eventListener.remove(e);
		}
	}
	
	public void handleEvents(){
		for (int i = 0; i < eventListener.size(); i++){
			for (int j = 0; j< pendingEvent.size(); j++){
				pendingEvent.get(j).processEvent(eventListener.get(i));
			}
		}
		pendingEvent.clear();
	}
	
}
