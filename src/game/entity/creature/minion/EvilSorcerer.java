package game.entity.creature.minion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.attacks.FireBallSpell;
import game.entity.creature.attacks.ProjectileAttacks;
import game.graphics.Animation;
import game.graphics.Assets;
import game.utils.Utils;

public class EvilSorcerer extends Minion {

	private static final int DEFAULT_ATTACK_RANGE = 250;

	private int attackCoolDown = 0;
	private long attackCoolDownTimer, lastTimeCoolDown;
	private ProjectileAttacks fireball;


	public EvilSorcerer(GameThread gameThread, float xPos, float yPos, int health, boolean collidable) {
		super(gameThread, xPos, yPos, 300, 300, health, 2.0f, 100, 50, collidable);

		chaseRange = 800;
		isCastingAttack = false;

		bounds.x = 100;
		bounds.y = 80;
		bounds.width = 80;
		bounds.height = 200;

		animationWalk = new Animation(100, Assets.evil_sorcerer_slide);
		animationAttack = new Animation(100, Assets.evil_sorcerer_attack);
		
		fireball = new FireBallSpell(gameThread, this);
	}

	@Override
	public void attack() {
		if (!isCastingAttack && attackCoolDown == 0) {
//			ProjectileAttacks atk = ProjectileAttacks.attackList.get(0);
//			attackCoolDown = atk.getCoolDown();
//			isCastingAttack = true;
//			atk.fire();
		}
	}

	private void attackCooldownTimer() {
		attackCoolDownTimer += System.currentTimeMillis() - lastTimeCoolDown;
		lastTimeCoolDown = System.currentTimeMillis();
		if (attackCoolDownTimer > attackCoolDown) {
			attackCoolDown = 0;
			attackCoolDownTimer = 0;
		}
	}

	@Override
	public void update() {
		if (isAlive) {
			fireball.update();
			attackCooldownTimer();
			animationWalk.timerCounter();
			animationAttack.timerCounter();
			if (detectPlayerInChaseRange(gameThread.getWorld().getEntityManager().getPlayer())) {
				facingDirection = getFacingDirectionFromPlayerPos();
				if (getThisAndPlayerRange() <= DEFAULT_ATTACK_RANGE) {
					attack();
				} else {
					chasePlayer(gameThread.getWorld().getEntityManager().getPlayer());
					hurtPlayerOnHit();
				}
			} else {
				moveRandomly();
			}
		} else {
			gameThread.getWorld().getEntityManager().removeEntity(this);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if (isAlive) {
			fireball.render(g2d);
			g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);

			g2d.setColor(Color.red);
			// draw healthBar
			g2d.fillRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - 20 - gameThread.getGameCamera().getyOffset()), (int) getHealthBarWidth(),
					8);
			g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
			g2d.fillOval((int) (xPos - gameThread.getGameCamera().getxOffset()), (int) (yPos - gameThread.getGameCamera().getyOffset()), 10, 10);
		}
	}

	@Override
	protected BufferedImage getCurrentAnimationFrame() {
		if (isCastingAttack) {
			if (facingDirection == "RIGHT") {
				return animationAttack.getCurrentFrame();
			} else if (facingDirection == "LEFT") {
				return Utils.flipImageHorizontally(animationAttack.getCurrentFrame());
			} else if (facingDirection == "UP") {
				return animationAttack.getCurrentFrame();
			} else {
				return Utils.flipImageHorizontally(animationAttack.getCurrentFrame());
			}
		} else {
			if (facingDirection == "RIGHT") {
				return animationWalk.getCurrentFrame();
			} else if (facingDirection == "LEFT") {
				return Utils.flipImageHorizontally(animationWalk.getCurrentFrame());
			} else if (facingDirection == "UP") {
				return animationWalk.getCurrentFrame();
			} else {
				return Utils.flipImageHorizontally(animationWalk.getCurrentFrame());
			}
		}
	}
}
