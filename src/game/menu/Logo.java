package game.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Logo extends VBox {
	
	private ImageView upperLogo;
	private ImageView lowerLogo;

	public Logo() {
		super();
		setAlignment(Pos.CENTER);
		setSpacing(5);
		setPadding(new Insets(10, 10, 10, 10));
		ImageView upperLogo = new ImageView(MenuAssets.FX_upper_logo);
		ImageView lowerLogo = new ImageView(MenuAssets.FX_lower_logo);
		getChildren().addAll(upperLogo, lowerLogo);
	}

	public ImageView getUpperLogo() {
		return upperLogo;
	}

	public void setUpperLogo(ImageView upperLogo) {
		this.upperLogo = upperLogo;
	}

	public ImageView getLowerLogo() {
		return lowerLogo;
	}

	public void setLowerLogo(ImageView lowerLogo) {
		this.lowerLogo = lowerLogo;
	}

}
