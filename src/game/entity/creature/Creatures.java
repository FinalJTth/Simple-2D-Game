package game.entity.creature;

import game.engine.GameThread;
import game.entity.Entity;
import game.tile.Tile;

public abstract class Creatures extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 2.0f;
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
		if (!checkEntityCollision(xMove, 0f))
			moveX();
		if (!checkEntityCollision(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) { // moving right
			int tx = (int) (xPos + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH; // get tile that we're going to
																						// enter

			// detect that if no collision going to happen then move
			if (!collisionWithTile(tx, (int) (yPos + bounds.y) / Tile.TILE_HEIGHT) && // upper right
					!collisionWithTile(tx, (int) (yPos + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) { // lower right
				xPos += xMove;
			} else {
				xPos = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}

		} else if (xMove < 0) { // moving left
			int tx = (int) (xPos + xMove + bounds.x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (yPos + bounds.y) / Tile.TILE_HEIGHT) && // upper left
					!collisionWithTile(tx, (int) (yPos + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) { // lower left
				xPos += xMove;
			} else {
				xPos = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}

	public void moveY() {
		if (yMove < 0) { // moving up
			int ty = (int) (yPos + yMove + bounds.y) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((int) (xPos + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (xPos + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				yPos += yMove;
			} else {
				yPos = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}

		} else if (yMove > 0) { // moving down
			int ty = (int) (yPos + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((int) (xPos + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (xPos + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				yPos += yMove;
			} else {
				yPos = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return !gameThread.getGameState().getWorld().getTile(x, y).isSolid();
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
