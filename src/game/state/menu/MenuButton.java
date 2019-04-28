package game.state.menu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuButton extends Button{
	
	private final String name;
	
	public MenuButton(String name, String text, String imgUrl) {
		
		setPadding(new Insets(5));
		setText(text);
		this.name = name;
		this.setGraphic(new ImageView("file:res/menuButton/" + imgUrl));
	}
	
	public String getName() {
		return name;
	}
	
}
