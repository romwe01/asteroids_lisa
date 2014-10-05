package emmitter;

public class EmmitterSettings {

	private static final int MAXSIZE = 70;
	private static final int MINSIZE = 5;
	private static final float SPIN_MAX = 0.08f;
	private static final float MIN_SPEED = 1.0f;
	private static final float MAX_SPEED = 3.0f;
	private static final float FREQUENCY = 1.0f;
	
	
	int maxsize;
	int minsize;
	float spin_max;
	float min_speed;
	float max_speed;
	float frequency;
	
	/*
	 * default Constructor
	 */
	public EmmitterSettings(){
		maxsize = MAXSIZE;
		minsize = MINSIZE;
		spin_max = SPIN_MAX;
		min_speed = MIN_SPEED;
		max_speed = MAX_SPEED;
		frequency = FREQUENCY;
	}
	
	public EmmitterSettings(int maxsize, int minsize, float spin_max, float min_speed, float max_speed, float frequency){
		this.maxsize = maxsize;
		this.minsize = minsize;
		this.spin_max = spin_max;
		this.min_speed = min_speed;
		this.max_speed = max_speed;
		this.frequency = frequency;
	}	
	
	public int getMaxsize() {
		return maxsize;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
	}

	public int getMinsize() {
		return minsize;
	}

	public void setMinsize(int minsize) {
		this.minsize = minsize;
	}

	public float getSpin_max() {
		return spin_max;
	}

	public void setSpin_max(float spin_max) {
		this.spin_max = spin_max;
	}


	public float getMin_speed() {
		return min_speed;
	}

	public void setMin_speed(float min_speed) {
		this.min_speed = min_speed;
	}

	public float getMax_speed() {
		return max_speed;
	}

	public void setMax_speed(float max_speed) {
		this.max_speed = max_speed;
	}

}
