package game.menu;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuButton extends Button {
	private Image normalImage;
	private Image onClickedImage;
	private Image hoverImage;
	private ImageView imageView;
	
	public MenuButton(Image image) {
		super();
		this.normalImage = image;
		this.imageView = new ImageView(image);
		getChildren().add(imageView);
		super.setGraphic(imageView);
		setStyle("    -fx-background-color: transparent;\r\n" + 
				"    -fx-background-insets: 0 0 0 0, 0, 0, 0;\r\n" + 
				"    -fx-background-radius: 0 0 0 0, 0, 0, 0;\r\n" + 
				"    -fx-border-width: 0 0 0 0, 0, 0, 0;");
	}
	
	public MenuButton() {
		super();
		this.imageView = new ImageView();
		getChildren().add(imageView);
		super.setGraphic(imageView);
		setStyle("    -fx-background-color: transparent;\r\n" + 
				"    -fx-background-insets: 0 0 0 0, 0, 0, 0;\r\n" + 
				"    -fx-background-radius: 0 0 0 0, 0, 0, 0;\r\n" + 
				"    -fx-border-width: 0 0 0 0, 0, 0, 0;");
	}

	public Image getNormalImage() {
		return normalImage;
	}

	public void setNormalImage(Image normalImage) {
		this.normalImage = normalImage;
		imageView.setImage(normalImage);
	}

	public Image getOnClickedImage() {
		return onClickedImage;
	}

	public void setOnClickedImage(Image onClickedImage) {
		this.onClickedImage = onClickedImage;
		super.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				imageView.setImage(onClickedImage);
			}
		});
		super.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				imageView.setImage(normalImage);
			}
		});
	}

	public Image getHoverImage() {
		return hoverImage;
	}

	public void setHoverImage(Image hoverImage) {
		this.hoverImage = hoverImage;
		super.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				imageView.setImage(hoverImage);
			}
		});
		super.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				imageView.setImage(normalImage);
			}
		});
	}

	public ImageView getImageView() {
		return imageView;
	}
}
