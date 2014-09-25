package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.AsteroidsGame;
import core.Core;
import events.KeyPressedEvent;
import events.KeyPressedListener;
import events.EventListener.EventType;

public class EntityManager implements KeyPressedListener {

	public ArrayList<Entity> entities;

	public EntityManager() {
		this.entities = new ArrayList<Entity>();
	}

	public boolean addEntity(Entity entity) {
		this.entities.add(entity);
		return true;
	}

	public void removeEntity(Entity entity) {
		if (this.entities.contains(entity)) {
			this.entities.remove(entity);
		}
	}

	public void updateAllEntities(Graphics2D g) {
		for (Entity e : this.entities) {
			e.update();
			e.render(g);
		}
	}

	@Override
	public String getType() {
		return EventType.KEY.name();
	}

	@Override
	public void setUp() {
		// handled in core
		// not good

	}

	@Override
	public void tearDown() {
		// handled in core
		// not good
	}

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
				entities.add(en);
				en.fire();
			}
			break;
		}

	}

}
