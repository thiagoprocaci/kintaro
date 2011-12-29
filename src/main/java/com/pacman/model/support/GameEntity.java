package com.pacman.model.support;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Entidade que representa um personagem do jogo. Qualquer objeto que tenha
 * coordenada x, y, largura e altura deve ser considerado um game entity.
 */
@SuppressWarnings("serial")
public class GameEntity implements IPaintable {

	private int x;
	private int y;
	private int speed;
	private int width;
	private int height;
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
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


	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
		this.shape = shape;
	}

	@Override
	public void paint(Graphics graphics) {

	}
}
