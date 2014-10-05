package input;



public class GameAction {

	//isPressed() returns true when key pressed
	public static final int NORMAL = 0;
	
	//isPressed() returns true only after the key is first pressed, released and pressed again
	public static final int DETECT_INITIAL_PRESS_ONLY = 1;
	
	private static final int STATE_RELEASED = 0;
	private static final int STATE_PRESSED = 1;
	private static final int STATE_WAITING_FOR_RELEASE = 2;
	
	private String name;
	private int behavior;
	private int amount;
	private int state;
	
	/**
	 * create a new GameAction with the normal behavior
	 * @param name
	 */
	public GameAction(String name){
		this(name, NORMAL);
	}
	
	/**
	 * create a new GameAction with the specified behavior
	 * @param name
	 * @param behavior
	 */
	public GameAction (String name, int behavior){
		this.name = name;
		this.behavior = behavior;
		reset();
	}
	
	public String getName(){
		return name;
	}
	
	public void reset(){
		state = STATE_RELEASED;
		amount = 0;
	}
	
	public synchronized void tap(){
		press();
		release();
	}
	
	//signals pressed key
	public synchronized void press(){
		press(1);
	}
	
	public synchronized void press(int amount){
		if (state != STATE_WAITING_FOR_RELEASE){
			this.amount += amount;
			state = STATE_PRESSED;
		}
	}
	
	//signals the key was released
	public synchronized void release(){
		state = STATE_RELEASED;
	}
	
	//signals whether key pressed or not
	public synchronized boolean isPressed(){
		return (getAmount() != 0);
	}
	
	
	public synchronized int getAmount(){
		int retVal = amount;
		if (retVal != 0){
			if (state == STATE_RELEASED){
				amount = 0;
			}
			else if(behavior == DETECT_INITIAL_PRESS_ONLY){
				state = STATE_WAITING_FOR_RELEASE;
				amount = 0;
			}
		}
		return retVal;
	}
	
}
