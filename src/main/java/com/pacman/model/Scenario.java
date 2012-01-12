package com.pacman.model;

import com.pacman.model.support.GameEntity;

/**
 * Cenario do jogo
 */
@SuppressWarnings("serial")
public class Scenario extends GameEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Scenario() {
		super(0, 0, 0, 570, 450);
	}

}
