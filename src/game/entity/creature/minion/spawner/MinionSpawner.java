package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;

import game.engine.GameThread;
import game.entity.creature.minion.BigBlob;
import game.graphics.Animation;
import game.graphics.Assets;

public abstract class MinionSpawner {
	
	protected static final int DELAY = 10000;
	
	protected final Animation portal;
	protected final GameThread gameThread;
	protected final int xPos, yPos;
	protected long timer, lastTime;
	
	public MinionSpawner(GameThread gameThread, int xPos, int yPos) {
		this.gameThread = gameThread;
		this.xPos = xPos;
		this.yPos = yPos;
		
		portal = new Animation(100, Assets.portal);
	}
	
	public void update() {
		spawnMinion();
		portal.timerCounter();
	}
	
	public abstract void render(Graphics2D g2d);
	
	public abstract void spawnMinion();
}
