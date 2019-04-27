package game.graphics;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Animation {

	protected int speed, index;
	protected long lastTime, timer;
	protected BufferedImage[] frames;
	protected AffineTransform tx;
	protected AffineTransformOp op;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}

	public void timerCounter() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

}
