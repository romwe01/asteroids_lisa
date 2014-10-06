package collider;

import java.util.ArrayList;


import update.Updatable;
import core.Core;

public class CollisionTester implements Updatable{

	public ArrayList<CircleCollider> enemies;
	public ArrayList<CircleCollider> bullets;
	public CircleCollider player;
	
	private Core core;
	
	public CollisionTester(Core core) {
		this.core = core;
		enemies = new ArrayList<CircleCollider>();
		bullets = new ArrayList<CircleCollider>();
		setUpUpdatable();
	}

	public void addBullet(CircleCollider bullet){
		bullets.add(bullet);
	}
	
	public void addPlayer(CircleCollider player){
		this.player = player;
	}
	
	public void addCollisionObject(CircleCollider obj){
		if (!enemies.contains(obj)){
			enemies.add(obj);
		}
		else{
			System.err.println("Object already in collisionmanager.");
		}
	}
	
	public void removeBullet(CircleCollider bullet){
		if (bullets.contains(bullet)){
			bullets.remove(bullet);
		}
	}
	
	public void removeCollisionObj(CircleCollider obj){
		if (enemies.contains(obj)){
			enemies.remove(obj);
		}
	}
	
	/**
	 * runs over all objects, calls method for collision detection
	 */
	public void update(){
		//Player - Asteroid collision
		for (int i = 0; i < enemies.size(); i++){
			if (checkCollisionWithPlayer(enemies.get(i))){
				player.collided(enemies.get(i));
				enemies.get(i).collided(player);
			}
		}
		
		//Bullet - Asteroid collision
		for (int i = 0; i < enemies.size(); i++){
			for (int j = 0; j < bullets.size(); j++){
				if (checkCollisionBulletAsteroid(bullets.get(j), enemies.get(i))){
					bullets.get(j).collided(enemies.get(i));
					enemies.get(i).collided(bullets.get(j));
				}
			}
		}
	}
	
	/**
	 * checks if player collides with another entity
	 * @param obj
	 * @return
	 */
	private boolean checkCollisionWithPlayer(CircleCollider col) {
		if ( ((player.center.getX() - col.center.getX() - col.radius/2.0)*(player.center.getX() - col.center.getX() - col.radius/2.0)) < ((player.radius/2.0f + col.radius/2.0f)*(player.radius/2.0f + col.radius/2.0f)) &&
				((player.center.getY() - col.center.getY() - col.radius/2.0f)* (player.center.getY() - col.center.getY() - col.radius/2.0f)) < ((player.radius/2.0f + col.radius/2.0f)*(player.radius/2.0f + col.radius/2.0f)))
		{
			return true;	// collision with player
		}
		return false;
	}

	/**
	 * checks if two objects collide
	 * @param bullet collider of a bullet entity
	 * @param asteroid collider of an asteroid entity
	 * @return
	 */
	private boolean checkCollisionBulletAsteroid(CircleCollider bullet, CircleCollider asteroid) {
		if ( ((bullet.center.getX() - asteroid.center.getX() - asteroid.radius/2.0f) * (bullet.center.getX() - asteroid.center.getX() - asteroid.radius/2.0f)) < ((bullet.radius/2.0f + asteroid.radius/2.0f) * (bullet.radius/2.0f + asteroid.radius/2.0f))&&
				((bullet.center.getY() - asteroid.center.getY() - asteroid.radius/2.0f) *(bullet.center.getY() - asteroid.center.getY() - asteroid.radius/2.0f)) < ((bullet.radius/2.0f + asteroid.radius/2.0f) *(bullet.radius/2.0f + asteroid.radius/2.0f)) ){
			return true;	// collision with asteroid
		}
		return false;
	}

	public void setUpUpdatable() {
		core.updateManager.addClass(this);
		
	}
	

}
