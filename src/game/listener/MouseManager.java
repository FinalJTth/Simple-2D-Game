package game.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	private int mouseX, mouseY;
	@SuppressWarnings("unused")
	private boolean leftPressed, rightPressed;
	
	public MouseManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1)	// BUTTON1 == LMB
			leftPressed = true;
		else if (event.getButton() == MouseEvent.BUTTON3)	// BUTTON == RMB
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1)	// BUTTON1 == LMB
			leftPressed = false;
		else if (event.getButton() == MouseEvent.BUTTON3)	// BUTTON == RMB
			rightPressed = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
		// System.out.println(String.format("x : %d , y : %d",	mouseX, mouseY));
	}
	
	public String getMouseDirection() {
		return "";
	}
	
	public boolean isMousePressed() {
		return true;
	}
	
	// Getters & Setters
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
}
