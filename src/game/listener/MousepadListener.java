package game.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousepadListener implements MouseListener {
	
	private int mouse_x, mouse_y;
	@SuppressWarnings("unused")
	private boolean clicked;

	@Override
	public void mouseClicked(MouseEvent event) {
		this.mouse_x = event.getX();
		this.mouse_y = event.getY();
		clicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		mouseClicked(event);
		this.clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.clicked = false;
	}
	
	public boolean isMousePressed() {
		return true;
	}
	
	public int getX() {
		return this.mouse_x;
	}
	public int getY() {
		return this.mouse_y;
	}
}
