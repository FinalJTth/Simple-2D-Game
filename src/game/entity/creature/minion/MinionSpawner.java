package game.entity.creature.minion;

import game.engine.GameThread;

public class MinionSpawner {
	
	private static final int DELAY = 10000;
	
	private final GameThread gameThread;
	private final int xPos, yPos;
	private long timer, lastTime;
	
	public MinionSpawner(GameThread gameThread, int xPos, int yPos) {
		this.gameThread = gameThread;
		this.xPos = xPos;
		this.yPos = yPos;
		
	}
	
	public void update() {
		spawnMinion();
	}
	
	public void spawnMinion() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DELAY) {
			timer = 0;
			gameThread.getWorld().getEntityManager().addEntity(new BigBlob(gameThread, xPos, yPos));
		}
	}
	
}
