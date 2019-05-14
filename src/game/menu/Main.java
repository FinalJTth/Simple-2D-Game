package game.menu;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import game.display.MyGame;
import game.graphics.Assets;
import game.graphics.ImageLoader;
import game.graphics.SpriteSheet;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static BufferedImage[] play_button, sound_ON_button, sound_OFF_button, exit_button;
	public static BufferedImage play_button_normal, sound_ON_button_normal, sound_OFF_button_normal, exit_button_normal;
	public static BufferedImage play_button_hover, sound_ON_button_hover, sound_OFF_button_hover, exit_button_hover;
	public static BufferedImage play_button_onClick, sound_ON_button_onClick, sound_OFF_button_onClick,
			exit_button_onClick;
	
	public static Image startButtonUnselected, startButtonSelected, startButtonHover;
	public static Image exitButtonUnselected, exitButtonSelected, exitButtonHover;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub;
		init();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setPrefWidth(800);
		root.setPrefHeight(600);
		primaryStage.setHeight(600);
		
		MenuButton startButton = new MenuButton();
		
		startButton.setNormalImage(startButtonUnselected);
		startButton.setOnClickedImage(startButtonSelected);
		startButton.setHoverImage(startButtonHover);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new MyGame();
			}
		});
		
		MenuButton exitButton = new MenuButton();
		
		exitButton.setNormalImage(exitButtonUnselected);
		exitButton.setOnClickedImage(exitButtonSelected);
		exitButton.setHoverImage(exitButtonHover);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		root.getChildren().addAll(startButton, exitButton);
		Scene scene = new Scene(root);
		primaryStage.setTitle("JobJob Adventure");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void init() {
		
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
		
		play_button_normal = scaleImage(play_button_normal, 6);
		play_button_hover = scaleImage(play_button_hover, 6);
		play_button_onClick = scaleImage(play_button_onClick, 6);
		
		exit_button_normal = scaleImage(exit_button_normal, 6);
		exit_button_hover = scaleImage(exit_button_hover, 6);
		exit_button_onClick = scaleImage(exit_button_onClick, 6);
		
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
	}
	
	private static BufferedImage scaleImage(BufferedImage before, double scale) {
	    int w = before.getWidth();
	    int h = before.getHeight();
	    // Create a new image of the proper size
	    int w2 = (int) (w * scale);
	    int h2 = (int) (h * scale);
	    BufferedImage after = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
	    AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
	    AffineTransformOp scaleOp 
	        = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

	    scaleOp.filter(before, after);
	    return after;
	}
}
