package com.pacman.system.rendering.core;

import java.awt.Color;
import java.awt.Graphics;

import com.pacman.model.Block;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class BlockRenderingEngine implements IEntityRenderingEngine {

	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		Block block = (Block) entity;
		graphics.setColor(Color.WHITE);
		graphics.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());

		graphics.setColor(Color.RED);
		graphics.drawRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());

	}

}
