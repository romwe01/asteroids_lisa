package events;

import enumeration.Enumerations.GameEventEnum;

public class GameEvent extends Event{

	private String TYPE = "GameEvent";
	private GameEventEnum ge;
	
	public GameEvent(GameEventEnum g) {
		// TODO Auto-generated constructor stub
		super();
		ge = g;
	}

	public GameEventEnum getGameEvent(){
		return ge;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	public void processEvent(EventListener listener) {
		// TODO Auto-generated method stub
		if (listener instanceof GameEventListener){
			((GameEventListener) listener).OnGameEvent(this);
		}
	}

}
