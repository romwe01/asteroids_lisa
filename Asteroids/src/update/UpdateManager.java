package update;

import java.util.ArrayList;

public class UpdateManager {

	public ArrayList<Updatable> updateClasses;
	
	public UpdateManager() {
		updateClasses = new ArrayList<Updatable>();
	}
	
	/**
	 * Adds class to updateManager
	 * @param up
	 * @return
	 */
	public boolean addClass(Updatable up){
		if (!updateClasses.contains(up)){
			updateClasses.add(up);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Removes class from updateManager
	 * @param up
	 */
	public void removeClass(Updatable up){
		if (updateClasses.contains(up)){
			updateClasses.remove(up);
		}
	}
	
	/**
	 * calls update function of all added classes
	 */
	public void UpdateAll(){
		for (Updatable i: updateClasses ){
			i.update();
		}
	}

}
