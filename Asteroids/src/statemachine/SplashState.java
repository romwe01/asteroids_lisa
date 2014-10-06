package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;

import core.Core;
import messenger.Messenger;

public class SplashState extends State {
	
	public Messenger messenger;
	public Core c;
	private Font stringFont;
	
	public SplashState(Messenger messenger, Core c) {
		this.messenger = messenger;
		this.c = c;
		
		// text for screen
		stringFont = new Font( "SansSerif", Font.PLAIN, 50 );
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	/**
	 * sends message to messenger
	 * @param msgType
	 */
	@Override
	public void handle(String msgType) {
		messenger.send(msgType);
	}

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
		g.setFont(stringFont);
		g.drawString("ASTEROIDS",-150, -0);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		g.drawString("Press 'm' to continue.",-100, 150);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
}