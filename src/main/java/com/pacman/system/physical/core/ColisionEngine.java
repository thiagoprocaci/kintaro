package com.pacman.system.physical.core;

import java.util.Collection;

import com.pacman.model.support.GameEntity;
import com.pacman.system.physical.IColisionEngine;

/**
 *
 * Gerenciador de colisoes
 *
 */
public class ColisionEngine implements IColisionEngine {

	@Override
	public boolean detectColision(GameEntity entity, GameEntity entity2) {
		return (entity.getShape().intersects(entity2.getShape()));
	}

	@Override
	public boolean detectColision(GameEntity entity, Collection<? extends GameEntity> collection) {
		for (GameEntity e : collection)
			if (detectColision(entity, e)) {
				return true;
			}
		return false;
	}

}
