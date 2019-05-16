package game.menu;

import game.soundFX.SoundPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuAssets.init();
		SoundPlayer.initMenuSound();
		VBox root = new VBox();

		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setPrefWidth(800);
		root.setPrefHeight(600);
		primaryStage.setHeight(600);

		Logo logo = new Logo();
		ButtonPanel buttonPanel = new ButtonPanel();
		root.setBackground(new Background(new BackgroundImage(MenuAssets.bg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(758, 590, true, true, true, true))));

//		ImageView iv1 = new ImageView(FX_retro_text_A);
//		ImageView iv2 = new ImageView(FX_retro_text_B);
//		ImageView iv3 = new ImageView(FX_retro_text_C);
//		ImageView iv4 = new ImageView(FX_retro_text_D);
//		ImageView iv5 = new ImageView(FX_retro_text_E);
//		ImageView iv6 = new ImageView(FX_retro_text_F);
//		ImageView iv7 = new ImageView(FX_retro_text_G);
//		
//		ImageView iv8 = new ImageView(FX_retro_text_H);
//		ImageView iv9 = new ImageView(FX_retro_text_I);
//		ImageView iv10 = new ImageView(FX_retro_text_J);
//		ImageView iv11 = new ImageView(FX_retro_text_K);
//		ImageView iv12 = new ImageView(FX_retro_text_L);
//		ImageView iv13 = new ImageView(FX_retro_text_M);
//		ImageView iv14 = new ImageView(FX_retro_text_N);
//		logo1.getChildren().addAll(iv1, iv2, iv3, iv4, iv5, iv6, iv7);
//		logo2.getChildren().addAll(iv8, iv9, iv10, iv11, iv12, iv13, iv14);
		root.getChildren().addAll(logo, buttonPanel);
		Scene scene = new Scene(root);
		primaryStage.setTitle("JobJob Adventure");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SoundPlayer.menuBgm.setVolume(0.5);
				SoundPlayer.menuBgm.playNonStop();
			}
		});
		
	}
}
