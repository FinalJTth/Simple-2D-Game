package game.entity.creature.attacks;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public class IceShardSpell extends ProjectileAttacks {

	private static final int ANIMATION_SPEED = 100, DEFAULT_MANA_COST = 10;

	public IceShardSpell(GameThread gameThread, Creatures source) {
		super(gameThread, source, 7.0f, 200, DEFAULT_MANA_COST);

		animationFiring = new TemporaryAnimation(ANIMATION_SPEED, Assets.iceShardSpell_firing);
		animationHit = new TemporaryAnimation(ANIMATION_SPEED, Assets.iceShardSpell_hit);

	}

	@Override
	public void update() {
		if (isFiring) {
			animationFiring.timerCounter();
			if (animationFiring.isDone()) {
				isFiring = false;
				animationFiring.reset();
				source.doneAttacking();
				// create new ice bullet to prevent old one disappear
				firedBullet.add(new SpellBullet(gameThread, Assets.iceShardSpell_bullet, Assets.iceShardSpell_hit, 100,
						source.getFacingDirection(), speed, damage, source.getxPos() + xOffset,
						source.getyPos() + yOffset, createBoundingBox(30, 24)));
			}
		}
		updateAllBullets();
	}

	@Override
	// set value for rotating image according to player direction
	protected void handleDirectionChange() {
		String facingDirection = source.getFacingDirection();
		if (facingDirection == "UP") {
			rotationAngle = 270;
			xOffset = -20;
			yOffset = -source.getHeight() - 14;
		} else if (facingDirection == "DOWN") {
			rotationAngle = 90;
			xOffset = -12;
			yOffset = source.getHeight();
		} else if (facingDirection == "LEFT") {
			rotationAngle = 180;
			xOffset = -source.getWidth() - 20;
			yOffset = -12;
		} else {
			rotationAngle = 0;
			xOffset = source.getWidth() - 8;
			yOffset = -12;
		}
	}

	@Override
	// create bbox with proper direction
	protected Rectangle createBoundingBox(int width, int height) {
		String direction = gameThread.getWorld().getEntityManager().getPlayer().getFacingDirection();
		Rectangle r;
		if (direction == "UP") {
			r = new Rectangle((int) 44, (int) 40, height, width);
		} else if (direction == "DOWN") {
			r = new Rectangle((int) 36, (int) 38, height, width);
		} else if (direction == "LEFT") {
			r = new Rectangle((int) 25, (int) 32, width, height);
		} else {
			r = new Rectangle((int) 42, (int) 44, width, height);
		}
		return r;
	}

}
