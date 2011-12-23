package com.pacman.model;

import java.awt.Graphics;
import java.util.Collection;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;

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
	private PacMan pacAux;

	public PacMan() {
		super(10, 10, 3, 22, 22);
		setMoving(true);
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

	/**
	 * Move PacMan para cima
	 */
	public void moveUp(Collection<Block> blocks, Collection<Fruit> fruits) {
		if (canMove(blocks, UP)) {
			if (currentDirection != UP)
				currentDirection = UP;
			moveUp();
			eatFruit(fruits);
		}
	}

	/**
	 * Move PacMan para baixo
	 */
	public void moveDown(Collection<Block> blocks, Collection<Fruit> fruits) {
		if (canMove(blocks, DOWN)) {
			if (currentDirection != DOWN)
				currentDirection = DOWN;
			moveDown();
			eatFruit(fruits);
		}
	}

	/**
	 * Move PacMan para esquerda
	 */
	public void moveLeft(Collection<Block> blocks, Collection<Fruit> fruits) {
		if (canMove(blocks, LEFT)) {
			if (currentDirection != LEFT)
				currentDirection = LEFT;
			moveLeft();
			eatFruit(fruits);
		}
	}

	/**
	 * Move PacMan para direita
	 */
	public void moveRight(Collection<Block> blocks, Collection<Fruit> fruits) {
		if (canMove(blocks, RIGHT)) {
			if (currentDirection != RIGHT)
				currentDirection = RIGHT;
			moveRight();
			eatFruit(fruits);
		}
	}

	/**
	 * Atualiza o estado da boca do pacMan
	 */
	private void updateMouth() {
		if (currentMouthState == MOUTH_CLOSED)
			currentMouthState = MOUTH_OPENING;
		else if (currentMouthState == MOUTH_OPENING)
			currentMouthState = MOUTH_OPENED;
		else if (currentMouthState == MOUTH_OPENED)
			currentMouthState = MOUTH_CLOSED;
	}

	/**
	 * Verifica se o PacMan podera mover
	 *
	 * @param blocks
	 *
	 */
	private boolean canMove(Collection<Block> blocks, int direction) {
		if (pacAux == null)
			pacAux = new PacMan();
		pacAux.setX(getX());
		pacAux.setY(getY());
		if (direction == LEFT)
			pacAux.moveLeft();
		else if (direction == RIGHT)
			pacAux.moveRight();
		else if (direction == UP)
			pacAux.moveRight();
		else if (direction == DOWN)
			pacAux.moveDown();
		for (Block block : blocks)
			if (pacAux.detectColision(block))
				return false;
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
