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
	public static final int GHOST_DEFAULT_SPEED = 3;

	private boolean scared;
	private boolean shift;
	private int previousValidX;
	private int previousValidY;


	public Ghost(int x, int y) {
		super(x, y, GHOST_DEFAULT_SPEED, 23, 23);
		previousValidX = getX();
		previousValidY = getY();
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

	public int getPreviousValidX() {
		return previousValidX;
	}

	public void setPreviousValidX(int previousValidX) {
		this.previousValidX = previousValidX;
	}

	public int getPreviousValidY() {
		return previousValidY;
	}

	public void setPreviousValidY(int previousValidY) {
		this.previousValidY = previousValidY;
	}

}
