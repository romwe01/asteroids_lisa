package update;

import java.util.ArrayList;

public class UpdateManager {

	public ArrayList<Updatable> updateClasses;
	
	public UpdateManager(){
		updateClasses = new ArrayList<Updatable>();
	}
	
	public boolean addClass(Updatable up){
		if (!updateClasses.contains(up)){
			updateClasses.add(up);
			return true;
		}else{
			return false;
		}
	}
	
	public void removeClass(Updatable up){
		if (updateClasses.contains(up)){
			updateClasses.remove(up);
		}
	}
	
	public void UpdateAll(){
		for (Updatable i: updateClasses){
			i.update();
		}
	}
}
