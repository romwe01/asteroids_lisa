package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EntityManager {

	public ArrayList<Entity> entities;
	
	public EntityManager(){
		this.entities = new ArrayList<Entity>();
	}
	
	public boolean addEntity(Entity entity){
		this.entities.add(entity);
		return true;
	}
	
	public void removeEntity(Entity entity){
		if (this.entities.contains(entity)){
			this.entities.remove(entity);
		}
	}
	
	public void updateAllEntities(Graphics2D g){
		for (Entity e: this.entities){
			e.update();
			e.render(g);
		}
	}
	
}
