package game.entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.engine.GameThread;

public abstract class Entity {
	
	protected GameThread gameThread;
	protected float xPos, yPos;
	protected int width, height;
	protected Rectangle bounds;
	
	public Entity(GameThread gameThread, float xPos, float yPos, int width, int height) {
		this.gameThread = gameThread;
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics2D g2d);
	
	// Getters & Setters
	
	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
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
