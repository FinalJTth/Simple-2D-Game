package game.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.engine.GameThread;

public abstract class Entity implements Collidable {

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
		// System.out.println(String.format("x = %d, y = %d, w = %d, h = %d", bounds.x,
		// bounds.y, bounds.width, bounds.height));
	}

	public abstract void update();

	public abstract void render(Graphics2D g2d);

	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : gameThread.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (xPos + bounds.x + xOffset), (int) (yPos + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	// for debugging purpose
	public Rectangle getCollisionBoundsForCamera(float xOffset, float yOffset) {
		return new Rectangle((int) (xPos + bounds.x + xOffset - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y + yOffset - gameThread.getGameCamera().getyOffset()), bounds.width,
				bounds.height);
	}

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
