package game.entity.creature.attacks;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.Entity;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;

public class IceShardSpell extends ProjectileAttacks {

	private static final int ANIMATION_SPEED = 100;
	private TemporaryAnimation animationFiring, animationHit;

	public IceShardSpell(GameThread gameThread, Entity source) {
		super(gameThread, source, 10.0f, 100, 1000);

		animationFiring = new TemporaryAnimation(100, Assets.iceShardSpell_firing);
		animationHit = new TemporaryAnimation(ANIMATION_SPEED, Assets.iceShardSpell_hit);

	}

	@Override
	public void fire() {
		xPos = source.getxPos() + source.getWidth();
		yPos = source.getyPos();
		status = "FIRING";
	}

	@Override
	public void update() {
		if (status == "FIRING") {
			animationFiring.timerCounter();
			if (animationFiring.isDone()) {
				status = "FIRED";
				animationFiring.reset();
			}
		} else if (status == "FIRED") {
			// chcek for collision
		}

	}

	@Override
	public void render(Graphics2D g2d) {
		if (status == "FIRING") {
			g2d.drawImage(animationFiring.getCurrentFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), null);
		}
	}

}
