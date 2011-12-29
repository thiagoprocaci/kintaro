package com.pacman.model;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
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

	/**
	 * Desenha PacMan
	 */
	@Override
	public void paint(Graphics graphics) {
		if (currentDirection == Direction.UP && currentMouthState == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.UP && currentMouthState == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_UP.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.UP && currentMouthState == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_UP.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.DOWN && currentMouthState == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.DOWN && currentMouthState == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_DOWN.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.DOWN && currentMouthState == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_DOWN.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.LEFT && currentMouthState == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.LEFT && currentMouthState == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_LEFT.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.LEFT && currentMouthState == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_LEFT.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.RIGHT && currentMouthState == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.RIGHT && currentMouthState == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_RIGHT.value()), getX(), getY(), null);
		} else if (currentDirection == Direction.RIGHT && currentMouthState == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_RIGHT.value()), getX(), getY(), null);
		}
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
