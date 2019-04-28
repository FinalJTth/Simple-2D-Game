package game.graphics;

import java.awt.image.BufferedImage;

import game.utils.Utils;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 56, SPRITESHEET_HEIGHT = 56;

	public static BufferedImage grass, lava;
	public static BufferedImage medium_spikey_tree;
	public static BufferedImage[] player_wizard_down, player_wizard_up, player_wizard_left, player_wizard_right;
	public static BufferedImage[] iceShardSpell_firing, iceShardSpell_hit;
	public static BufferedImage iceShardSpell_bullet;
	public static BufferedImage[] play_button, sound_ON_button, sound_OFF_button, exit_button;
	public static BufferedImage play_button_normal, sound_ON_button_normal, sound_OFF_button_normal, exit_button_normal;
	public static BufferedImage play_button_hover, sound_ON_button_hover, sound_OFF_button_hover, exit_button_hover;
	public static BufferedImage play_button_onClick, sound_ON_button_onClick, sound_OFF_button_onClick,
			exit_button_onClick;
	public static BufferedImage[] big_blob_idle, big_blob_walk, big_blob_attack;

	private static int cnt = 0;

	public static void init() {

		// player
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

		// trees
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/trees.png"));
		medium_spikey_tree = treeSheet.crop(129, 279, 60, 75);

		// ice shard spell
		SpriteSheet iceShardSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/ice_shard/iceShardSprite.png"));
		iceShardSpell_firing = new BufferedImage[6];
		iceShardSpell_hit = new BufferedImage[4];
		cnt = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 4; y++) {
				if (cnt == 11)
					break;
				if (cnt < 6)
					iceShardSpell_firing[cnt] = Utils.resize(iceShardSheet.crop(y * 70, x * 70, 70, 70), 1.5f);
				else if (cnt == 6)
					iceShardSpell_bullet = Utils.resize(iceShardSheet.crop(y * 70, x * 70, 70, 70), 1.5f);
				else {
					iceShardSpell_hit[cnt - 7] = Utils.resize(iceShardSheet.crop(y * 70, x * 70, 70, 70), 1.5f);
				}

				cnt++;
			}
		}

		SpriteSheet bigBlobSheet = new SpriteSheet(
				ImageLoader.loadImage("res/texture/minion/bigBlob/bigBlobMinionSprite.png"));
		big_blob_idle = new BufferedImage[6];
		big_blob_walk = new BufferedImage[8];
		big_blob_attack = new BufferedImage[8];
		for (int x = 0; x < 3; x++) {
			cnt = 0;
			for (int y = 0; y < 7; y++) {
				if (x == 0 && y < 6) {
					big_blob_idle[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				} else if (x == 1) {
					big_blob_walk[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				} else {
					big_blob_attack[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				}
			}
		}

		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("res/menuButton/buttonSheet.png"));
		play_button_normal = buttonSheet.crop(0, 0, 40, 21);
		exit_button_normal = buttonSheet.crop(41, 0, 40, 21);
		sound_ON_button_normal = buttonSheet.crop(41 * 2, 0, 40, 21);
		sound_OFF_button_normal = buttonSheet.crop(41 * 3, 0, 40, 21);

		play_button_hover = buttonSheet.crop(0, 22, 40, 21);
		exit_button_hover = buttonSheet.crop(41, 22, 40, 21);
		sound_ON_button_hover = buttonSheet.crop(41 * 2, 22, 40, 21);
		sound_OFF_button_hover = buttonSheet.crop(41 * 3, 22, 40, 21);

		play_button_onClick = buttonSheet.crop(0, 43, 40, 21);
		exit_button_onClick = buttonSheet.crop(41, 43, 40, 21);
		sound_ON_button_onClick = buttonSheet.crop(41 * 2, 43, 40, 21);
		sound_OFF_button_onClick = buttonSheet.crop(41 * 3, 43, 40, 21);

		play_button = new BufferedImage[3];
		play_button[0] = play_button_normal;
		play_button[1] = play_button_hover;
		play_button[2] = play_button_onClick;

		exit_button = new BufferedImage[3];
		exit_button[0] = exit_button_normal;
		exit_button[1] = exit_button_hover;
		exit_button[2] = exit_button_onClick;

		sound_ON_button = new BufferedImage[3];
		sound_ON_button[0] = sound_ON_button_normal;
		sound_ON_button[1] = sound_ON_button_hover;
		sound_ON_button[2] = sound_ON_button_onClick;

		sound_OFF_button = new BufferedImage[3];
		sound_OFF_button[0] = sound_OFF_button_normal;
		sound_OFF_button[1] = sound_OFF_button_hover;
		sound_OFF_button[2] = sound_OFF_button_onClick;

		// TODO temporary code. Will be replaced by SpriteSheet later.
		grass = ImageLoader.loadImage("res/texture/grass.png");
		lava = ImageLoader.loadImage("res/texture/lava.png");
	}

}
