package com.pacman.model;

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

}
