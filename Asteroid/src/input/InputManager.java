package input;

import java.awt.event.*;

import core.Core;
import events.KeyPressedEvent;

public class InputManager implements KeyListener{

	private static final int NUM_KEY_CODES = 256;
	private Core core;
	private GameAction[] keyActions;
	
	public InputManager(Core core){
		this.core = core;
		keyActions = new GameAction[NUM_KEY_CODES];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() >= 0 && e.getKeyCode() < 256){
			if (keyActions[e.getKeyCode()] != null){
				keyActions[e.getKeyCode()].press();
			}
			//core.eventManager.addEvent(new KeyPressedEvent(e.getKeyCode()));			
		}
		e.consume();
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() >= 0 && e.getKeyCode() < 256){
			if (keyActions[e.getKeyCode()] != null)
				keyActions[e.getKeyCode()].release();
		}
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		e.consume();
	}
	
	//maps GameAction to a key
	public void mapToKey(GameAction gameAction, int keyCode){
		keyActions[keyCode] = gameAction;
	}

}
