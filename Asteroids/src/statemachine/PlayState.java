package statemachine;

import java.awt.Graphics2D;

import update.UpdateManager;
import collider.CollisionTester;
import core.Core;
import emmitter.AsteroidEmmitter;
import emmitter.EmmitterSettings;
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
	public CollisionTester collisionT;
	public UpdateManager updateManager;

	// Entities
	public Ship player;

	public PlayState(Messenger messenger, EventManager eventManager, Core c) {
		this.messenger = messenger;
		this.eventManager = eventManager;
		this.c = c;
		this.entityManager = new EntityManager(c);
		//Collision
		collisionT = new CollisionTester(c);
		

		// Entity player
		player = new Ship("Player 1",this, this.c, 50f, 50f, 50);
		
		// Add entities to EntityManager
		entityManager.addEntity(player);
		
		// Entity asteroids
		entityManager.addEntity(new AsteroidEmmitter("emmitter 1", this, c));
		EmmitterSettings es = new EmmitterSettings();
		es.setFrequency(0.5f);
		
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
		
		c.updateManager.UpdateAll();
		//update all the entities
		entityManager.updateAllEntites();
		entityManager.renderAllEntities(g);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}

}