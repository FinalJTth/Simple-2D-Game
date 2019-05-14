package game.state.menu;

import game.engine.GameThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox {

	private final MenuButton startButton, quitButton;
	private final ToggleMenuButton soundToggleButton;
	private final GameThread gameThread;
	
	public MenuPane(GameThread gameThread) {
		this.gameThread = gameThread;
		
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(10));
		
		startButton = new MenuButton("start", "play", "play.png");
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				gameThread.setGameState();
			}
		});
		
		soundToggleButton = new ToggleMenuButton("sound", "sound", "sound.png");
		soundToggleButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				soundToggleButton.toggle();
				// do something when clicked on
				if (soundToggleButton.getStatus() == "OFF") {
					// TODO turn sound off
				} else {
					// TODO turn sound on
				}
			}
		});
		
		quitButton = new MenuButton("quit", "quit", "quit.png");
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				MenuPane.this.gameThread.getWindow().dispose();
			}
		});
		
		getChildren().addAll(startButton, soundToggleButton, quitButton);
	}
	
}
