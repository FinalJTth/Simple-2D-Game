package game.display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.engine.Screen;
import game.engine.ScreenFactory;
import game.state.GameState;

public class MyScreen extends Screen {

	private int x = 0, y = 0;
	@SuppressWarnings("unused")
	private String fps = new String();

	private GameState gameState;

	public MyScreen(ScreenFactory screenFactory) {
		super(screenFactory);
	}

	@Override
	public void onCreate() {
		System.out.println("Creating !");
		this.gameState = this.getScreenFactory().getGame().getGameThread().getGameState();
	}

	@Override
	public void onUpdate() {
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_A)) {
			x -= 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_D)) {
			x += 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_W)) {
			y -= 2;
		}
		if (this.getScreenFactory().getGame().getKeyboardListener().isKeyPressed(KeyEvent.VK_S)) {
			y += 2;
		}
		if (x >= super.getScreenFactory().getGame().getJFrame().getWidth() - 64) {
			x = 800 - 64;
		}
		if (x <= 0) {
			x = 0;
		}
		if (y >= super.getScreenFactory().getGame().getJFrame().getHeight() - 64) {
			y = 800 - 64;
		}
		if (y <= 0) {
			y = 0;
		}
		// gameState.update();
		// }
		// x++;
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		String fps = String.format("%d", super.getScreenFactory().getGame().getGameThread().getCurrentFPS());
		g2d.setColor(Color.black);
		// g2d.draw3DRect(x, y, 64, 64, true);
		g2d.fill3DRect(x, y, 64, 64, true);
		g2d.drawString(fps, 10, 20);
		g2d.drawString("x = " + x, 10, 40);
		g2d.drawString("y = " + y, 50, 40);
		System.out.println("Rendered");

		// gameState.render(g2d);
		// g2d.fillRect(x, y, 64, 64);
	}

}
