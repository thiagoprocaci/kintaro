package com.pacman.model;

import com.pacman.model.support.GameEntity;

/**
 * Entidade que representa o mostro do jogo
 *
 *
 *
 */
@SuppressWarnings("serial")
public class Ghost extends GameEntity {
	private boolean scared;
	private boolean shift;

	public Ghost(int x, int y) {
		super(x, y, 3, 23, 23);
	}

	public boolean isShift() {
		return shift;
	}

	public void setShift(boolean shift) {
		this.shift = shift;
	}

	public boolean isScared() {
		return scared;
	}

	public void setScared(boolean scared) {
		this.scared = scared;
	}

}
