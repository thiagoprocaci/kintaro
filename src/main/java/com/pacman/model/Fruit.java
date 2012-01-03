package com.pacman.model;

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

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}
}
