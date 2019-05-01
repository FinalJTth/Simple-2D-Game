package game.graphics;

import java.awt.image.BufferedImage;

import game.utils.Utils;

public class Assets {

	public static final int SPRITESHEET_WIDTH = 56, SPRITESHEET_HEIGHT = 56;

	public static BufferedImage grass, lava;
	public static BufferedImage crosshair;
	public static BufferedImage medium_spikey_tree;
	public static BufferedImage[] player_wizard_down, player_wizard_up, player_wizard_left, player_wizard_right;
	public static BufferedImage[] player_wizard_dead_down, player_wizard_dead_up, player_wizard_dead_left,
			player_wizard_dead_right;
	public static BufferedImage[] iceShardSpell_firing, iceShardSpell_hit;
	public static BufferedImage iceShardSpell_bullet;
	public static BufferedImage[] play_button, sound_ON_button, sound_OFF_button, exit_button;
	public static BufferedImage play_button_normal, sound_ON_button_normal, sound_OFF_button_normal, exit_button_normal;
	public static BufferedImage play_button_hover, sound_ON_button_hover, sound_OFF_button_hover, exit_button_hover;
	public static BufferedImage play_button_onClick, sound_ON_button_onClick, sound_OFF_button_onClick,
			exit_button_onClick;
	public static BufferedImage[] big_blob_idle, big_blob_walk, big_blob_attack;
	public static BufferedImage[] evil_sorcerer_slide, evil_sorcerer_attack;
	public static BufferedImage[] dirt_tile, space_tile, rock_tile;

	private static int cnt = 0;

	public static void init() {

		// player
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/wizard_animation_sprite.png"));

		player_wizard_down = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_down[x] = playerSheet.crop(56 * x, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_left = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_left[x] = playerSheet.crop(56 * x, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_right = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_right[x] = playerSheet.crop(56 * x, 56 * 2, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_up = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_up[x] = playerSheet.crop(56 * x, 56 * 3, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_dead_down = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_dead_down[x] = playerSheet.crop(56 * x + 12 * 56, 0, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_dead_left = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_dead_left[x] = playerSheet.crop(56 * x + 12 * 56, 56, SPRITESHEET_WIDTH, SPRITESHEET_HEIGHT);
		}

		player_wizard_dead_right = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_dead_right[x] = playerSheet.crop(56 * x + 12 * 56, 56 * 2, SPRITESHEET_WIDTH,
					SPRITESHEET_HEIGHT);
		}

		player_wizard_dead_up = new BufferedImage[4];
		for (int x = 0; x < 4; x++) {
			player_wizard_dead_up[x] = playerSheet.crop(56 * x + 12 * 56, 56 * 3, SPRITESHEET_WIDTH,
					SPRITESHEET_HEIGHT);
		}

		// trees
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("res/texture/trees.png"));
		medium_spikey_tree = treeSheet.crop(129, 279, 60, 75);

		// dark dimension map
		SpriteSheet darkDimensionSheet = new SpriteSheet(
				ImageLoader.loadImage("res/texture/darkDimension/darkdimension_sheet.png"));
		rock_tile = new BufferedImage[2];
		rock_tile[0] = darkDimensionSheet.crop(16, 192, 16, 16);
		rock_tile[1] = darkDimensionSheet.crop(16, 192 + 16, 16, 16);

		dirt_tile = new BufferedImage[7];
		for (int x = 1; x < 8; x++) {
			dirt_tile[x - 1] = darkDimensionSheet.crop(16 * (x + 1), 16, 16, 16);
		}
		space_tile = new BufferedImage[8];
		for (int x = 0; x < 8; x++) {
			if (x < 4)
				space_tile[x] = darkDimensionSheet.crop(16 * x + 16, 240, 32, 32);
			else
				space_tile[x] = darkDimensionSheet.crop(16 * (x - 4) + 16, 272, 32, 32);
		}

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
			for (int y = 0; y < 8; y++) {
				if (x == 0 && y < 6) {
					big_blob_idle[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				} else if (x == 1) {
					big_blob_walk[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				} else if (x == 2) {
					big_blob_attack[y] = bigBlobSheet.crop(y * 80, x * 80, 80, 80);
				}
			}
		}

		SpriteSheet evilSorcererSheet = new SpriteSheet(
				ImageLoader.loadImage("res/texture/minion/evilWizard/sorcerer_sprite.png"));
		evil_sorcerer_attack = new BufferedImage[10];
		evil_sorcerer_slide = new BufferedImage[25];
		cnt = 0;
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				System.out.println(cnt);
				if (cnt == 35)
					break;
				if (cnt < 25) {
					evil_sorcerer_slide[cnt] = evilSorcererSheet.crop(x * 100, y * 100, 100, 100);
				} else {
					evil_sorcerer_attack[cnt - 25] = evilSorcererSheet.crop(x * 100, y * 100, 100, 100);
				}
				cnt++;
			}
		}

		// UI

		crosshair = ImageLoader.loadImage("res/texture/crosshairRed.png");

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
