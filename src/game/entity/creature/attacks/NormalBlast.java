package game.entity.creature.attacks;

import java.awt.Rectangle;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.soundFX.SoundPlayer;

public class NormalBlast extends ProjectileAttacks {

	public NormalBlast(GameThread gameThread, Creatures source) {
		super(gameThread, source, 12.0f, 20, 0);

		animationFiring = new TemporaryAnimation(10, Assets.blast_firing);
		animationHit = new TemporaryAnimation(20, Assets.blast_hit);
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
				firedBullet.add(new ScatterSpellBullet(gameThread, Assets.blast_bullet, Assets.blast_hit, 30,
						source.getFacingDirection(), speed, damage, source.getxPos() + xOffset,
						source.getyPos() + yOffset, createBoundingBox(30, 24)));
				SoundPlayer.pewpew.playSound();
			}
		}
		updateAllBullets();
	}

	@Override
	protected void handleDirectionChange() {
		String facingDirection = source.getFacingDirection();
		if (facingDirection == "UP") {
			rotationAngle = 270 + Math.random() * 8 - 4;
			xOffset = 0;
			yOffset = -source.getHeight() + 20;
		} else if (facingDirection == "DOWN") {
			rotationAngle = 90 + Math.random() * 8 - 4;
			xOffset = 0;
			yOffset = source.getHeight();
		} else if (facingDirection == "LEFT") {
			rotationAngle = 180 + Math.random() * 8 - 4;
			xOffset = -source.getWidth() + 10;
			yOffset = 20;
		} else {
			rotationAngle = 0 + Math.random() * 8 - 4;
			xOffset = source.getWidth() - 8;
			yOffset = 20;
		}
	}

	@Override
	protected Rectangle createBoundingBox(int width, int height) {
		String direction = gameThread.getWorld().getEntityManager().getPlayer().getFacingDirection();
		Rectangle r;
		if (direction == "UP") {
			r = new Rectangle((int) 20, (int) 0, height, width);
		} else if (direction == "DOWN") {
			r = new Rectangle((int) 20, (int) 0, height, width);
		} else if (direction == "LEFT") {
			r = new Rectangle((int) 20, (int) 0, width, height);
		} else {
			r = new Rectangle((int) 20, (int) 0, width, height);
		}
		return r;
	}

}
