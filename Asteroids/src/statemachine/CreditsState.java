package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;
import core.Core;
import messenger.Messenger;

/**
 * the credits
 * @author Lisa
 *
 */
public class CreditsState extends State {
	
	public Messenger messenger;
	public Core c;
	private Font stringFont;
	
	public CreditsState(Messenger messenger, Core c) {
		this.messenger = messenger;
		this.c = c;
		
		// text for screen
		stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	/**
	 * sends message to messenger
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
		
		g.setFont(stringFont);
		g.drawString("CREDITS",-50, -150);
		g.setFont(new Font("SansSerif", Font.PLAIN, 25));
		g.drawString("Christina Bauer",-50, -50);
		g.drawString("Lisa Frech",-50, 0);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Press 'm' to return to the menu.",-50, 150);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
}