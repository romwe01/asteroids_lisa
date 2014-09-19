package events;

public interface GameEventListener extends EventListener {

	void OnGameEvent(GameEvent e);
}
