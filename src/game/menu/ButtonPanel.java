package game.menu;

import game.engine.GameThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox {
	
	private MenuButton startButton;
	private MenuButton exitButton;

	public ButtonPanel() {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(5);
		setPadding(new Insets(10, 10, 10, 10));
		MenuButton startButton = new MenuButton();
		
		startButton.setNormalImage(MenuAssets.startButtonUnselected);
		startButton.setOnClickedImage(MenuAssets.startButtonSelected);
		startButton.setHoverImage(MenuAssets.startButtonHover);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Thread(new GameThread()).start();
			}
		});
		
		MenuButton exitButton = new MenuButton();
		
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
		getChildren().addAll(startButton, exitButton);
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

}
