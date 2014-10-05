package statemachine;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.lang.reflect.Array;

import core.Core;
import messenger.Messenger;

public class MenuState extends State {
	
	public Messenger messenger;
	public Core c;
	public StateMachine sm;
	private Font stringFont;
	private int heightOfSelected;
	private int currPos;
	private int height;
	private int indent;
	private String[] transitionMsg = { "play", "highscore", "credits", "exit" };
	
	public MenuState(Messenger messenger, Core c, StateMachine sm) {
		this.messenger = messenger;
		this.c = c;
		this.currPos = 0;
		this.sm = sm;
		this.height = 50;
		this.indent = -50;
		// text styles
		stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
		
	}

	@Override
	public void activate() {
		System.out.println("MenuState activated");
		this.heightOfSelected = -this.height + (this.currPos * this.height);
	}

	@Override
	public void deactivate() {
		System.out.println("MenuState deactivated");
	}

	@Override
	public void handle(String msgType) {
		
		if(msgType == "down"){					// iterate through the menu items
			if(this.currPos == (this.transitionMsg.length-1)){
				this.currPos = 0;
				this.heightOfSelected = -this.height;
			} else {
				this.currPos++;
				this.heightOfSelected += this.height;
			}
			
			System.out.println("currPos " + this.currPos);
		} else if (msgType == "up") {			// iterate through the menu items
			if(this.currPos == 0){
				this.currPos = this.transitionMsg.length-1;
				this.heightOfSelected = this.height*2;
			} else {
				this.currPos--;
				this.heightOfSelected -= this.height;
			}
			System.out.println("currPos " + this.currPos);
		} else if (msgType == "enter"){			// transition to state of selected menu item
			System.out.println("enter: currPos " + this.currPos);
			System.out.println("transition: " + transitionMsg[this.currPos]);
			sm.handle(transitionMsg[this.currPos]);
		}
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
		g.drawString("MENU",this.indent, -this.height*2);
		
		// menu items
		g.drawString("Play",this.indent, -this.height);
		g.drawString("Highscore",this.indent, 0);
		g.drawString("Credits",this.indent, this.height);
		g.drawString("Exit",this.indent, this.height*2);
		
		// marker of selected menu item
		g.drawString(">", this.indent-30, heightOfSelected);
		
		g.drawString("Press 'enter' to continue.",this.indent, this.height*4);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
	
}