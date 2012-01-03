package com.pacman.system.rendering.core;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.Scenario;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class ScenarioRenderingEngine implements IEntityRenderingEngine {


	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		Scenario scenario = (Scenario) entity;
		graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.SPACE_PAC.value()), scenario.getX(), scenario.getY(), null);
	}

}
