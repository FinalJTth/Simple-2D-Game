package game.entity.creature.minion;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.Player;
import game.graphics.Animation;

public abstract class Minion extends Creatures {

	protected static final long MOVE_DELAY = 3000, MOVING_TIME = 500;

	protected int randomInt, chaseRange, attackRange, attackDamage;
	protected boolean isWalking;
	protected String lastFacingDirection;
	protected Animation animationWalk, animationIdle, animationAttack, animationDead;

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

	protected int getThisAndPlayerRange() {
		Player player = gameThread.getWorld().getEntityManager().getPlayer();
		Rectangle rec = player.getBoundingBox();
		double playerX = (double) (player.getxPos() + rec.getX() + rec.getWidth() / 2);
		double playerY = (double) (player.getyPos() + rec.getY() + rec.getHeight() / 2);
		double centerX = (double) (xPos + bounds.x + bounds.width / 2);
		double centerY = (double) (yPos + bounds.y + bounds.height / 2);

		return (int) (Math.sqrt(Math.pow(playerX - centerX, 2) + Math.pow(playerY - centerY, 2)));
	}

	protected void chasePlayer(Player player) {
		float playerX = player.getxPos();
		float playerY = player.getyPos();
		isWalking = true;

		if (xPos + bounds.x < playerX + player.getBoundingBox().getWidth() + player.getBoundingBox().getX()) {
			xMove = speed;
		} else if (xPos + bounds.x > playerX + player.getBoundingBox().getWidth() + player.getBoundingBox().getX()) {
			xMove = -speed;
		}
		if (yPos + bounds.y + bounds.height < playerY + player.getHeight()) {
			yMove = speed;
		} else if (yPos + bounds.y + bounds.height > playerY + player.getHeight()) {
			yMove = -speed;
		} else if (yPos + bounds.y + bounds.height == playerY + player.getHeight()) {
			yMove = 0;
			// System.out.println("y = 0");
		}
		moveWithFixedDirection();
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

	protected String getFacingDirectionFromPlayerPos() {
		float playerX = gameThread.getWorld().getEntityManager().getPlayer().getxPos();
		if (playerX < xPos) {
			return "LEFT";
		}
		return "RIGHT";
	}

	protected void hurtPlayerOnHit() {
		Player player = gameThread.getWorld().getEntityManager().getPlayer();
		if (getCollisionBounds(xMove, 0f).intersects(player.getCollisionBounds(0, 0))) {
			player.hurt(attackDamage);
			player.knockBack(attackDamage, facingDirection);
		} else if (getCollisionBounds(0f, yMove).intersects(player.getCollisionBounds(0, 0))) {
			player.hurt(attackDamage);
			if (yMove > 0)
				player.knockBack(attackDamage, "DOWN");
			else
				player.knockBack(attackDamage, "UP");
		}

	}
}
