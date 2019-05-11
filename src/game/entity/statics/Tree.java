package game.entity.statics;

import java.awt.Color;
import java.awt.Graphics2D;

import game.engine.GameThread;
import game.graphics.Assets;

public class Tree extends StaticEntity {

	public Tree(GameThread gameThread, float xPos, float yPos, int width, int height) {
		super(gameThread, xPos, yPos, width, height);

		bounds.x = 16;
		bounds.y = 48;
		bounds.width = 25;
		bounds.height = 20;
	}

	@Override
	public void update() {
		return;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(Assets.medium_spikey_tree, (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		g2d.setColor(Color.red);
		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
		// draw collision checking (bounding) box
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

}
