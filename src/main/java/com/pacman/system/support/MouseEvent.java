package com.pacman.system.support;

import com.pacman.system.enumeration.MouseButton;

public class MouseEvent {
	private MouseButton pressedButton;
	private int x;
	private int y;



	public MouseEvent(MouseButton pressedButton, int x, int y) {
		this.pressedButton = pressedButton;
		this.x = x;
		this.y = y;
	}

	public MouseButton getPressedButton() {
		return pressedButton;
	}

	public void setPressedButton(MouseButton pressedButton) {
		this.pressedButton = pressedButton;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


}
