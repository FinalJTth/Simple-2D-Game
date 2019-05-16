package game.menu;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import game.graphics.SpriteSheet;
import game.utils.ImageLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class MenuAssets {

	public static BufferedImage[] play_button, sound_ON_button, sound_OFF_button, exit_button;
	public static BufferedImage play_button_normal, sound_ON_button_normal, sound_OFF_button_normal, exit_button_normal;
	public static BufferedImage play_button_hover, sound_ON_button_hover, sound_OFF_button_hover, exit_button_hover;
	public static BufferedImage play_button_onClick, sound_ON_button_onClick, sound_OFF_button_onClick,
			exit_button_onClick;
	public static BufferedImage upper_logo, lower_logo;
//	public static BufferedImage retro_text_A, retro_text_B, retro_text_C, retro_text_D, retro_text_E, retro_text_F, retro_text_G,
//			retro_text_H, retro_text_I, retro_text_J, retro_text_K, retro_text_L, retro_text_M, retro_text_N;

	public static Image FX_upper_logo, FX_lower_logo;

	public static Image startButtonUnselected, startButtonSelected, startButtonHover;
	public static Image exitButtonUnselected, exitButtonSelected, exitButtonHover;
	public static Image soundButtonON, soundButtonOFF;
	
	public static Image bg;
//	public static Image FX_retro_text_A, FX_retro_text_B, FX_retro_text_C, FX_retro_text_D, FX_retro_text_E, FX_retro_text_F, FX_retro_text_G,
//			FX_retro_text_H, FX_retro_text_I, FX_retro_text_J, FX_retro_text_K, FX_retro_text_L, FX_retro_text_M, FX_retro_text_N;

	public static void init() {

//		startButtonUnselected = SwingFXUtils.toFXImage(Assets.play_button[0], null);
//		startButtonSelected = SwingFXUtils.toFXImage(Assets.play_button[2], null);
//		startButtonHover = SwingFXUtils.toFXImage(Assets.play_button[1], null);
//		
//		testImage = new Image(ClassLoader.getSystemResourceAsStream("res/menuButton/play.png"), 40, 21, true, true);

		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("res/menuButton/buttonSheet.png"));
		play_button_normal = buttonSheet.crop(1, 1, 38, 19);
		exit_button_normal = buttonSheet.crop(41 + 1, 1, 38, 19);
		sound_ON_button_normal = buttonSheet.crop(41 * 2 + 1, 1, 38, 19);
		sound_OFF_button_normal = buttonSheet.crop(41 * 3 + 1, 1, 38, 19);

		play_button_hover = buttonSheet.crop(1, 44, 38, 19);
		exit_button_hover = buttonSheet.crop(41 + 1, 44, 38, 19);
		sound_ON_button_hover = buttonSheet.crop(41 * 2 + 1, 44, 38, 19);
		sound_OFF_button_hover = buttonSheet.crop(41 * 3 + 1, 44, 38, 19);

		play_button_onClick = buttonSheet.crop(2, 24, 36, 17);
		exit_button_onClick = buttonSheet.crop(41 + 2, 24, 36, 17);
		sound_ON_button_onClick = buttonSheet.crop(41 * 2 + 2, 24, 36, 17);
		sound_OFF_button_onClick = buttonSheet.crop(41 * 3 + 2, 24, 36, 17);

		play_button_normal = scaleImage(play_button_normal, 8.4);
		play_button_hover = scaleImage(play_button_hover, 8.4);
		play_button_onClick = scaleImage(play_button_onClick, 8.4);

		exit_button_normal = scaleImage(exit_button_normal, 4);
		exit_button_hover = scaleImage(exit_button_hover, 4);
		exit_button_onClick = scaleImage(exit_button_onClick, 4);
		
		sound_ON_button_normal = scaleImage(sound_ON_button_normal, 4);
		sound_OFF_button_normal = scaleImage(sound_OFF_button_normal, 4);

		play_button = new BufferedImage[3];
		play_button[0] = play_button_normal;
		play_button[1] = play_button_onClick;
		play_button[2] = play_button_hover;

		exit_button = new BufferedImage[3];
		exit_button[0] = exit_button_normal;
		exit_button[1] = exit_button_onClick;
		exit_button[2] = exit_button_hover;

		sound_ON_button = new BufferedImage[3];
		sound_ON_button[0] = sound_ON_button_normal;
		sound_ON_button[1] = sound_ON_button_onClick;
		sound_ON_button[2] = sound_ON_button_hover;

		sound_OFF_button = new BufferedImage[3];
		sound_OFF_button[0] = sound_OFF_button_normal;
		sound_OFF_button[1] = sound_OFF_button_onClick;
		sound_OFF_button[2] = sound_OFF_button_hover;

		startButtonUnselected = SwingFXUtils.toFXImage(play_button[0], null);
		startButtonSelected = SwingFXUtils.toFXImage(play_button[1], null);
		startButtonHover = SwingFXUtils.toFXImage(play_button[2], null);

		exitButtonUnselected = SwingFXUtils.toFXImage(exit_button[0], null);
		exitButtonSelected = SwingFXUtils.toFXImage(exit_button[1], null);
		exitButtonHover = SwingFXUtils.toFXImage(exit_button[2], null);
		
		soundButtonON = SwingFXUtils.toFXImage(sound_ON_button_normal, null);
		soundButtonOFF = SwingFXUtils.toFXImage(sound_OFF_button_normal, null);

		upper_logo = ImageLoader.loadImage("res/text/upperLogo.png");
		lower_logo = ImageLoader.loadImage("res/text/lowerLogo.png");

		FX_upper_logo = SwingFXUtils.toFXImage(upper_logo, null);
		FX_lower_logo = SwingFXUtils.toFXImage(lower_logo, null);
		
		bg = new Image(ClassLoader.getSystemResourceAsStream("bg.gif"), 758, 590, true, true);

//		SpriteSheet retroTextSheet = new SpriteSheet(ImageLoader.loadImage("res/text/retroText.png"));
//		retro_text_A = retroTextSheet.crop(87, 379, 73, 87);
//		retro_text_B = retroTextSheet.crop(192, 379, 73, 87);
//		retro_text_C = retroTextSheet.crop(297, 379, 73, 87);
//		retro_text_D = retroTextSheet.crop(401, 379, 73, 87);
//		retro_text_E = retroTextSheet.crop(505, 379, 73, 87);
//		retro_text_F = retroTextSheet.crop(598, 379, 73, 87);
//		retro_text_G = retroTextSheet.crop(692, 379, 73, 87);
//		
//		retro_text_H = retroTextSheet.crop(87, 484, 73, 87);
//		retro_text_I = retroTextSheet.crop(192, 484, 73, 87);
//		retro_text_J = retroTextSheet.crop(297, 484, 73, 87);
//		retro_text_K = retroTextSheet.crop(401, 484, 73, 87);
//		retro_text_L = retroTextSheet.crop(505, 484, 73, 87);
//		retro_text_M = retroTextSheet.crop(598, 484, 73, 87);
//		retro_text_N = retroTextSheet.crop(692, 484, 73, 87);
//		
//		FX_retro_text_A = SwingFXUtils.toFXImage(retro_text_A, null);
//		FX_retro_text_B = SwingFXUtils.toFXImage(retro_text_B, null);
//		FX_retro_text_C = SwingFXUtils.toFXImage(retro_text_C, null);
//		FX_retro_text_D = SwingFXUtils.toFXImage(retro_text_D, null);
//		FX_retro_text_E = SwingFXUtils.toFXImage(retro_text_E, null);
//		FX_retro_text_F = SwingFXUtils.toFXImage(retro_text_F, null);
//		FX_retro_text_G = SwingFXUtils.toFXImage(retro_text_G, null);
//		
//		FX_retro_text_H = SwingFXUtils.toFXImage(retro_text_H, null);
//		FX_retro_text_I = SwingFXUtils.toFXImage(retro_text_I, null);
//		FX_retro_text_J = SwingFXUtils.toFXImage(retro_text_J, null);
//		FX_retro_text_K = SwingFXUtils.toFXImage(retro_text_K, null);
//		FX_retro_text_L = SwingFXUtils.toFXImage(retro_text_L, null);
//		FX_retro_text_M = SwingFXUtils.toFXImage(retro_text_M, null);
//		FX_retro_text_N = SwingFXUtils.toFXImage(retro_text_N, null);
	}

	private static BufferedImage scaleImage(BufferedImage before, double scale) {
		int w = before.getWidth();
		int h = before.getHeight();
		// Create a new image of the proper size
		int w2 = (int) (w * scale);
		int h2 = (int) (h * scale);
		BufferedImage after = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
		AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		scaleOp.filter(before, after);
		return after;
	}

}
