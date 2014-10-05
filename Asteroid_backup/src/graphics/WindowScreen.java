package graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public class WindowScreen implements Screen{

	private RenderSystem renderSystem;
	private BufferStrategy bufferStrategy;
	private Graphics2D drawGraphics;
	
	private int width;
	private int height; 
	
	public WindowScreen(RenderSystem rs){
		renderSystem = rs;
	}
	
	@Override
	public void open(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
		
		renderSystem.requestFocus();
		renderSystem.setIgnoreRepaint(true);
		renderSystem.setResizable(false);
		Canvas windowC = new Canvas();
		windowC.setPreferredSize(new Dimension(width, height));
		renderSystem.add(windowC);
		renderSystem.pack();
		renderSystem.setVisible(true);
		windowC.createBufferStrategy(2);
		bufferStrategy = windowC.getBufferStrategy();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		renderSystem.dispose();
		
	}

	@Override
	public Graphics2D beginUpdate() {
		// TODO Auto-generated method stub
		drawGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		AffineTransform at = new AffineTransform();
		at.setToIdentity();
		at.translate(width/2, height/2);
		at.scale(1, -1);
		drawGraphics.transform(at);
		
		return drawGraphics;
	}

	@Override
	public void endUpdate() {
		// TODO Auto-generated method stub
		drawGraphics.dispose();
		bufferStrategy.show();
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	

}
