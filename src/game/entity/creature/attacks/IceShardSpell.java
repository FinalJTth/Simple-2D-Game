package game.entity.creature.attacks;

import java.awt.Graphics2D;
import java.awt.Rectangle;

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

	public IceShardSpell(GameThread gameThread, Creatures source) {
		super(gameThread, source, 10.0f, 100, 1000);

		animationFiring = new TemporaryAnimation(100, Assets.iceShardSpell_firing);
		animationHit = new TemporaryAnimation(ANIMATION_SPEED, Assets.iceShardSpell_hit);

	}

	@Override
	public void fire() {
		xPos = source.getxPos();
		yPos = source.getyPos();
		handleDirectionChange();	// handle all direction change when start firing
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
				// create ice lance bounding box
				bounds = new Rectangle(0, 0, 182, 115);
			}
		} else if (status == "FIRED") {
			// chcek for collision
			
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
	}

}
