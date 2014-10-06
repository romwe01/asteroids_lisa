package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;

import core.Core;
import messenger.Messenger;

/**
 * the menu
 * @author Lisa
 *
 */
public class MenuState extends State {
	
	public Messenger messenger;
	public Core c;
	public StateMachine sm;
	private Font stringFont;
	private int heightOfSelected;
	private int currPos;	// index of selected message in list
	private int height;		// height of a menu item
	private int indent;		// indent of a menu item
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

	/**
	 * defines position next to selected menu item
	 */
	@Override
	public void activate() {
		this.heightOfSelected = -this.height + (this.currPos * this.height);
	}

	@Override
	public void deactivate() {
	}

	/**
	 * handles navigation through menu items
	 * @param msgType
	 */
	@Override
	public void handle(String msgType) {
		
		if(msgType == "down"){					// iterate through menu items
			if(this.currPos == (this.transitionMsg.length-1)){
				this.currPos = 0;
				this.heightOfSelected = -this.height;
			} else {
				this.currPos++;
				this.heightOfSelected += this.height;
			}
		} else if (msgType == "up") {			// iterate through menu items reversed
			if(this.currPos == 0){
				this.currPos = this.transitionMsg.length-1;
				this.heightOfSelected = this.height*2;
			} else {
				this.currPos--;
				this.heightOfSelected -= this.height;
			}
		} else if (msgType == "enter"){			// transition to state of selected menu item
			sm.handle(transitionMsg[this.currPos]);
		}
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
		g.drawString("MENU",this.indent, -this.height*3);
		
		// menu items
		g.drawString("Play",this.indent, -this.height);
		g.drawString("Highscore",this.indent, 0);
		g.drawString("Credits",this.indent, this.height);
		g.drawString("Exit",this.indent, this.height*2);
		
		// marker of selected menu item
		g.drawString(">", this.indent-30, heightOfSelected);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Use arrow keys to change selection.",this.indent, this.height*4);
		g.drawString("Press 'enter' to open menu item.",this.indent, (int) (this.height*4.5));
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
	
}