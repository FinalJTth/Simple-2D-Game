package game.entity.creature.attacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.Collidable;
import game.entity.Entity;
import game.entity.creature.Creatures;
import game.entity.creature.Player;
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public class SpellBullet implements Collidable {

	private final float speed;
	private final int damage;
	protected final String direction;
	private final TemporaryAnimation animation, deadAnimation;

	private GameThread gameThread;
	private Rectangle bounds;
	protected double rotationAngle;
	private float xPos, yPos;
	private boolean isAlive, isFinished;
	
	public SpellBullet(GameThread gameThread, BufferedImage[] img, int animSpeed, BufferedImage[] deadAnim,
			int deadAnimSpeed, String direction, float speed, int damage, float originX, float originY,
			Rectangle bounds) {
		this.gameThread = gameThread;
		animation = new TemporaryAnimation(animSpeed, img);
		this.deadAnimation = new TemporaryAnimation(deadAnimSpeed, deadAnim);
		this.direction = direction;
		this.speed = speed;
		this.damage = damage;
		this.xPos = originX;
		this.yPos = originY;
		this.bounds = bounds;

		isAlive = true;
		isFinished = false;
		handleDirectionChange();
	}

	// for no animation bullet
	public SpellBullet(GameThread gameThread, BufferedImage img, BufferedImage[] deadAnimImg, int deadAnimSpeed,
			String direction, float speed, int damage, float originX, float originY, Rectangle bounds) {
		this.gameThread = gameThread;
		BufferedImage[] tmp = new BufferedImage[1];
		tmp[0] = img;
		animation = new TemporaryAnimation(100, tmp);
		this.deadAnimation = new TemporaryAnimation(deadAnimSpeed, deadAnimImg);
		this.direction = direction;
		this.speed = speed;
		this.damage = damage;
		this.xPos = originX;
		this.yPos = originY;
		this.bounds = bounds;

		isAlive = true;
		isFinished = false;
		handleDirectionChange();
	}

	protected void move() {
//		if (direction == "UP") {
//			yPos -= speed;
//		} else if (direction == "DOWN") {
//			yPos += speed;
//		} else if (direction == "LEFT") {
//			xPos -= speed;
//		} else {
//			xPos += speed;
//		}
		xPos += (float) (speed * Math.cos(Math.toRadians(rotationAngle)));
		yPos += (float) (speed * Math.sin(Math.toRadians(rotationAngle)));
	}

	private void handleDirectionChange() {
		if (direction == "UP") {
			rotationAngle = 270;
		} else if (direction == "DOWN") {
			rotationAngle = 90;
		} else if (direction == "LEFT") {
			rotationAngle = 180;
		} else {
			rotationAngle = 0;
		}
	}

	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : gameThread.getWorld().getEntityManager().getEntities()) {
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				if (e instanceof Player)
					continue;
				if (e instanceof Creatures) {
					Creatures c = (Creatures) e;
					c.hurt(damage); // deal damage to target creature
				}
				return true;
			}
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (xPos + bounds.x + xOffset), (int) (yPos + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public void update() {
		if (!isAlive) {
			deadAnimation.timerCounter();
			if (deadAnimation.isDone()) {
				isFinished = true;
				deadAnimation.reset();
				return;
			}
		}
		// prevent triggering multiple checkEntityCollision overkilling creature
		if (isAlive && (checkEntityCollision(speed, 0f) || checkEntityCollision(0f, speed))) {
			isAlive = false;
			return;
		}
		// prevent deadAnimation from moving
		if (isAlive) {
			move();
			animation.timerCounter();
			if (animation.isDone())
				animation.reset();
		}

	}

	public void render(Graphics2D g2d) {
		if (isAlive) {
			g2d.drawImage(Utils.rotateImage(animation.getCurrentFrame(), Math.toRadians(rotationAngle)),
					(int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), null);
			g2d.setColor(Color.red);
//			g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
//					(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}

		else
			g2d.drawImage(Utils.rotateImage(deadAnimation.getCurrentFrame(), Math.toRadians(rotationAngle)),
					(int) (xPos - gameThread.getGameCamera().getxOffset()),
					(int) (yPos - gameThread.getGameCamera().getyOffset()), null);
	}

	// tell IceShardSpell that bullet is completely hit target and finish dead
	// animation
	public boolean isFinished() {
		return isFinished;
	}
	
	public boolean isOutOfBound() {
		if (xPos > gameThread.getWorld().getWidth() * 64 || xPos < 0)
			return true;
		if (yPos > gameThread.getWorld().getHeight() * 64 || yPos < 0)
			return true;
		return false;
	}
}
