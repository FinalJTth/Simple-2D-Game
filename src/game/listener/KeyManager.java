package game.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys = new boolean[256];

	public boolean up, down, left, right, spacebar, escape, q;
	public boolean num_1, num_2, num_3;

	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		spacebar = keys[KeyEvent.VK_SPACE];
		escape = keys[KeyEvent.VK_ESCAPE];
		q = keys[KeyEvent.VK_Q];
		num_1 = keys[KeyEvent.VK_1];
		num_2 = keys[KeyEvent.VK_2];
		num_3 = keys[KeyEvent.VK_3];
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		this.keys[event.getKeyCode()] = true;	// Pressed
	}

	@Override
	public void keyReleased(KeyEvent event) {
		this.keys[event.getKeyCode()] = false;	// Not pressed
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}
	
	public boolean isKeyPressed(int key) {
		return keys[key];
	}
	
	public boolean isKeyReleased(int key) {
		return !keys[key];
	}
	
}
