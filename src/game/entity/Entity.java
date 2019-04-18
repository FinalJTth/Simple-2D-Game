package game.entity;


import java.awt.Graphics2D;
import game.engine.GameThread;

public abstract class Entity {
	
	protected GameThread gameThread;
	protected float x, y;
	protected int width, height;
	
	public Entity(GameThread gameThread, float x, float y, int width, int height) {
		this.gameThread = gameThread;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g2d);
	
	// Getters & Setters
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
