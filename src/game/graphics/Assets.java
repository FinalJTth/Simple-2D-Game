package game.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 56, SPRITESHEET_HEIGHT = 56;

	public static BufferedImage grass, lava;
	public static BufferedImage medium_spikey_tree;
	public static BufferedImage[] player_wizard_down, player_wizard_up, player_wizard_left, player_wizard_right;

	public static void init() {
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/wizard_animation_sprite.png"));

		player_wizard_down = new BufferedImage[4];
		player_wizard_down[0] = playerSheet.crop(0, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_down[1] = playerSheet.crop(56, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_down[2] = playerSheet.crop(56 * 2, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_down[3] = playerSheet.crop(56 * 3, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);

		player_wizard_left = new BufferedImage[4];
		player_wizard_left[0] = playerSheet.crop(0, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_left[1] = playerSheet.crop(56, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_left[2] = playerSheet.crop(56 * 2, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_left[3] = playerSheet.crop(56 * 3, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);

		player_wizard_right = new BufferedImage[4];
		player_wizard_right[0] = playerSheet.crop(0, 56 * 2, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_right[1] = playerSheet.crop(56, 56 * 2, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_right[2] = playerSheet.crop(56 * 2, 56 * 2, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_right[3] = playerSheet.crop(56 * 3, 56 * 2, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);

		player_wizard_up = new BufferedImage[4];
		player_wizard_up[0] = playerSheet.crop(0, 56 * 3, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_up[1] = playerSheet.crop(56, 56 * 3, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_up[2] = playerSheet.crop(56 * 2, 56 * 3, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		player_wizard_up[3] = playerSheet.crop(56 * 3, 56 * 3, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/trees.png"));
		
		medium_spikey_tree = treeSheet.crop(129, 279, 60, 75);
		
		

		// TODO temporary code. Will be replaced by SpriteSheet later.
		grass = ImageLoader.loadImage("res/texture/grass.png");
		lava = ImageLoader.loadImage("res/texture/lava.png");
	}

}
