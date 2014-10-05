package graphics;

import java.awt.Graphics2D;

public interface Screen {

	public void open(int width, int height);
	
	public void close();
	
	public Graphics2D beginUpdate();
	
	public void endUpdate();
}
