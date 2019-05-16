package game.entity;

import java.awt.Rectangle;

public interface Collidable {
	
	public boolean checkEntityCollision(float xOffset, float yOffset);
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset);

}
