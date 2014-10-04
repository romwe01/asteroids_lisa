package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;

import core.Core;
import messenger.Messenger;

public class MenuState extends State {
	
	public Messenger messenger;
	public Core c;
	private Font stringFont;
	
	public MenuState(Messenger messenger, Core c) {
		this.messenger = messenger;
		this.c = c;
		
		// text for menu screen
		stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		System.out.println("MenuState activated");
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		System.out.println("MenuState deactivated");
	}

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
		g.drawString("MENU",0, 0);
		g.drawString("Presse SPACE to continue.",0, 100);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
}