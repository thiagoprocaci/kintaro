package com.pacman.system.core;

import java.util.Collection;

import com.pacman.model.support.GameEntity;
import com.pacman.system.IColisionManager;

/**
 *
 * Gerenciador de colisoes
 *
 */
public class ColisionManager implements IColisionManager {
	private static ColisionManager INSTANCE = new ColisionManager();

	private ColisionManager() {

	}

	public static ColisionManager getInstance() {
		return INSTANCE;
	}

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
