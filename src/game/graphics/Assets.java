package game.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 32, SPRITESHEET_HEIGHT = 32;

	public static BufferedImage grass, lava;

	public static BufferedImage[] player_wizard_down, player_wizard_up, player_wizard_left, player_wizard_right;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/texture/wizard_animation_sprite.png"));

		player_wizard_down = new BufferedImage[4];
		player_wizard_down[0] = sheet.crop(0, 0, 56, 56);
		player_wizard_down[1] = sheet.crop(0, 56, 56, 56);
		player_wizard_down[2] = sheet.crop(0, 56 * 2, 56, 56);
		player_wizard_down[3] = sheet.crop(0, 56 * 3, 56, 56);

		// TODO temporary code. Will be replaced by SpriteSheet later.
		grass = ImageLoader.loadImage("res/texture/grass.png");
		lava = ImageLoader.loadImage("res/texture/lava.png");
	}

}
