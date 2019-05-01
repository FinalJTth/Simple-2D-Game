package game.graphics;

import java.awt.image.BufferedImage;

public class TemporaryAnimation extends Animation {

	private boolean isDoneForOnce;

	public TemporaryAnimation(int speed, BufferedImage[] frames) {
		super(speed, frames);
		isDoneForOnce = false;
	}

	@Override
	public void timerCounter() {
		if (!isDoneForOnce) {
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();

			if (timer > speed) {
				index++;
				timer = 0;
				if (index >= frames.length) {
					index = frames.length - 1;
					isDoneForOnce = true;
				}
			}
		}
	}

	public void reset() {
		isDoneForOnce = false;
		index = 0;
	}
	
	public boolean isDone() {
		return isDoneForOnce;
	}
}
