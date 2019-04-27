package game.entity.creature.attacks;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.Entity;
import game.entity.creature.Creatures;

public abstract class ProjectileAttacks {

	// Static stuff
	public static ArrayList<ProjectileAttacks> attackList = new ArrayList<ProjectileAttacks>();

	public static void addAttack(ProjectileAttacks atk) {
		attackList.add(atk);
	}

	public static void updateAttacks() {
		for (ProjectileAttacks atk : attackList) {
			atk.update();
		}
	}

	public static void renderAttacks(Graphics2D g2d) {
		for (ProjectileAttacks atk : attackList) {
			atk.render(g2d);
		}
	}

	// Class stuff
	protected GameThread gameThread;
	protected final int damage, coolDown;
	protected final float speed;
	protected float xPos, yPos;
	protected Creatures source;
	protected String status; // INACTIVE, FIRING, FIRED, HIT
	protected Rectangle bounds;

	public ProjectileAttacks(GameThread gameThread, Creatures source, float speed, int damage, int coolDown) {
		this.gameThread = gameThread;
		this.source = source;
		this.speed = speed;
		this.damage = damage;
		this.coolDown = coolDown;
		this.status = "INACTIVE";
	}

	public abstract void update();

	public abstract void render(Graphics2D g2d);

	public abstract void fire();

	@SuppressWarnings("unlikely-arg-type")
	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : gameThread.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (xPos + bounds.x + xOffset), (int) (yPos + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public int getCoolDown() {
		return coolDown;
	}
}
