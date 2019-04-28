package game.state.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.engine.GameThread;

public class NewMenuButton {

	private String status; // NORMAL, HOVER, CLICK
	private BufferedImage normal, hover, click;
	private final GameThread gameThread;
	private final int xPos, yPos, width, height;

	public NewMenuButton(GameThread gameThread, BufferedImage[] img, int xPos, int yPos, int width, int height) {
		this.gameThread = gameThread;
		this.normal = img[0];
		this.hover = img[1];
		this.click = img[2];
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;

		status = "NORMAL";
	}

	public String getStatus() {
		return status;
	}

	public void render(Graphics2D g2d) {
		if (status == "NORMAL") {
			g2d.drawImage(normal, xPos, yPos, width, height, null);
		} else if (status == "HOVER") {
			g2d.drawImage(hover, xPos, yPos, width, height, null);
		} else {
			g2d.drawImage(click, xPos, yPos, width, height, null);
		}
	}

}
