package game.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.engine.GameThread;
import game.graphics.Assets;
import game.listener.KeyManager;
import game.listener.MouseManager;
import game.state.menu.NewMenuButton;

public class MenuState extends State {

	private KeyManager keyManager;
	private MouseManager mouseManager;
	private NewMenuButton playButton, soundONButton, soundOFFButton, exitButton;

	public MenuState(GameThread gameThread) {
		super(gameThread);

		playButton = new NewMenuButton(gameThread, Assets.play_button, 0, 0, 40 * 2, 21 * 2);
		soundONButton = new NewMenuButton(gameThread, Assets.sound_ON_button, 0, 100, 40 * 2, 21 * 2);
		soundOFFButton = new NewMenuButton(gameThread, Assets.sound_OFF_button, 0, 200, 40 * 2, 21 * 2);
		exitButton = new NewMenuButton(gameThread, Assets.exit_button, 0, 300, 40 * 2, 21 * 2);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D g2d) {
		playButton.render(g2d);
		soundONButton.render(g2d);
		soundOFFButton.render(g2d);
		exitButton.render(g2d);
	}
}
