package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Collections;

import score.ScoreComparator;
import core.Core;
import messenger.Messenger;

/**
 * the highscore
 * @author Lisa
 *
 */
public class HighscoreState extends State {
	
	public Messenger messenger;
	public Core c;
	private Font stringFont;
	
	public HighscoreState(Messenger messenger, Core c) {
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
	 * sends a message to the messenger
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
	
	/**
	 * renders a sorted score
	 * @param g
	 */
	public void sortScore(Graphics2D g){
		// no data for sorting
		if(c.scores.isEmpty()){
			return;
		}
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Collections.sort(c.scores, new ScoreComparator());
		int y = -50;

		// top score entries --> max. 5 entries
		int length = c.scores.size() <= 5 ? c.scores.size() : 5;
		
		for (int i = 0; i < length; i++){
			g.drawString(c.scores.get(i).toString(), -30, y);
			y += 25;
		}
	}
	
	public void render()
	{
		//calculate time
		Graphics2D g = c.graphicsSystem.renderSystem.beginUpdate();
		//clear background
		g.clearRect(-c.graphicsSystem.width/2, -c.graphicsSystem.height/2, c.graphicsSystem.width, c.graphicsSystem.height);
		
		g.setFont(stringFont);
		g.drawString("HIGHSCORE",-50, -150);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Points      Date", -50, -70);
		sortScore(g);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Press 'm' to return to the menu.",-50, 150);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
}