package game.entity.creature.attacks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.Entity;
import game.entity.creature.Creatures;
import game.graphics.Assets;
import game.graphics.TemporaryAnimation;
import game.utils.Utils;

public abstract class ProjectileAttacks {

	// Class stuff
	protected GameThread gameThread;
	protected final int damage, coolDown, manaCost;
	protected final float speed;
	
	protected float xPos, yPos;
	protected boolean isFiring;
	protected Creatures source;
	protected Rectangle bounds;
	protected TemporaryAnimation animationFiring, animationHit;
	
		// for rotating image
	protected double rotationAngle;
	protected int xOffset, yOffset;
	
	protected ArrayList<SpellBullet> firedBullet = new ArrayList<SpellBullet>();

	public ProjectileAttacks(GameThread gameThread, Creatures source, float speed, int damage, int coolDown, int manaCost) {
		this.gameThread = gameThread;
		this.source = source;
		this.speed = speed;
		this.damage = damage;
		this.coolDown = coolDown;
		this.manaCost = manaCost;
	}

	public void fire() {
		System.out.println("FIRE");
		handleDirectionChange(); // handle all direction change when start firing
		isFiring = true;
	}
	
	public abstract void update();

	protected void updateAllBullets() {
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
	
	protected abstract void handleDirectionChange();
	
	protected abstract Rectangle createBoundingBox(int width, int height);
	
	public void render(Graphics2D g2d) {
		if (isFiring) {
			// draw image(rotated) accordingly to handleDirectionChange()
			g2d.drawImage(Utils.rotateImage(animationFiring.getCurrentFrame(), Math.toRadians(rotationAngle)),
					(int) (source.getxPos() + xOffset - gameThread.getGameCamera().getxOffset()),
					(int) (source.getyPos() + yOffset - gameThread.getGameCamera().getyOffset()), null);
		}
		for (SpellBullet sp : firedBullet) {
			sp.render(g2d);
		}

	}

	public int getCoolDown() {
		return coolDown;
	}
	
	public int getManaCost() {
		return manaCost;
	}
}
