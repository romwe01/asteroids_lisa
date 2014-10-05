package messenger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import entities.Entity;

/**
 * The messenger provides...
 * @author Lisa
 *
 */
public class Messenger {

	// stores all function calls that have to be executed during the current game loop
	private ArrayList<Runnable> queue;
	// maps function calls to a string message
	Map<String, ArrayList<Runnable>> subscribers;
	
	
	public Messenger(){
		setUpLists();
	}
	
	private void setUpLists(){
		subscribers = new HashMap<String, ArrayList<Runnable>>();
		queue = new ArrayList<Runnable>();
	}
	
	public void subscribe(Runnable subscriber, String msgType){
		if(subscribers.containsKey(msgType)){
			ArrayList<Runnable> tmp = subscribers.get(msgType);
			tmp.add(subscriber);
			subscribers.put(msgType, tmp);
		} else {
			ArrayList<Runnable> list = new ArrayList<Runnable>();
			list.add(subscriber);
			subscribers.put(msgType, list);
		}
		// subscribers.put(msgType, subscriber);
		// Object o = (Runnable) () -> { System.out.println("hi"); };	  // Legal because disambiguated
		
		// subscribers.get("name"); // returns "demo"
//      JButton button2 = new JButton("Click me too!");     
//      button2.addActionListener(e -> { System.out.println("Bye!"); });

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
	
	public void fire(String msgType){
		ArrayList<Runnable> subscribersOfMsg = subscribers.get(msgType);
		if(subscribersOfMsg == null){
			return;
		}
		for(Runnable entry : subscribersOfMsg) {
		    
		    entry.run();
		}
	}
	
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
