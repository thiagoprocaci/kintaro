package com.pacman.system.rendering.core;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.Ghost;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class GhostRenderingEngine implements IEntityRenderingEngine {

	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		Ghost ghost = (Ghost) entity;
		if (!ghost.isScared()) {
			if (ghost.isShift())
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_1.value()), ghost.getX(), ghost.getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_2.value()), ghost.getX(), ghost.getY(), null);
		} else {
			if (ghost.isShift())
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_1.value()), ghost.getX(), ghost.getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_2.value()), ghost.getX(), ghost.getY(), null);
		}
		ghost.setShift(!ghost.isShift());


	}

}
