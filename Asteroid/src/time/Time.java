package time;

import core.Core;
import events.GameEvent;
import events.GameEventListener;

public class Time implements GameEventListener {

	private long gameStartupTime;
	private Core core;
	
	
	public Time (Core c){
		this.core = c;
		setUp();
		startGameTime();
	}

	public float getTotalGameTime(){
		return System.currentTimeMillis() - gameStartupTime;
	}
	
	public void startGameTime(){
		gameStartupTime = System.currentTimeMillis();
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "GameEvent";
	}


	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		//core.eventManager.addListener(this, "GameEvent");
	}


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		//core.eventManager.removeListener(this,"GameEvent");
	}


	@Override
	public void OnGameEvent(GameEvent e) {
		// TODO Auto-generated method stub
		
	}
}
