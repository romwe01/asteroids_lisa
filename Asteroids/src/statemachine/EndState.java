package statemachine;

import messenger.Messenger;

public class EndState extends State {
	
	public Messenger messenger;
	
	public EndState(Messenger messenger) {
		this.messenger = messenger;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		System.out.println("EndState activated");
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		System.out.println("EndState deactivated");
	}

	@Override
	public void handle(String msgType) {
		System.out.println("handle EndState");
		messenger.send(msgType);
	}

	@Override
	public void update() {
		System.out.println("update EndState");
		messenger.send("quit");
	}
	
}