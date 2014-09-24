package graphics;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public class Fullscreen implements Screen{

	private RenderSystem renderSystem;
	private BufferStrategy bufferStrategy;
	private Graphics2D drawGraphics;
	private GraphicsDevice graphicsDevice;
	
	private int width;
	private int height; 
	
	public Fullscreen(RenderSystem rs){
		renderSystem = rs;
	}
	
	@Override
	public void open(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
		
		graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		renderSystem.setIgnoreRepaint(true);
		renderSystem.setResizable(false);
		renderSystem.setUndecorated(true);
		renderSystem.setLocationRelativeTo(null);
		renderSystem.setVisible(true);
		
		renderSystem.createBufferStrategy(2);
		bufferStrategy = renderSystem.getBufferStrategy();
		
		graphicsDevice.setFullScreenWindow(renderSystem);
		setDisplayMode(32, width, height);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		graphicsDevice.setFullScreenWindow(null);
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
		drawGraphics.dispose();
		bufferStrategy.show();
		Toolkit.getDefaultToolkit().sync();
	}

	private void setDisplayMode(int bitDepth, int width2, int height2){
		int rate = 60;
		
		try{
			graphicsDevice.setDisplayMode(new DisplayMode(width2, height2, bitDepth, rate));
		} catch(IllegalArgumentException e){
			System.out.println("Error: setting Display mode");
		}
	}
	
	
}
