package statemachine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Collections;

import score.ScoreComparator;
import core.Core;
import messenger.Messenger;

public class HighscoreState extends State {
	
	public Messenger messenger;
	public Core c;
	private Font stringFont;
	
	public HighscoreState(Messenger messenger, Core c) {
		this.messenger = messenger;
		this.c = c;
		
		// text for splash screen
		stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
	}

	@Override
	public void activate() {
		System.out.println("HighscoreState activated");
	}

	@Override
	public void deactivate() {
		System.out.println("HighscoreState deactivated");
	}

	@Override
	public void handle(String msgType) {
		messenger.send(msgType);
	}

	@Override
	public void update() {
		render();
	}
	
	public void sortScore(Graphics2D g){
		g.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Collections.sort(c.scores, new ScoreComparator());
		int y = -50;
		for (int i = 0; i < 5; i++){
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
		g.drawString("HIGHSCORE",-50, -100);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		g.drawString("Punkte      Datum", -50, -70);
		sortScore(g);
		
		g.drawString("Press 'm' to return to the menu.",-50, 100);
		
		c.graphicsSystem.renderSystem.endUpdate();
	}
	
}