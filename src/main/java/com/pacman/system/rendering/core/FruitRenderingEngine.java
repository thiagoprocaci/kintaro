package com.pacman.system.rendering.core;

import java.awt.Color;
import java.awt.Graphics;

import com.pacman.model.Fruit;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class FruitRenderingEngine implements IEntityRenderingEngine {

	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		Fruit fruit = (Fruit) entity;
		if (fruit.isAlive()) {
			if (fruit.isSpecial()) {
				graphics.setColor(Color.GREEN);
			} else {
				graphics.setColor(Color.ORANGE);
			}
			graphics.fillOval(fruit.getX(), fruit.getY(), fruit.getWidth(), fruit.getHeight());
		}

	}

}
