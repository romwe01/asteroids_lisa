package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityQueue {

	public List<Entity> entitiesToAdd;
	public List<Entity> entitiesToRemove;
	
	public EntityQueue(){
		entitiesToAdd = new ArrayList<Entity>();
		entitiesToRemove = new ArrayList<Entity>();
	}
	
	public void queueEntityAdd(Entity e){
		entitiesToAdd.add(e);
	}
	
	public void queueEntityRemove(Entity e){
		entitiesToRemove.add(e);
	}
	
	public void process(Map<String, Entity> objects){
		for (Entity e: entitiesToAdd){
			objects.put(e.getName(), e);
		}
		
		for (Entity e: entitiesToRemove){
			String key = e.getName();
			if (objects.containsKey(key)){
				objects.remove(key);
			}
		}
		entitiesToAdd.clear();
		entitiesToRemove.clear();
	}
}
