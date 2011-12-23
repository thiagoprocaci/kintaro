package com.pacman.model.support;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Random;

/**
 * Entidade que representa um personagem do jogo.
 * Qualquer objeto que tenha coordenada x, y, largura e altura deve ser considerado um game entity.
 */
@SuppressWarnings("serial")
public abstract class GameEntity implements IPaintable {

	public static final int MAX_X = 540;
	public static final int MAX_Y = 430;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;

	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int UP = 2;
	protected static final int DOWN = 3;
	protected static final int DIRECTION_NOT_DEFINED = 4;
	private static Random random = new Random();

	private int x;
	private int y;
	private int speed;
	private int width;
	private int height;
	private boolean moving = false;
	private boolean alive = true;

	// variavel auxiliar para fazer o bounding box
	private Rectangle shape;


	public GameEntity(int x, int y, int speed, int width, int height) {
		this.shape = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;

	}

	public GameEntity(int x, int y, int speed) {
		this.shape = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	/**
	 * Verifica se houve colisao entre dois personagens do jogo.
	 */
	public boolean detectColision(GameEntity entity) {
		return (entity.shape.intersects(shape));
	}

	/**
	 * Verifica se houve colisao entre um personagem com uma colecao de
	 * personagens
	 *
	 * @param collection
	 *
	 */
	public boolean detectColision(Collection<? extends GameEntity> collection) {
		for (GameEntity actor : collection)
			if (detectColision(actor)) {
				return true;
			}
		return false;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		shape.setBounds(x, y, width, height);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		shape.setBounds(x, y, width, height);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		shape.setBounds(x, y, width, height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		shape.setBounds(x, y, width, height);
	}

	public Dimension getDimension() {
		return new Dimension(width, height);
	}

	protected void moveUp() {
		if (canMoveUp()) {
			setY(getY() - getSpeed());
		}
	}

	protected void moveDown() {
		if (canMoveDown()) {
			setY(getY() + getSpeed());
		}
	}

	protected void moveLeft() {
		if (canMoveLeft()) {
			setX(getX() - getSpeed());
		}

	}

	protected void moveRight() {
		if (canMoveRight()) {
			setX(getX() + getSpeed());
		}
	}

	// os canMoves da entitdade abstrada somente confere os limites do mundo.
	// nao levam em conta a presenca de obstaculos

	protected boolean canMoveUp() {
		return (isMoving() && getX() > MIN_X && getY() - getSpeed() > MIN_Y);
	}

	protected boolean canMoveDown() {
		return (isMoving() && getX() < MAX_X && getY() + getSpeed() < MAX_Y);
	}

	protected boolean canMoveLeft() {
		return (isMoving() && getX() - getSpeed() > MIN_X && getY() > MIN_Y);
	}

	protected boolean canMoveRight() {
		return (isMoving() && getX() + getSpeed() < MAX_X && getY() < MAX_Y);
	}


	protected int generateRandomDirection(int directionNotDesired) {
		int randomNumber = random.nextInt(4);
		while (randomNumber == x)
			randomNumber = random.nextInt(4);
		return randomNumber;
	}

}
