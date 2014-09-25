package entities;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Core;

public abstract class Entity {
	protected Vector2D position;
	protected ArrayList<Vector2D> vertices = new ArrayList<Vector2D>();
	protected ArrayList<Vector2D> verticesTrans = new ArrayList<Vector2D>();
	protected Vector2D velocity = new Vector2D(0,0);
	protected float angle = 180f;
	protected Vector2D scale = new Vector2D(2,2);
	
	private String name;
	protected Core core;
	private int renderLayer;

	
	public Entity(String name, Core core, double d, double e){
		
		this.name = name;
		this.core = core;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getRenderLayer(){
		return renderLayer;
	}
	
	public void setRenderLayer(int renderLayer){
		this.renderLayer = renderLayer;
	//	core.entityManager = requestRenderLayerUpdate();
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
	
	public abstract Vector2D getPosition();
	
	public abstract void setPosition(float x, float y);
	
	
	
	
}
