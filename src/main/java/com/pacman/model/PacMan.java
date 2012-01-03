package com.pacman.model;

import com.pacman.model.enumeration.Direction;
import com.pacman.model.enumeration.MouthState;
import com.pacman.model.support.GameEntity;

/**
 * Entidade que representa o pacman
 */
@SuppressWarnings("serial")
public class PacMan extends GameEntity {

	private MouthState currentMouthState = MouthState.MOUTH_CLOSED;
	private Direction currentDirection = Direction.LEFT;
	private int previousValidX;
	private int previousValidY;

	public PacMan() {
		super(10, 10, 3, 22, 22);
		previousValidX = getX();
		previousValidY = getY();
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	public MouthState getCurrentMouthState() {
		return currentMouthState;
	}

	public void setCurrentMouthState(MouthState currentMouthState) {
		this.currentMouthState = currentMouthState;
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
