package game.entity.creature.minion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.entity.creature.Player;
import game.graphics.Animation;
import game.graphics.Assets;
import game.utils.Utils;

public class BigBlob extends Minion {

	private Animation animationAttack, animationWalk, animationIdle;

	public BigBlob(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);

		health = 300;
		speed = 1.0f;
		chaseRange = 300;
		// System.out.println(String.format("x : %d, y : %d", bounds.x, bounds.y));
		// System.out.println(String.format("w : %d, h : %d", bounds.width,
		// bounds.height));

		bounds.x = width / 4;
		bounds.y = height / 2 + 10;
		bounds.width = width / 2 - 10;
		bounds.height = height / 2 - 10;

		animationAttack = new Animation(100, Assets.big_blob_attack);
		animationIdle = new Animation(100, Assets.big_blob_idle);
		animationWalk = new Animation(100, Assets.big_blob_walk);

	}

	@Override
	public void update() {
		animationIdle.timerCounter();
		animationWalk.timerCounter();
		
		if (detectPlayerInChaseRange(gameThread.getWorld().getEntityManager().getPlayer())) {
			facingDirection = getFacingDirectionFromPlayerPos();
			chasePlayer(gameThread.getWorld().getEntityManager().getPlayer());
			System.out.println("Chase");
		} else {
			System.out.println("Patrol");
			moveRandomly();
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if (isAlive) {
			g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
			// draw collision checking (bounding) box
			g2d.setColor(Color.red);
			g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
			g2d.setColor(Color.blue);
			g2d.drawOval((int) (xPos + bounds.x + bounds.width - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), 3, 3);
		}

	}

	@Override
	public void hurt(int damage) {
		health -= damage;
		if (health <= 0) {
			isAlive = false;
			gameThread.getWorld().getEntityManager().removeEntity(this);
			System.out.println("DEAD");
		}
	}

	@Override
	public void attack(Player player) {

	}

	@Override
	protected BufferedImage getCurrentAnimationFrame() {
		if (isWalking) {
			if (facingDirection == "LEFT") {
				return animationWalk.getCurrentFrame();
			} else if (facingDirection == "RIGHT") {
				return Utils.flipImageHorizontally(animationWalk.getCurrentFrame());
			} else if (facingDirection == "UP") {
				return animationWalk.getCurrentFrame();
			} else {
				return Utils.flipImageHorizontally(animationWalk.getCurrentFrame());
			}
		} else {
			if (facingDirection == "LEFT") {
				return animationIdle.getCurrentFrame();
			} else if (facingDirection == "RIGHT") {
				return Utils.flipImageHorizontally(animationIdle.getCurrentFrame());
			} else if (facingDirection == "UP") {
				return animationIdle.getCurrentFrame();
			} else {
				return Utils.flipImageHorizontally(animationIdle.getCurrentFrame());
			}
		}

	}

}
