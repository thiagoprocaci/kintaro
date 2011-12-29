package com.pacman.model;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;

/**
 * Cenario do jogo
 */
@SuppressWarnings("serial")
public class Scenario extends GameEntity {

	public Scenario() {
		super(0, 0, 0, 570, 450);
	}

	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.SPACE_PAC.value()), getX(), getY(), null);
	}
}
