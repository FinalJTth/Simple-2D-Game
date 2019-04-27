package game.entity.statics;

import game.engine.GameThread;
import game.entity.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);
	}

	
	
}
