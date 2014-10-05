package graphics;

public class GraphicsConfig {

	public int Width;
	public int Height;
	public int FPS;
	public boolean Fullscreen;
	
	public GraphicsConfig(int width, int height, int fps, boolean fullscreen){
		this.Width = width;
		this.Height = height; 
		this.FPS = fps;
		this.Fullscreen = fullscreen;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public boolean isFullscreen() {
		return Fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		Fullscreen = fullscreen;
	}	
}
