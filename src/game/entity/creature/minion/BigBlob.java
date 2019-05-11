package game.entity.creature.minion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.graphics.Animation;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public class BigBlob extends CrystalAttackingMinion {

	private static final int DEFAULT_WIDHT = 200, DEFAULT_HEIGHT = 200;
	private Animation animationWalk, animationIdle;
	private TemporaryAnimation animationAttack;
	private long attackCoolDownTimer, lastTimeCoolDown;

	public BigBlob(GameThread gameThread, float xPos, float yPos, boolean collidable) {
		super(gameThread, xPos, yPos, DEFAULT_WIDHT, DEFAULT_HEIGHT, 3000, 1.0f, 100, 10, collidable);

		chaseRange = 300;
		// System.out.println(String.format("x : %d, y : %d", bounds.x, bounds.y));
		// System.out.println(String.format("w : %d, h : %d", bounds.width,
		// bounds.height));

		bounds.x = width / 4;
		bounds.y = height / 2 + 10;
		bounds.width = width / 2 - 10;
		bounds.height = height / 2 - 10;

		animationAttack = new TemporaryAnimation(100, Assets.big_blob_attack);
		animationIdle = new Animation(100, Assets.big_blob_idle);
		animationWalk = new Animation(100, Assets.big_blob_walk);

	}

	@Override
	public void update() {
		if (isAlive) {
			animationIdle.timerCounter();
			animationWalk.timerCounter();
			countAttackCooldown();
			setFacingDirectionFromCrystalPos();
			// moving mechanism
//			if (detectPlayerInChaseRange(gameThread.getWorld().getEntityManager().getPlayer())) {
//				facingDirection = getFacingDirectionFromPlayerPos();
//				chasePlayer(gameThread.getWorld().getEntityManager().getPlayer());
//				hurtPlayerOnHit();
//			} else {
//				moveRandomly();
//			}
			if (isCastingAttack) {
				animationAttack.timerCounter();

				if (animationAttack.isDone()) {
					animationAttack.reset();
					isCastingAttack = false;
				}
			} else {
				if (getDistanceToCrystal() < 160) {
					isWalking = false;
					if (attackCoolDownTimer == 0)
						attackCrystal();
				} else {
					moveToCrystal();
				}
			}

		} else {
			gameThread.getWorld().getEntityManager().removeEntity(this);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if (isAlive) {
			g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);

			g2d.setColor(Color.red);
			// draw healthBar
			g2d.fillRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - 20 - gameThread.getGameCamera().getyOffset()), (int) getHealthBarWidth(),
					8);
			g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}

	}

	@Override
	protected BufferedImage getCurrentAnimationFrame() {
		if (isCastingAttack) {

			if (facingDirection == "LEFT") {
				return animationAttack.getCurrentFrame();
			} else if (facingDirection == "RIGHT") {
				return Utils.flipImageHorizontally(animationAttack.getCurrentFrame());
			} else if (facingDirection == "UP") {
				return animationAttack.getCurrentFrame();
			} else {
				return Utils.flipImageHorizontally(animationAttack.getCurrentFrame());
			}
		}
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
	
	private void countAttackCooldown() {
		attackCoolDownTimer += System.currentTimeMillis() - lastTimeCoolDown;
		lastTimeCoolDown = System.currentTimeMillis();
		if (attackCoolDownTimer > 3000) {
			attackCoolDownTimer = 0;
		}
	}

	@Override
	public void attackCrystal() {
		isCastingAttack = true;
		gameThread.getWorld().getEntityManager().getCenterCrystal().hurt(50);
	}
}
