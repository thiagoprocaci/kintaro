package com.pacman.model;

import java.awt.Image;

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
	private Image[][] imageMatrix = new Image[4][3];
	private byte mouthStateCountHelper = 0;

	public PacMan() {
		super(10, 10, 3, 22, 22);
		previousValidX = getX();
		previousValidY = getY();
		loadImageMatrix();
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

	public Image getCurrentImage() {
		return imageMatrix[currentDirection.value()][currentMouthState.value()];
	}

	public void updateMouthState() {
		mouthStateCountHelper = mouthStateCountHelper < 0 ? 0 : mouthStateCountHelper;
		currentMouthState = MouthState.values()[mouthStateCountHelper % MouthState.values().length];
		mouthStateCountHelper++;
	}

	private void loadImageMatrix() {
		// mais desempenho utilizando a matriz de imagens..
		imageMatrix[Direction.UP.value()][MouthState.MOUTH_CLOSED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value());
		imageMatrix[Direction.UP.value()][MouthState.MOUTH_OPENING.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_UP.value());
		imageMatrix[Direction.UP.value()][MouthState.MOUTH_OPENED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_UP.value());

		imageMatrix[Direction.DOWN.value()][MouthState.MOUTH_CLOSED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value());
		imageMatrix[Direction.DOWN.value()][MouthState.MOUTH_OPENING.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_DOWN.value());
		imageMatrix[Direction.DOWN.value()][MouthState.MOUTH_OPENED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_DOWN.value());

		imageMatrix[Direction.LEFT.value()][MouthState.MOUTH_CLOSED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value());
		imageMatrix[Direction.LEFT.value()][MouthState.MOUTH_OPENING.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_LEFT.value());
		imageMatrix[Direction.LEFT.value()][MouthState.MOUTH_OPENED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_LEFT.value());

		imageMatrix[Direction.RIGHT.value()][MouthState.MOUTH_CLOSED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value());
		imageMatrix[Direction.RIGHT.value()][MouthState.MOUTH_OPENING.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_RIGHT.value());
		imageMatrix[Direction.RIGHT.value()][MouthState.MOUTH_OPENED.value()] = ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_RIGHT.value());
	}

}
