package game.entity.creature.attacks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.engine.GameThread;

public class ScatterSpellBullet extends SpellBullet {

	public ScatterSpellBullet(GameThread gameThread, BufferedImage img, BufferedImage[] deadAnimImg, int deadAnimSpeed,
			String direction, float speed, int damage, float originX, float originY, Rectangle bounds) {
		super(gameThread, img, deadAnimImg, deadAnimSpeed, direction, speed, damage, originX, originY, bounds);
		handleDirectionChange();
	}
	
	private void handleDirectionChange() {
		if (direction == "UP") {
			rotationAngle = 270 + Math.random() * 12 - 4;
		} else if (direction == "DOWN") {
			rotationAngle = 90 + Math.random() * 12 - 4;
		} else if (direction == "LEFT") {
			rotationAngle = 180 + Math.random() * 12 - 4;
		} else {
			rotationAngle = 0 + Math.random() * 12 - 4;
		}
	}
	
}
