package game.entity.creature;

import game.engine.GameThread;
import game.entity.Entity;

public abstract class Creatures extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 6.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creatures(GameThread gameThread, float x, float y, int width, int height) {
		super(gameThread, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		x += xMove;
		y += yMove;
	}

	// Getters & Setters
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	

}
