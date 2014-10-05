package emmitter;

import java.awt.Graphics2D;

import statemachine.PlayState;
import collider.CircleCollider;
import core.Core;
import entities.Asteroid;
import entities.Entity;

public class AsteroidEmmitter extends Entity{

	//ceate Astroid on every e.g 10th frame
	final static float FREQUENCY = 01.0f;
	
	
	//default settings
	private static final int MAXSIZE = 70;
	private static final int MINSIZE = 5;
	private static final float SPIN_MAX = 0.06f;
	private static final float MIN_SPEED = 1.0f;
	private static final float MAX_SPEED = 3.0f;	
	
	private PlayState state;	
	private EmmitterSettings es;
	
	public AsteroidEmmitter(String name, PlayState state, Core core) {
		super(name, state,core, 0, 0, 0);
		//default EmmitterSettings
		es = new EmmitterSettings (MAXSIZE, MINSIZE, SPIN_MAX, MAX_SPEED, MIN_SPEED, FREQUENCY);
		this.state = state;
	}
	
	public AsteroidEmmitter(String name,PlayState state, Core core, EmmitterSettings es){
		super(name,state, core, 0, 0, 0);
		//default EmmitterSettings
		this.es = es;
	}
	

	/**
	 * creates Asteroids and adds them immediately to the manager
	 */
	public void update() {
		if(createAsteroid()){
			state.entityManager.addEntity(new Asteroid("aster" + " " + System.currentTimeMillis(),state, core, es));
			//System.out.println("Asteroid erstellt");
		}
	}

	private boolean createAsteroid() {
		return (int)(Math.random()*(int)(60/es.getFrequency())) == 1;
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collided(CircleCollider col) {
		// TODO Auto-generated method stub
		
	}

}
