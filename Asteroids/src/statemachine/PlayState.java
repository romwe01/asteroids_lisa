package statemachine;

import java.awt.Font;
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
import messenger.Messenger;

/**
 * the astroid game
 * @author Lisa
 *
 */
public class PlayState extends State {

	public Messenger messenger;
	public EventManager eventManager;
	public EntityManager entityManager;
	private Core c;
	public CollisionTester collisionT;
	public UpdateManager updateManager;
	public Font stringFont;

	// Entities
	public Ship player;

	public PlayState(Messenger messenger, EventManager eventManager, Core c) {
		this.messenger = messenger;
		this.eventManager = eventManager;
		this.c = c;
		this.entityManager = new EntityManager(c);
		//Collision
		collisionT = new CollisionTester(c);
		
		// text for screen
		stringFont = new Font( "SansSerif", Font.PLAIN, 15 );

		// Entity player
		player = new Ship("Player 1",this, this.c, 100f, 100f, 50, this.messenger);
		
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

	/**
	 * setup state specific subscribers of messenger
	 */
	@Override
	public void activate() {		
		// subscribe behaviour to messenger
		this.messenger.subscribe(() -> player.accelerate(), "up");
		this.messenger.subscribe(() -> player.decelerate(), "down");
		this.messenger.subscribe(() -> player.rotate(true), "left");
		this.messenger.subscribe(() -> player.rotate(false), "right");
		this.messenger.subscribe(() -> shoot(), "space");
		
		c.score = 0;
		// reset player to start position
		player.reset();
		
	}

	/**
	 * unsubscribe state specific subscribers from messenger
	 */
	@Override
	public void deactivate() {		
		this.messenger.unsubscribe(() -> player.accelerate(), "up");
		this.messenger.unsubscribe(() -> player.decelerate(), "down");
		this.messenger.unsubscribe(() -> player.rotate(true), "left");
		this.messenger.unsubscribe(() -> player.rotate(false), "right");
		this.messenger.unsubscribe(() -> shoot(), "space");
	}

	/**
	 * sends a message to the messenger
	 * @param msgType message
	 */
	@Override
	public void handle(String msgType) {
		messenger.send(msgType);
	}

	/**
	 * updates the rendering
	 */
	@Override
	public void update() {
		render();
	}
	
	public void render()
	{
		//calculate time
		Graphics2D g = c.graphicsSystem.renderSystem.beginUpdate();
		
		//clear background
		g.clearRect(-c.graphicsSystem.width/2, -c.graphicsSystem.height/2, c.graphicsSystem.width, c.graphicsSystem.height);
		
		//score
		g.setFont(stringFont);
		g.drawString("SCORE  " + c.score, 100, 290);
		g.drawString("LIVES " + player.lives, 200, 290);
		
		// check for collision
		c.updateManager.UpdateAll();
		//update all the entities
		entityManager.updateAllEntites();
		entityManager.renderAllEntities(g);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}

}