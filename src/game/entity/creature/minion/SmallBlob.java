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
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public class SmallBlob extends Minion implements CrystalAttacker {

	private static final int DEFAULT_WIDHT = 50, DEFAULT_HEIGHT = 50;

	private TemporaryAnimation animationExplode;
	private boolean isExploding;

	public SmallBlob(GameThread gameThread, float xPos, float yPos) {
		super(gameThread, xPos, yPos, 100, 100, 150, 1.0f, 100, 10);

		chaseRange = 300;
		isExploding = false;
		// System.out.println(String.format("x : %d, y : %d", bounds.x, bounds.y));
		// System.out.println(String.format("w : %d, h : %d", bounds.width,
		// bounds.height));

		bounds.x = width / 4 + 5;
		bounds.y = height / 2 - 20;
		bounds.width = width / 2 - 10;
		bounds.height = height / 2 - 10;

		animationAttack = new Animation(100, Assets.small_blob_attack);
		animationIdle = new Animation(100, Assets.small_blob_idle);
		animationWalk = new Animation(100, Assets.small_blob_walk);
		animationDead = new TemporaryAnimation(100, Assets.small_blob_dead);
		animationExplode = new TemporaryAnimation(1000 / 60, Assets.small_blob_explosion);
	}

	@Override
	public void update() {
		if (isAlive && !isExploding) {
			animationIdle.timerCounter();
			animationWalk.timerCounter();
			// moving mechanism
//			if (detectPlayerInChaseRange(gameThread.getWorld().getEntityManager().getPlayer())) {
//				facingDirection = getFacingDirectionFromPlayerPos();
//				chasePlayer(gameThread.getWorld().getEntityManager().getPlayer());
//				hurtPlayerOnHit();
//			} else {
//				moveRandomly();
//			}
			if (getDistanceToCrystal() < 125) {
				attackCrystal();
			} else
				moveToCrystal();
		} else if (isExploding) {
			animationExplode.timerCounter();
			if (animationExplode.isDone())
				gameThread.getWorld().getEntityManager().removeEntity(this);
		} else {
			animationDead.timerCounter();
			// allow to play animationDead before remove this entity
			if (((TemporaryAnimation) animationDead).isDone()) {
				gameThread.getWorld().getEntityManager().removeEntity(this);
			}
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if (isExploding) {
			g2d.drawImage(animationExplode.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()) - 40,
					(int) (yPos - gameThread.getGameCamera().getyOffset()) - 40, width * 2, height * 2, null);
		} else if (isAlive) {
			g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);

			g2d.setColor(Color.red);
			// draw healthBar
			g2d.fillRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - 20 - gameThread.getGameCamera().getyOffset()), (int) getHealthBarWidth(),
					8);
			g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		} else {
			g2d.drawImage(animationDead.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		}
	}

	@Override
	public void attack() {
		isCastingAttack = true;

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

	@Override
	public void attackCrystal() {
		isExploding = true;
		gameThread.getWorld().getEntityManager().getCrystal().hurt(50);
	}

	@Override
	public float getDistanceToCrystal() {
		float crystalX = gameThread.getWorld().getEntityManager().getCrystal().getxPos();
		float crystalY = gameThread.getWorld().getEntityManager().getCrystal().getyPos();

		return (float) (Math.sqrt(Math.pow(xPos - crystalX, 2) + Math.pow(yPos - crystalY, 2)));
	}

	@Override
	public void moveToCrystal() {
		float crystalX = gameThread.getWorld().getEntityManager().getCrystal().getxPos();
		float crystalY = gameThread.getWorld().getEntityManager().getCrystal().getyPos();
		isWalking = true;

		if (xPos + bounds.x < crystalX + gameThread.getWorld().getEntityManager().getCrystal().getWidth()) {
			xMove = speed;
		} else if (xPos + bounds.x > crystalX + gameThread.getWorld().getEntityManager().getCrystal().getWidth()) {
			xMove = -speed;
		}
		if (yPos + bounds.y + bounds.height < crystalY
				+ gameThread.getWorld().getEntityManager().getCrystal().getHeight()) {
			yMove = speed;
		} else if (yPos + bounds.y + bounds.height > crystalY
				+ gameThread.getWorld().getEntityManager().getCrystal().getHeight()) {
			yMove = -speed;
		} else if (yPos + bounds.y + bounds.height == crystalY
				+ gameThread.getWorld().getEntityManager().getCrystal().getHeight()) {
			yMove = 0;
			// System.out.println("y = 0");
		}
		moveWithFixedDirection();
		if (xMove == 0 && yMove == 0) {
			isWalking = false;
		}
	}

}
