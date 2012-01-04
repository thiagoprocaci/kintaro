package com.pacman.system.rendering.core;

import java.awt.Graphics;

import com.pacman.model.PacMan;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class PacManRenderingEngine implements IEntityRenderingEngine {

	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		PacMan pacMan = (PacMan) entity;
		graphics.drawImage(pacMan.getCurrentImage(), pacMan.getX(), pacMan.getY(), null);

	}

}
