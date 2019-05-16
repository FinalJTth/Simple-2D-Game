package game.entity.creature.minion.spawner;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.engine.GameThread;
import game.entity.creature.Creatures;
import game.entity.creature.minion.BigBlob;
import game.graphics.Animation;
import game.graphics.Assets;

public class WaveSpawner {

	private static final int DELAY = 5000;
	
	protected final Animation portal;
	protected final GameThread gameThread;
	
	protected long timer, lastTime;
	
	private ArrayList<Creatures> minionToSpawn;
	private int minionIndex;
	private final float xPos1 = 200, yPos1 = 200, xPos2 = 1600, yPos2 = 200, xPos3 = 200, yPos3 = 1600, xPos4 = 1600, yPos4 = 1600;

	public WaveSpawner(GameThread gameThread, ArrayList<Creatures> minionToSpawn) {
		this.gameThread = gameThread;
		
		portal = new Animation(100, Assets.portal);
		
		this.minionToSpawn = minionToSpawn;
		minionIndex = 0;
	}

	public void update() {
		spawnMinion();
		portal.timerCounter();
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos1 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos1 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos2 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos2 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos3 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos3 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
		g2d.drawImage(portal.getCurrentFrame(), (int) (xPos4 - gameThread.getGameCamera().getxOffset()),
				(int) (yPos4 - gameThread.getGameCamera().getyOffset()), 200, 200, null);
	}

	public void spawnMinion() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DELAY - gameThread.getWorld().getEntityManager().getWaveManager().getWaveNo() * 250) {
			timer = 0;
			if (minionIndex < minionToSpawn.size()) {
				for (int i = 0; i<4 ;i++) {
					gameThread.getWorld().getEntityManager().addEntity(minionToSpawn.get(minionIndex));
					minionIndex++;
					if (minionIndex >= minionToSpawn.size())
						break;
				}
			}
			
		}
	}

}
