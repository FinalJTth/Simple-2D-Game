package game.entity.creature.minion;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.Player;
import game.tile.Tile;
import javafx.scene.shape.Ellipse;

public abstract class Minion extends Creatures {

	protected static final long MOVE_DELAY = 3000, MOVING_TIME = 500;

	protected int randomInt, chaseRange, attackRange, attackDamage;
	protected boolean isWalking;
	protected String lastFacingDirection;

	// for randomly move purpose
	protected long timer, lastTime;
	protected boolean isMovable, isMoved;

	public Minion(GameThread gameThread, float xPos, float yPos, int width, int height, int health, float speed,
			int attackRange, int attackDamage) {
		super(gameThread, xPos, yPos, width, height, health, speed);

		this.attackRange = attackRange;
		this.attackDamage = attackDamage;

		isMovable = true;
		isMoved = false;
		isAlive = true;
	}

	protected boolean detectPlayerInChaseRange(Player player) {
		Rectangle rec = player.getBoundingBox();
		double playerX = (double) (player.getxPos() + rec.getX() + rec.getWidth() / 2);
		double playerY = (double) (player.getyPos() + rec.getY() + rec.getHeight() / 2);
		double centerX = (double) (xPos + bounds.x + bounds.width / 2);
		double centerY = (double) (yPos + bounds.y + bounds.height / 2);

		if (Math.sqrt(Math.pow(playerX - centerX, 2) + Math.pow(playerY - centerY, 2)) > chaseRange) {
			return false;
		}
		return true;
	}

	protected void chasePlayer(Player player) {
		float playerX = player.getxPos();
		float playerY = player.getyPos();
		isWalking = true;

		if (xPos + bounds.width + bounds.x == playerX) {
			xMove = 0;
		} else if (xPos + bounds.x < playerX + player.getWidth()) {
			xMove = speed;
		} else if (xPos + bounds.x > playerX + player.getWidth()) {
			xMove = -speed;
		} else if (xPos + bounds.x == playerX + player.getWidth()) {
			xMove = 0;
			// System.out.println("x = 0");
		}
		if (yPos + bounds.y + bounds.height < playerY + player.getHeight()) {
			yMove = speed;
		} else if (yPos + bounds.y + bounds.height > playerY + player.getHeight()) {
			yMove = -speed;
		} else if (yPos + bounds.y + bounds.height == playerY + player.getHeight()) {
			yMove = 0;
			// System.out.println("y = 0");
		}
		move();
		if (xMove == 0 && yMove == 0) {
			isWalking = false;
		}
	}

	protected void moveRandomly() {
		if (isMovable || !isMoved) {
			if (randomInt == 1) {
				xMove = speed;
				yMove = 0;
				facingDirection = "RIGHT";
			} else if (randomInt == 2) {
				xMove = -speed;
				yMove = 0;
				facingDirection = "LEFT";
			} else if (randomInt == 3) {
				yMove = speed;
				xMove = 0;
				facingDirection = "LEFT";
			} else {
				yMove = -speed;
				xMove = 0;
				facingDirection = "RIGHT";
			}
			move();
			isMovable = false;
			movingTimeCounter();
		}
		moveDelayCounter();
	}

	protected void moveDelayCounter() {
		if (isMoved) {
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();

			if (timer > MOVE_DELAY) {
				timer = 0;
				isMovable = true;
				isMoved = false;
				isWalking = true;
				randomInt = (int) (Math.random() * 4);
			}
		}
	}

	protected void movingTimeCounter() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > MOVING_TIME) {
			timer = 0;
			isMoved = true;
			isMovable = false;
			isWalking = false;
		}
	}

	// override moveX and moveY for set facingDirection according to player position
	@Override
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

	@Override
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

	protected String getFacingDirectionFromPlayerPos() {
		float playerX = gameThread.getWorld().getEntityManager().getPlayer().getxPos();
		if (playerX < xPos) {
			return "LEFT";
		}
		return "RIGHT";
	}

	protected void hurtPlayerOnHit() {
		Player player = gameThread.getWorld().getEntityManager().getPlayer();
		Rectangle bbox = player.getBoundingBox();
		if (getCollisionBounds(0f, 0f).intersects(player.getCollisionBounds(player.getxMove(), 0))
				|| getCollisionBounds(0f, 0f).intersects(player.getCollisionBounds(0, player.getyMove()))) {
			player.hurt(attackDamage);
		}

	}

	public abstract void attack(Player player);

}
