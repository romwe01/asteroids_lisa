package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import main.AsteroidsGame;
import events.KeyPressedEvent;
import events.KeyPressedListener;

public class EntityManager implements KeyPressedListener {

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
	
	@Override
	public String getType() {
		return EventType.KEY.name();
	}

	@Override
	public void setUp() {
		// handled in core
		

	}

	@Override
	public void tearDown() {
		// handled in core
		
	}

	/**
	 * handles what the ship should do if keys are pressed
	 */
	@Override
	public void onKeyPressed(KeyPressedEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			AsteroidsGame.player.accelerate();
			break;
		case KeyEvent.VK_DOWN:
			AsteroidsGame.player.decelerate();
			break;
		case KeyEvent.VK_LEFT:
			AsteroidsGame.player.rotate(true);
			break;
		case KeyEvent.VK_RIGHT:
			AsteroidsGame.player.rotate(false);
			break;
		case KeyEvent.VK_SPACE:
			Shot en = AsteroidsGame.player.shoot();
			if(en!=null){
				//entities.add(en);
				addEntity(en);
				en.fire();
			}
			break;
		}

	}

}
