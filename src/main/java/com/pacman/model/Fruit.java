package com.pacman.model;

import java.awt.Color;
import java.awt.Graphics;

import com.pacman.model.support.GameEntity;

/**
 * Entidade que representa a fruta que o pacMan come
 * 
 * @author Thiago Baesso Procaci
 * 
 */
@SuppressWarnings("serial")
public class Fruit extends GameEntity {
	private boolean special = false;

	public Fruit(int x, int y, boolean special) {
		super(x, y, 0);
		this.special = special;
		if (special) {
			setWidth(10);
			setHeight(10);
		} else {
			setWidth(7);
			setHeight(7);
		}
	}

	/**
	 * Desenha a fruta
	 */
	@Override
	public void paint(Graphics graphics) {
		if (isAlive()) {
			if (special)
				graphics.setColor(Color.GREEN);
			else
				graphics.setColor(Color.ORANGE);
			graphics.fillOval(getX(), getY(), getWidth(), getHeight());
		}
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}
}
