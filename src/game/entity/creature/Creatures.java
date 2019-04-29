package game.entity.creature;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.entity.Entity;
import game.tile.Tile;

public abstract class Creatures extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 5.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected int health;
	protected final int maxHealth;
	protected float speed;
	protected float xMove, yMove;
	protected String facingDirection;
	protected boolean isCastingAttack, isAlive;

	public Creatures(GameThread gameThread, float xPos, float yPos, int width, int height, int health, float speed) {
		super(gameThread, xPos, yPos, width, height);
		this.health = health;
		maxHealth = health;
		this.speed = speed;
		xMove = 0;
		yMove = 0;
		facingDirection = "DOWN";

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
			// can't change direction while casting attack
			if (!isCastingAttack)
				facingDirection = "RIGHT";

		} else if (xMove < 0) { // moving left
			int tx = (int) (xPos + xMove + bounds.x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (yPos + bounds.y) / Tile.TILE_HEIGHT) && // upper left
					!collisionWithTile(tx, (int) (yPos + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) { // lower left
				xPos += xMove;
			} else {
				xPos = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
			if (!isCastingAttack)
				facingDirection = "LEFT";
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
			if (!isCastingAttack)
				facingDirection = "UP";

		} else if (yMove > 0) { // moving down
			int ty = (int) (yPos + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((int) (xPos + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (xPos + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				yPos += yMove;
			} else {
				yPos = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
			if (!isCastingAttack)
				facingDirection = "DOWN";
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return !gameThread.getGameState().getWorld().getTile(x, y).isSolid();
	}

	public void hurt(int damage) {
		health -= damage;
		if (health <= 0) {
			isAlive = false;
			gameThread.getWorld().getEntityManager().removeEntity(this);
			System.out.println("DEAD");
		}
	}

	protected abstract BufferedImage getCurrentAnimationFrame();

	// Getters & Setters

	public float getHealthBarWidth() {
		// health, maxHealth, bounds.width is int, if dont cast float will calculate as int
		return (float) health / maxHealth * bounds.width;
	}
	
	public Rectangle getBoundingBox() {
		return bounds;
	}

	public void doneAttacking() {
		isCastingAttack = false;
	}

	public String getFacingDirection() {
		return facingDirection;
	}

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
