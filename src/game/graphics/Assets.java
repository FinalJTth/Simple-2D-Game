package game.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 32, SPRITESHEET_HEIGHT = 32;

	public static BufferedImage player, grass, lava;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/texture/dummy.png"));

		// TODO temporary code. Will be replaced by SpriteSheet later.
		player = ImageLoader.loadImage("res/texture/dummy.png");
		grass = ImageLoader.loadImage("res/texture/grass.png");
		lava = ImageLoader.loadImage("res/texture/lava.png");
	}

}
