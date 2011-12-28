package com.pacman.model;

import java.awt.Color;
import java.awt.Graphics;

import com.pacman.model.support.GameEntity;

/**
 * Blocos que formam o cenario
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class Block extends GameEntity {
	public Block(int x, int y) {
		super(x, y, 0, 30, 30);
	}

	/**
	 * Desenha o bloco
	 */
	@Override
	public void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());

		graphics.setColor(Color.RED);
		graphics.drawRect(getX(), getY(), getWidth(), getHeight());
	}
}
