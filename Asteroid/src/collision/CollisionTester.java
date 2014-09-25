package collision;

import java.util.ArrayList;

import core.Core;

public class CollisionTester {

	
	public ArrayList<BoundingCircle> m_Enemies;
	private ArrayList<BoundingCircle> m_Bullets;
	private BoundingCircle m_Player;
	private Core m_Core;
	
	public CollisionTester(Core core){
		m_Core = core;
		m_Enemies = new ArrayList<BoundingCircle>();
		m_Bullets = new ArrayList<BoundingCircle>();
		
	}
	
	public void addBulletObject(BoundingCircle bullet){
		m_Bullets.add(bullet);
	}
	
	public void addPlayerObject(BoundingCircle player){
		m_Player = player;
	}
	
	public void addCollisionObject(BoundingCircle obj){
		if (!m_Enemies.contains(obj)){
			m_Enemies.add(obj);
		}
		else{
			System.out.println("scho drin");
		}
	}
	
	public void removeBulletObject(BoundingCircle bullet){
		if (m_Bullets.contains(bullet)){
			m_Bullets.remove(bullet);
		}
	}
	
	public void removeCollisionObject(BoundingCircle obj){
		if (m_Enemies.contains(obj)){
			m_Enemies.remove(obj);
		}
		
	}
	
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
	
	private boolean checkCollisionWithPlayer(BoundingCircle obj){
		if (((m_Player.getCenter().getX() - obj.getCenter().getX() - obj.getRadius()/2.0f) * (m_Player.getCenter().getX() - obj.getCenter().getX() - obj.getRadius()/2.0f)) < ((m_Player.getRadius()/2.0f + obj.getRadius()/2.0f) * (m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)) &&
				((m_Player.getCenter().getY() - obj.getCenter().getY() - obj.getRadius()/2.0f) *(m_Player.getCenter().getY() - obj.getCenter().getY() - obj.getRadius()/2.0f)) < ((m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)  * (m_Player.getRadius()/2.0f + obj.getRadius()/2.0f)))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkCollisionWithAsteroid(BoundingCircle bullet, BoundingCircle asteroid){
		if ( ((bullet.getCenter().getX() - asteroid.getCenter().getX() - asteroid.getRadius()/2.0f) * (bullet.getCenter().getX() - asteroid.getCenter().getX() - asteroid.getRadius()/2.0f)) < ((bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f) * (bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f))&&
				((bullet.getCenter().getY() - asteroid.getCenter().getY() - asteroid.getRadius()/2.0f) *(bullet.getCenter().getY() - asteroid.getCenter().getY() - asteroid.getRadius()/2.0f)) < ((bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f) *(bullet.getRadius()/2.0f + asteroid.getRadius()/2.0f)) ){
			return true;
		}
		return false;
	}
	
}
