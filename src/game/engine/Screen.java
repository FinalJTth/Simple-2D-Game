package game.engine;

import java.awt.Graphics2D;

// Make you able to draw on screen
public abstract class Screen {
	
	private ScreenFactory screenFactory;	// Handle everything on screen
	
	public Screen(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}
	
	public abstract void onCreate();
		
	public abstract void onUpdate();	// Update screen
		
	public abstract void onDraw(Graphics2D g2d);	// 
	
	public ScreenFactory getScreenFactory() {
		return this.screenFactory;
	}
}
