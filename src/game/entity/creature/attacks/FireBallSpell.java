package game.entity.creature.attacks;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;

public class FireBallSpell extends ProjectileAttacks {

	public FireBallSpell(GameThread gameThread, Creatures source) {
		super(gameThread, source, 4.0f, 800, 50);

		animationHit = new TemporaryAnimation(100, Assets.fireball_hit);
		animationFiring = new TemporaryAnimation(100, Assets.fireball_bullet);
	}

	@Override
	public void update() {
		if (isFiring) {
			animationFiring.timerCounter();
			handleDirectionChange();
			if (animationFiring.isDone()) {
				isFiring = false;
				animationFiring.reset();
				source.doneAttacking();
				// create new ice bullet to prevent old one disappear
				firedBullet.add(new SpellBullet(gameThread, Assets.fireball_bullet, 100, Assets.fireball_hit, 100,
						source.getFacingDirection(), speed, damage, source.getxPos() + xOffset,
						source.getyPos() + yOffset, createBoundingBox(35, 35)));
			}
		}
		updateAllBullets();
	}

	@Override
	protected void handleDirectionChange() {
		String facingDirection = source.getFacingDirection();
		if (facingDirection == "UP") {
			rotationAngle = 270;
			xOffset = 0;
			yOffset = -source.getHeight() + 20;
		} else if (facingDirection == "DOWN") {
			rotationAngle = 90;
			xOffset = 0;
			yOffset = source.getHeight();
		} else if (facingDirection == "LEFT") {
			rotationAngle = 180;
			xOffset = -source.getWidth() + 10;
			yOffset = 0;
		} else {
			rotationAngle = 0;
			xOffset = source.getWidth() - 8;
			yOffset = 0;
		}
	}

	@Override
	protected Rectangle createBoundingBox(int width, int height) {
		String direction = gameThread.getWorld().getEntityManager().getPlayer().getFacingDirection();
		Rectangle r;
		if (direction == "UP") {
			r = new Rectangle((int) 15, (int) 15, height, width);
		} else if (direction == "DOWN") {
			r = new Rectangle((int) 15, (int) 15, height, width);
		} else if (direction == "LEFT") {
			r = new Rectangle((int) 15, (int) 15, width, height);
		} else {
			r = new Rectangle((int) 15, (int) 15, width, height);
		}
		return r;
	}

}
