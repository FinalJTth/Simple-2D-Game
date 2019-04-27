package game.graphics;

import java.awt.image.BufferedImage;

public class Animation {

	protected int speed, index;
	protected long lastTime, timer;
	protected BufferedImage[] frames;

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
