package statemachine;

import java.awt.Graphics2D;
import core.Core;
import entities.Asteroid;
import entities.EntityManager;
import entities.Ship;
import entities.Shot;
import events.EventManager;
import events.EventListener.EventType;
import messenger.Messenger;

public class PlayState extends State {

	public Messenger messenger;
	public EventManager eventManager;
	public EntityManager entityManager;
	private Core c;

	// Entities
	public Ship player;

	public PlayState(Messenger messenger, EventManager eventManager, Core c) {
		this.messenger = messenger;
		this.eventManager = eventManager;
		this.c = c;
		this.entityManager = new EntityManager();

		// Entity player
		player = new Ship("Player 1", this.c, 50, 50, 50);
		
		// Add entities to EntityManager
		entityManager.addEntity(player);
		
		// Entity asteroids
		entityManager.addEntity(new Asteroid("astroid 1", c, -100, 100));
	}
	
	/**
	 * helper function for lambda expression
	 */
	private void shoot(){
		Shot en = this.player.shoot();
		if(en!=null){
			this.entityManager.addEntity(en);
			en.fire();
		}
	}

	@Override
	public void activate() {
		System.out.println("playstate activated");
		
		// subscribe behaviour to messenger
		this.messenger.subscribe(() -> player.accelerate(), "up");
		this.messenger.subscribe(() -> player.decelerate(), "down");
		this.messenger.subscribe(() -> player.rotate(true), "left");
		this.messenger.subscribe(() -> player.rotate(false), "right");
		this.messenger.subscribe(() -> shoot(), "space");
	}

	@Override
	public void deactivate() {
		System.out.println("playstate deactivated");
		
		this.messenger.unsubscribe(() -> player.accelerate(), "up");
		this.messenger.unsubscribe(() -> player.decelerate(), "down");
		this.messenger.unsubscribe(() -> player.rotate(true), "left");
		this.messenger.unsubscribe(() -> player.rotate(false), "right");
		this.messenger.unsubscribe(() -> shoot(), "space");
	}

	@Override
	public void handle(String msgType) {
		messenger.send(msgType);
	}

	@Override
	public void update() {
		render();
	}

	public String getType() {
		return EventType.KEY.name();
	}
	
	public void render()
	{
		//calculate time
		Graphics2D g = c.graphicsSystem.renderSystem.beginUpdate();
		
		//clear background
		g.clearRect(-c.graphicsSystem.width/2, -c.graphicsSystem.height/2, c.graphicsSystem.width, c.graphicsSystem.height);
		
		//update all the entities
		entityManager.updateAllEntities();
		entityManager.renderAllEntities(g);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}

}