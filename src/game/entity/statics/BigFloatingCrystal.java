package game.entity.statics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.graphics.Animation;
import game.graphics.Assets;

public class BigFloatingCrystal extends StaticEntity {

	private Animation animationFloating;

	public BigFloatingCrystal(GameThread gameThread, float xPos, float yPos, int width, int height,
			boolean collidable) {
		super(gameThread, xPos, yPos, width, height, collidable);
		bounds.width = (width + 20) / 3;
		bounds.height = (width) / 3;
		bounds.x = (width - bounds.width) / 2;
		bounds.y = height - (height / 5);
		animationFloating = new Animation(700, Assets.big_floating_crystal);
	}

	@Override
	public void update() {
		animationFloating.timerCounter();
		return;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(getCurrentAnimationFrame(), (int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height, null);
		g2d.setColor(Color.red);
		g2d.drawRect((int) (xPos - gameThread.getGameCamera().getxOffset()),
				(int) (yPos - gameThread.getGameCamera().getyOffset()), width, height);
		// draw collision checking (bounding) box
		g2d.drawRect((int) (xPos + bounds.x - gameThread.getGameCamera().getxOffset()),
				(int) (yPos + bounds.y - gameThread.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	protected BufferedImage getCurrentAnimationFrame() {
		return animationFloating.getCurrentFrame();
	}
}
