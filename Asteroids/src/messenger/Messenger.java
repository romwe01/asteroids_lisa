package messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The messenger provides a list with all actions that have to be executed during one game cycle.
 * @author Lisa
 *
 */
public class Messenger {

	// stores all function calls that have to be executed during one game loop
	private ArrayList<Runnable> queue;
	// maps function calls to a string message
	private Map<String, ArrayList<Runnable>> subscribers;
	
	public Messenger(){
		setUpLists();
	}
	
	/**
	 * creates a message queue and dictionary of subscribers
	 */
	private void setUpLists(){
		subscribers = new HashMap<String, ArrayList<Runnable>>();
		queue = new ArrayList<Runnable>();
	}
	
	/**
	 * adds a subscriber to a certain message
	 * @param subscriber
	 * @param msgType
	 */
	public void subscribe(Runnable subscriber, String msgType){
		// messenger already contains a entry of message
		if(subscribers.containsKey(msgType)){
			// list of all subscribers of a certain message
			ArrayList<Runnable> tmp = subscribers.get(msgType);
			
			// add subscriber to a message
			tmp.add(subscriber);
			subscribers.put(msgType, tmp);
		} else {
			// create new message entry for subscriber
			ArrayList<Runnable> tmp = new ArrayList<Runnable>();
			tmp.add(subscriber);
			subscribers.put(msgType, tmp);
		}
	}
	
	/**
	 * Remove subscriber from the list of subscribers associated to the specified message type.
	 * 
	 * @param subscriber
	 * @param msgType
	 */
	public void unsubscribe(Runnable subscriber, String msgType){
		ArrayList<Runnable> subscribersOfMsg = subscribers.get(msgType);
		
		if (subscribersOfMsg != null){
			subscribersOfMsg.remove(subscriber);
		}
		return;
	}
	
	/**
	 * execute all subscribed method calls 
	 * @param msgType
	 */
	public void fire(String msgType){
		ArrayList<Runnable> subscribersOfMsg = subscribers.get(msgType);
		if(subscribersOfMsg == null){
			return;
		}
		for(Runnable entry : subscribersOfMsg) {
		    
		    entry.run();
		}
	}
	
	/**
	 * adds a 
	 * @param msgType
	 */
	public void send(String msgType){
		
		Runnable foo = () -> { fire(msgType); };	  // Legal because disambiguated
		queue.add(foo);
	}
	
	public void update(){
		for(Runnable r : queue){
			r.run();
		}
		
		queue.clear();
	}
	
	
}
