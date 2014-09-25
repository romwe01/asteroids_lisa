package events;

public class KeyPressedEvent extends Event {

	private String TYPE = "KeyPressed";
	private int m_KeyCode;
	
	public KeyPressedEvent(int KeyCode){
		super();
		m_KeyCode = KeyCode;
	}
	
	public int getKeyCode(){
		return m_KeyCode;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void processEvent(EventListener listener) {
		if (listener instanceof KeyPressedListener){
			((KeyPressedListener)listener).onKeyPressed(this);
		}
	}



}
