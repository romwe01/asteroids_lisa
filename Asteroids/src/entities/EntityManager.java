package entities;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import core.Core;

public class EntityManager {

	private Map<String, Entity> objects;
	
	private Core core;
	private EntityQueue equeue;
	private boolean refreshOnUpdate = false;
	
	public EntityManager(Core core) {
		this.core = core;
		this.objects = new TreeMap<String, Entity>();
		this.equeue = new EntityQueue();
	}
	
	/**
	 * adds entity to list
	 * @param entity
	 * @return 
	 */
	public void addEntity(Entity e){
		equeue.queueEntityAdd(e);
	}
	
	/**
	 * removes entity from list
	 * @param entity
	 */
	public void removeEntity(Entity e){
		equeue.queueEntityRemove(e);
	}
	
	/**
	 * returns the entity with the key name
	 * @param name
	 * @return
	 */
	public Entity getEntity(String name){
		Object[] keySet = objects.keySet().toArray();
		int stringLen = name.length();
		
		for (Object key : keySet){
			if (((String) key).substring(stringLen).contains(name)){
				return objects.get(key);
			}
		}
		return null;
	}
	
	private void refreshRenderLayers(){
		Collection <Entity> tempE = objects.values();
		this.objects = new TreeMap<String, Entity>();
		
		for (Entity e: tempE){
			objects.put(e.getName(), e);
		}
	}
	
	public void requestOnUpdate(){
		refreshOnUpdate = true;
	}
	
	/**
	 * updates all entities
	 * @param g
	 */
	public void updateAllEntites(){
		equeue.process(objects);
		equeue.entitiesToAdd.clear();
		
		for (Entity ent: objects.values()){
			ent.update();
		}
		
		if (refreshOnUpdate){
			refreshRenderLayers();
			refreshOnUpdate = false;
		}
	}
	
	public void renderAllEntities(Graphics2D g){
		for (Entity ent: objects.values()){
			ent.render(g);
		}
	}

}
