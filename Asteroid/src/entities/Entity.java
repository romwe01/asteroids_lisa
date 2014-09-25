package entities;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Core;
import graphics.RenderLayer;

public abstract class Entity {
	
	private String name;
	protected Core core;
	private int renderLayer;

	
	public Entity(String name, Core core, float x, float y, float radius){
		
		this.renderLayer = RenderLayer.BACKGROUND;
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
