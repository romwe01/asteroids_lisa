package collision;

import java.util.ArrayList;

import core.Core;

public class CollisionTester {

	
	public ArrayList<CircleCollider> m_Enemies;
	private ArrayList<CircleCollider> m_Bullets;
	private CircleCollider m_Player;
	private Core m_Core;
	
	public CollisionTester(Core core){
		m_Core = core;
		m_Enemies = new ArrayList<CircleCollider>();
		m_Bullets = new ArrayList<CircleCollider>();
		
	}
	
	public void addBulletObject(CircleCollider bullet){
		m_Bullets.add(bullet);
	}
	
	public void addPlayerObject(CircleCollider player){
		m_Player = player;
	}
	
	public void addCollisionObject(CircleCollider obj){
		if (!m_Enemies.contains(obj)){
			m_Enemies.add(obj);
		}
		else{
			System.out.println("scho drin");
		}
	}
	
	public void removeBulletObject(CircleCollider bullet){
		if (m_Bullets.contains(bullet)){
			m_Bullets.remove(bullet);
		}
	}
	
	public void removeCollisionObject(CircleCollider obj){
		if (m_Enemies.contains(obj)){
			m_Enemies.remove(obj);
		}
		
	}
	
	/**
	 * runs over all objects, calls method for collision detection
	 */
	public void update(){
		for (int i = 0; i < m_Enemies.size(); i++){
			if (checkCollisionWithPlayer(m_Enemies.get(i))){
				m_Enemies.get(i).collided(m_Player);
			}
		}
		
		for (int i = 0; i < m_Enemies.size(); i++){
			for (int j = 0; j < m_Bullets.size(); j++){
				if (checkCollisionWithAsteroid(m_Bullets.get(j), m_Enemies.get(i))){
					m_Bullets.get(j).collided(m_Enemies.get(i));
					m_Enemies.get(i).collided(m_Bullets.get(j));
				}
			}
		}
	}
	
	/**
	 * checks if player collides with another entity
	 * @param obj
	 * @return
	 */
	private boolean checkCollisionWithPlayer(CircleCollider obj){
		if (((m_Player.getCenter().getX() - obj.getCenter().getX() - obj.getRadius()/2.0f) * (m_Player.getCenter().getX() - obj.getCenter().getX() - obj.getRadius()/2.0f)) < ((m_Player.getRadius()/2.0f + obj.getRadius()/2.0f) * (m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)) &&
				((m_Player.getCenter().getY() - obj.getCenter().getY() - obj.getRadius()/2.0f) *(m_Player.getCenter().getY() - obj.getCenter().getY() - obj.getRadius()/2.0f)) < ((m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)  * (m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * checks if two objects collide
	 * @param bullet collider of a bullet entity
	 * @param asteroid collider of an asteroid entity
	 * @return
	 */
	private boolean checkCollisionWithAsteroid(CircleCollider bullet, CircleCollider asteroid){
		if ( ((bullet.getCenter().getX() - asteroid.getCenter().getX() - asteroid.getRadius()/2.0f) * (bullet.getCenter().getX() - asteroid.getCenter().getX() - asteroid.getRadius()/2.0f)) < ((bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f) * (bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f))&&
				((bullet.getCenter().getY() - asteroid.getCenter().getY() - asteroid.getRadius()/2.0f) *(bullet.getCenter().getY() - asteroid.getCenter().getY() - asteroid.getRadius()/2.0f)) < ((bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f) *(bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f)) ){
			return true;
		}
		return false;
	}
	
}
