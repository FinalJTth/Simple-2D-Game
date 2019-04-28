package game.entity.creature.minion;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.Entity;
import game.entity.creature.Creatures;
import game.entity.creature.Player;

public abstract class Minion extends Creatures {

	protected static final long MOVE_DELAY = 3000, MOVING_TIME = 500;

	// private final Player player;
	protected int randomInt;
	protected long timer, lastTime;
	protected boolean isMovable, isMoved;

	public Minion(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);

		isMovable = true;
		isMoved = false;
		// player = gameThread.getWorld().getEntityManager().getPlayer();
	}

	protected void moveToPlayerDiagonally(Player player) {
		float playerX = player.getxPos();
		float playerY = player.getyPos();
		float distancePlayerAndMinionX = playerX - xPos;
		float distancePlayerAndMinionY = playerY - yPos;

		xMove = (distancePlayerAndMinionX / distancePlayerAndMinionX + distancePlayerAndMinionY) * speed;
		yMove = (distancePlayerAndMinionY / distancePlayerAndMinionX + distancePlayerAndMinionY) * speed;

		if (!checkEntityCollision(xMove, 0f))
			moveX();
		if (!checkEntityCollision(0f, yMove))
			moveY();
	}

	protected void moveRandomly() {
		if (isMovable || !isMoved) {
			if (randomInt == 1) {
				xMove = speed;
				yMove = 0;
			} else if (randomInt == 2) {
				xMove = -speed;
				yMove = 0;
			} else if (randomInt == 3) {
				yMove = speed;
				xMove = 0;
			} else {
				yMove = -speed;
				xMove = 0;
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
				randomInt = (int) (Math.random() * 4);
				System.out.println(randomInt);
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
		}
	}

	// not yet usable
//	protected boolean[] getMovableDirection() {
//		boolean[] out = new boolean[4];
//		for (int i = 0; i < 4; i++)
//			out[i] = false;
//		if (!checkEntityCollision(speed * MOVING_TIME / 1000, 0)) {
//			out[0] = true;
//		}
//		if (!checkEntityCollision(-speed * MOVING_TIME / 1000, 0)) {
//			out[1] = true;
//		}
//		if (!checkEntityCollision(0, speed * MOVING_TIME / 1000)) {
//			out[2] = true;
//		}
//		if (!checkEntityCollision(0, -speed * MOVING_TIME / 1000)) {
//			out[3] = true;
//		}
//		return out;
//	}
//
//	protected String getMovableDirectionAsText() {
//		boolean[] in = getMovableDirection();
//		String out = "";
//		for (int i = 0; i < 4; i++) {
//			if (in[i]) {
//				if (i == 0)
//					out = out + "RIGHT ";
//				else if (i == 1)
//					out = out + "LEFT ";
//				else if (i == 2)
//					out = out + "DOWN ";
//				else
//					out = out + "UP";
//
//			}
//		}
//		return out;
//	}

	public abstract void attack(Player player);

}
