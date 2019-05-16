package game.menu;

import game.engine.GameThread;
import game.soundFX.SoundPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox {

	private MenuButton startButton;
	private MenuButton exitButton;
	private MenuButton soundToggleButton;
	private GameThread gameThread;

	public ButtonPanel() {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(5);
		setPadding(new Insets(10, 10, 10, 10));
		startButton = new MenuButton();

		startButton.setNormalImage(MenuAssets.startButtonUnselected);
		startButton.setOnClickedImage(MenuAssets.startButtonSelected);
		startButton.setHoverImage(MenuAssets.startButtonHover);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SoundPlayer.menuBgm.stopPlaying();
				gameThread = new GameThread();
				new Thread(gameThread).start();
				Platform.setImplicitExit(false);
				Main.primaryStage.hide();
			}
		});

		exitButton = new MenuButton();

		exitButton.setNormalImage(MenuAssets.exitButtonUnselected);
		exitButton.setOnClickedImage(MenuAssets.exitButtonSelected);
		exitButton.setHoverImage(MenuAssets.exitButtonHover);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		soundToggleButton = new MenuButton();
		soundToggleButton.setNormalImage(MenuAssets.soundButtonON);
		soundToggleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				SoundPlayer.toggleSound();
				if (soundToggleButton.getNormalImage().equals(MenuAssets.soundButtonON)) {
					soundToggleButton.setNormalImage(MenuAssets.soundButtonOFF);
				} else {
					soundToggleButton.setNormalImage(MenuAssets.soundButtonON);
				}
			}
		});

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(soundToggleButton, exitButton);

		getChildren().addAll(startButton, hbox);
	}

	public MenuButton getStartButton() {
		return startButton;
	}

	public void setStartButton(MenuButton startButton) {
		this.startButton = startButton;
	}

	public MenuButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(MenuButton exitButton) {
		this.exitButton = exitButton;
	}

	public MenuButton getSoundToggleButton() {
		return soundToggleButton;
	}

	public void setSoundToggleButton(MenuButton soundToggleButton) {
		this.soundToggleButton = soundToggleButton;
	}

	public GameThread getGameThread() {
		return gameThread;
	}

	public void setGameThread(GameThread gameThread) {
		this.gameThread = gameThread;
	}

}
