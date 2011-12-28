package com.pacman.model;

import java.awt.Graphics;
import java.util.Collection;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;
import com.pacman.model.support.IWorld;

/**
 * Entidade que representa o pacman
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class PacMan extends GameEntity {
	private static final int MOUTH_CLOSED = 0;
	private static final int MOUTH_OPENING = 1;
	private static final int MOUTH_OPENED = 2;
	private int currentMouthState = 1;
	private int currentDirection = LEFT;
	private int previousValidX;
	private int previousValidY;

	public PacMan() {
		super(10, 10, 3, 22, 22);
		setMoving(true);
		previousValidX = getX();
		previousValidY = getY();
	}

	/**
	 * Desenha PacMan
	 */
	@Override
	public void paint(Graphics graphics) {
		updateMouth();
		if (currentDirection == UP && currentMouthState == MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == UP && currentMouthState == MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_UP.value()), getX(), getY(), null);
		} else if (currentDirection == UP && currentMouthState == MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_UP.value()), getX(), getY(), null);
		} else if (currentDirection == DOWN && currentMouthState == MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == DOWN && currentMouthState == MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_DOWN.value()), getX(), getY(), null);
		} else if (currentDirection == DOWN && currentMouthState == MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_DOWN.value()), getX(), getY(), null);
		} else if (currentDirection == LEFT && currentMouthState == MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == LEFT && currentMouthState == MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_LEFT.value()), getX(), getY(), null);
		} else if (currentDirection == LEFT && currentMouthState == MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_LEFT.value()), getX(), getY(), null);
		} else if (currentDirection == RIGHT && currentMouthState == MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), getX(), getY(), null);
		} else if (currentDirection == RIGHT && currentMouthState == MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_RIGHT.value()), getX(), getY(), null);
		} else if (currentDirection == RIGHT && currentMouthState == MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_RIGHT.value()), getX(), getY(), null);
		}
	}

	@Override
	public void moveUp(IWorld world) {
		if (canMove(world, UP)) {
			currentDirection = UP;
			super.moveUp(world);
			eatFruit(world.getFruits());
		}
	}

	@Override
	public void moveDown(IWorld world) {
		if (canMove(world, DOWN)) {
			currentDirection = DOWN;
			super.moveDown(world);
			eatFruit(world.getFruits());
		}
	}

	@Override
	public void moveLeft(IWorld world) {
		if (canMove(world, LEFT)) {
			currentDirection = LEFT;
			super.moveLeft(world);
			eatFruit(world.getFruits());
		}
	}

	@Override
	public void moveRight(IWorld world) {
		if (canMove(world, RIGHT)) {
			currentDirection = RIGHT;
			super.moveRight(world);
			eatFruit(world.getFruits());
		}
	}

	/**
	 * Atualiza o estado da boca do pacMan
	 */
	private void updateMouth() {
		switch (currentMouthState) {
		case MOUTH_CLOSED:
			currentMouthState = MOUTH_OPENING;
			break;
		case MOUTH_OPENING:
			currentMouthState = MOUTH_OPENED;
			break;
		default:
			currentMouthState = MOUTH_CLOSED;
			break;
		}
	}

	private boolean canMove(IWorld world, int direction) {
		switch (direction) {
		case LEFT:
			super.moveLeft(world);
			break;
		case RIGHT:
			super.moveRight(world);
			break;
		case UP:
			super.moveUp(world);
			break;
		case DOWN:
			super.moveDown(world);
			break;
		}
		if (detectColision(world.getBlocks())) {
			setX(previousValidX);
			setY(previousValidY);
			return false;
		}
		previousValidX = getX();
		previousValidY = getY();
		return true;
	}

	/**
	 * Verifica se o pacMan comeu alguma fruta
	 *
	 * @param fruits
	 */
	private void eatFruit(Collection<Fruit> fruits) {
		if (fruits != null)
			for (Fruit fruit : fruits) {
				if (detectColision(fruit)) {
					fruit.setAlive(false);
					break;
				}
			}
	}
}
