package com.pacman.system;

import com.pacman.model.support.GameEntity;


public interface IMovement {

	void moveUp(GameEntity entity);

	void moveDown(GameEntity entity);

	void moveLeft(GameEntity entity);

	void moveRight(GameEntity entity);

	boolean canMoveUp(GameEntity entity);

	boolean canMoveDown(GameEntity entity);

	boolean canMoveLeft(GameEntity entity);

	boolean canMoveRight(GameEntity entity);
}
