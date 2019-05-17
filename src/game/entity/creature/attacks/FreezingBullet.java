package game.entity.creature.attacks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.entity.Entity;
import game.entity.creature.Player;
import game.entity.creature.minion.Minion;

public class FreezingBullet extends SpellBullet {

	public FreezingBullet(GameThread gameThread, BufferedImage img, BufferedImage[] deadAnimImg, int deadAnimSpeed,
			String direction, float speed, int damage, float originX, float originY, Rectangle bounds) {
		super(gameThread, img, deadAnimImg, deadAnimSpeed, direction, speed, damage, originX, originY, bounds);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : gameThread.getWorld().getEntityManager().getEntities()) {
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if (e instanceof Player)
					continue;
				if (e instanceof Minion) {
					Minion c = (Minion) e;
					c.hurt(damage); // deal damage to target creature
					c.freeze();
				}
				return true;
			}
		}
		return false;
	}

}
