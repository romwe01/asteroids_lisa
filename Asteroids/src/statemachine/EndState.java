package statemachine;

import messenger.Messenger;

/**
 * state for exiting game
 * @author Lisa
 *
 */
public class EndState extends State {
	
	public Messenger messenger;
	
	public EndState(Messenger messenger) {
		this.messenger = messenger;
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	/**
	 * sends message to messenger
	 */
	@Override
	public void handle(String msgType) {
		messenger.send(msgType);
	}

	/**
	 * quits the game and closes window
	 */
	@Override
	public void update() {
		messenger.send("quit");
	}
	
}