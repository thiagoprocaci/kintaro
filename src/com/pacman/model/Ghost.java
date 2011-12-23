package com.pacman.model;

import java.awt.Graphics;
import java.util.Collection;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;

/**
 * Entidade que representa o mostro do jogo
 * 
 * @author Thiago Baesso Procaci
 * 
 */
@SuppressWarnings("serial")
public class Ghost extends GameEntity {
	private boolean scared;
	private boolean shift;
	private int currentDirection = LEFT;

	public Ghost(int x, int y) {
		super(x, y, 5, 23, 23);
		setMoving(true);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!scared) {
			if (shift)
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_1.value()), getX(), getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_2.value()), getX(), getY(), null);
		} else {
			if (shift)
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_1.value()), getX(), getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_2.value()), getX(), getY(), null);
		}
		shift = !shift;
	}

	public void setScared(boolean scared) {
		this.scared = scared;
	}

	public void move(Collection<Block> blocks) {		
		currentDirection = moveWhere(blocks);
		if (currentDirection == UP)
			moveUp();
		else if (currentDirection == DOWN)
			moveDown();
		else if (currentDirection == LEFT)
			moveLeft();
		else if (currentDirection == RIGHT)
			moveRight();
	}

	/**
	 * Metodo que verifica qual po
	 * 
	 * @param blocks
	 * 
	 */
	private int moveWhere(Collection<Block> blocks) {
		Ghost ghost = new Ghost(getX(), getY());
		int numberColisionUp = 10;
		int numberColisionDown = 10;
		int numberColisionLeft = 10;
		int numberColisionRight = 10;
		int count = 0;
		int x = 0;
		int y = 0;
		if (ghost.canMoveUp()) {
			numberColisionUp = 0;
			count = 0;
			ghost.moveUp();
			x = ghost.getX();
			y = ghost.getY();
			while (count < 4) {
				ghost.setX(x);
				ghost.setY(y);
				if (count == 1)
					ghost.moveDown();
				else if (count == 2)
					ghost.moveLeft();
				else if (count == 3)
					ghost.moveRight();
				for (Block block : blocks)
					if (ghost.detectColision(block))
						numberColisionUp++;
				count++;
			}
		}
		ghost.setX(getX());
		ghost.setY(getY());
		if (ghost.canMoveDown()) {
			numberColisionDown = 0;
			count = 0;
			ghost.moveDown();
			x = ghost.getX();
			y = ghost.getY();
			while (count < 4) {
				ghost.setX(x);
				ghost.setY(y);
				if (count == 1)
					ghost.moveUp();
				else if (count == 2)
					ghost.moveLeft();
				else if (count == 3)
					ghost.moveRight();
				for (Block block : blocks)
					if (ghost.detectColision(block))
						numberColisionDown++;
				count++;
			}
		}
		ghost.setX(getX());
		ghost.setY(getY());
		if (ghost.canMoveLeft()) {
			numberColisionLeft = 0;
			count = 0;
			ghost.moveLeft();
			x = ghost.getX();
			y = ghost.getY();
			while (count < 4) {
				ghost.setX(x);
				ghost.setY(y);
				if (count == 1)
					ghost.moveUp();
				else if (count == 2)
					ghost.moveDown();
				else if (count == 3)
					ghost.moveRight();
				for (Block block : blocks)
					if (ghost.detectColision(block))
						numberColisionLeft++;
				count++;
			}
		}
		ghost.setX(getX());
		ghost.setY(getY());
		if (ghost.canMoveRight()) {
			numberColisionRight = 0;
			count = 0;
			ghost.moveRight();
			x = ghost.getX();
			y = ghost.getY();
			while (count < 4) {
				ghost.setX(x);
				ghost.setY(y);
				if (count == 1)
					ghost.moveUp();
				else if (count == 2)
					ghost.moveDown();
				else if (count == 3)
					ghost.moveLeft();
				for (Block block : blocks)
					if (ghost.detectColision(block))
						numberColisionRight++;
				count++;
			}
		}
		System.out.println(numberColisionUp + "," + numberColisionDown + "," + numberColisionLeft + "," + numberColisionRight);
		if (numberColisionUp <= numberColisionDown && numberColisionUp <= numberColisionLeft && numberColisionUp <= numberColisionRight) {
			return UP;
		}
		if (numberColisionDown <= numberColisionUp && numberColisionDown <= numberColisionLeft && numberColisionDown <= numberColisionRight) {
			return DOWN;
		}
		if (numberColisionLeft <= numberColisionUp && numberColisionLeft <= numberColisionDown && numberColisionLeft <= numberColisionRight) {
			return LEFT;
		}
		return RIGHT;
	}
}
