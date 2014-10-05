package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import main.AsteroidsGame;
import events.KeyPressedEvent;
import events.KeyPressedListener;

public class EntityManager {

	public ArrayList<Entity> entities;
	private EntityQueue entityQueue;
	private Map<String, Entity> objects;

	public EntityManager() {
		this.entities = new ArrayList<Entity>();
		this.entityQueue = new EntityQueue();
		this.objects = new TreeMap<String, Entity>();
	}

	/**
	 * adds entity to list
	 * @param entity
	 * @return 
	 */
	public void addEntity(Entity entity) {
		//this.entities.add(entity);
		entityQueue.queueEntityAdd(entity);
		
	}

	/**
	 * removes entity from list
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		/*if (this.entities.contains(entity)) {
			this.entities.remove(entity);
		}*/
		entityQueue.queueEntityRemove(entity);
	}

	/**
	 * updates all entities
	 * @param g
	 */
	public void updateAllEntities() {
		/*for (Entity e : this.entities) {
			e.update();
			e.render(g);
		}*/
		entityQueue.process(objects);
		entityQueue.entitiesToAdd.clear();
		
		for (Entity ent : objects.values()){
			ent.update();
		}
	}

	public void renderAllEntities(Graphics2D g){
		for (Entity ent: objects.values()){
			ent.render(g);
		}
	}

}
