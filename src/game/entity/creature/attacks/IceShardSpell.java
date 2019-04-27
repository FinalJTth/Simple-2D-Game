package game.entity.creature.attacks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public class IceShardSpell extends ProjectileAttacks {

	private static final int ANIMATION_SPEED = 100;
	private TemporaryAnimation animationFiring, animationHit;
	private double rotationAngle;
	private int xOffset, yOffset;

	private ArrayList<SpellBullet> firedBullet = new ArrayList<SpellBullet>();

	public IceShardSpell(GameThread gameThread, Creatures source) {
		super(gameThread, source, 6.0f, 100, 1000);

		animationFiring = new TemporaryAnimation(100, Assets.iceShardSpell_firing);
		animationHit = new TemporaryAnimation(100, Assets.iceShardSpell_hit);

	}

	@Override
	public void fire() {
		handleDirectionChange(); // handle all direction change when start firing
		status = "FIRING";
	}

	@Override
	public void update() {
		if (status == "FIRING") {
			animationFiring.timerCounter();
			if (animationFiring.isDone()) {
				status = "FIRED";
				animationFiring.reset();
				source.doneAttacking();

				Utils.printAllEntities(gameThread);
				// create new ice bullet to prevent old one disappear
				firedBullet.add(new SpellBullet(gameThread, Assets.iceShardSpell_bullet, animationHit,
						source.getFacingDirection(), speed, damage,
						source.getxPos() + xOffset,
						source.getyPos() + yOffset, 20, 12));
				Utils.printPlayerXYCoords(gameThread);
			}
			updateAllBullets();
		} else if (status == "FIRED") {
			updateAllBullets();
		}

	}
	
	private void updateAllBullets() {
		ArrayList<SpellBullet> done = new ArrayList<SpellBullet>();
		for (SpellBullet sp : firedBullet) {
			sp.update();
			if (sp.isFinished())
				done.add(sp);
		}
		for (SpellBullet sp : done) {
			firedBullet.remove(sp);
		}
	}

	// set value for rotating image according to player direction
	private void handleDirectionChange() {
		String facingDirection = source.getFacingDirection();
		if (facingDirection == "UP") {
			rotationAngle = 270;
			xOffset = 0;
			yOffset = -source.getHeight();
		} else if (facingDirection == "DOWN") {
			rotationAngle = 90;
			xOffset = 0;
			yOffset = source.getHeight();
		} else if (facingDirection == "LEFT") {
			rotationAngle = 180;
			xOffset = -source.getWidth();
			yOffset = 0;
		} else {
			rotationAngle = 0;
			xOffset = source.getWidth();
			yOffset = 0;
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if (status == "FIRING") {
			// draw image(rotated) accordingly to handleDirectionChange()
			g2d.drawImage(Utils.rotateImage(animationFiring.getCurrentFrame(), Math.toRadians(rotationAngle)),
					(int) (source.getxPos() + xOffset - gameThread.getGameCamera().getxOffset()),
					(int) (source.getyPos() + yOffset - gameThread.getGameCamera().getyOffset()), null);
		}
		for (SpellBullet sp : firedBullet) {
			sp.render(g2d);
		}

	}

}
