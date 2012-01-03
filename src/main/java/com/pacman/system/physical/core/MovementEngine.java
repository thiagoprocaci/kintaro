package com.pacman.system.physical.core;

import com.pacman.model.support.GameEntity;
import com.pacman.model.support.IWorld;
import com.pacman.system.physical.IMovementEngine;

/**
 *
 * Entidade que representa um o movimento geral de qualquer personagem do jogo.
 *
 */
public class MovementEngine implements IMovementEngine {

	@Override
	public void moveUp(GameEntity entity) {
		if (canMoveUp(entity)) {
			entity.setY(entity.getY() - entity.getSpeed());
		}
	}

	@Override
	public void moveDown(GameEntity entity) {
		if (canMoveDown(entity)) {
			entity.setY(entity.getY() + entity.getSpeed());
		}
	}

	@Override
	public void moveLeft(GameEntity entity) {
		if (canMoveLeft(entity)) {
			entity.setX(entity.getX() - entity.getSpeed());
		}
	}

	@Override
	public void moveRight(GameEntity entity) {
		if (canMoveRight(entity)) {
			entity.setX(entity.getX() + entity.getSpeed());
		}
	}

	@Override
	public boolean canMoveUp(GameEntity entity) {
		return (entity.getX() > IWorld.MIN_X && entity.getY() - entity.getSpeed() > IWorld.MIN_Y);
	}

	@Override
	public boolean canMoveDown(GameEntity entity) {
		return (entity.getX() < IWorld.MAX_X && entity.getY() + entity.getSpeed() < IWorld.MAX_Y);
	}

	@Override
	public boolean canMoveLeft(GameEntity entity) {
		return (entity.getX() - entity.getSpeed() > IWorld.MIN_X && entity.getY() > IWorld.MIN_Y);
	}

	@Override
	public boolean canMoveRight(GameEntity entity) {
		return (entity.getX() + entity.getSpeed() < IWorld.MAX_X && entity.getY() < IWorld.MAX_Y);
	}

}
