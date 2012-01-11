package com.pacman.system.physical.core;

import com.pacman.model.PacMan;
import com.pacman.model.enumeration.Direction;
import com.pacman.model.support.GameEntity;
import com.pacman.system.physical.ICollisionEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.physical.support.ActionDto;

public class PacManActionEngine implements IEntityActionEngine {

	private IMovementEngine movementEngine;
	private ICollisionEngine collisionEngine;

	public void setMovementEngine(IMovementEngine movementEngine) {
		this.movementEngine = movementEngine;
	}

	public void setColisionEngine(ICollisionEngine collisionEngine) {
		this.collisionEngine = collisionEngine;
	}

	// main enitity = pacman
	// secondary = blocks + fruits

	@Override
	public void act(ActionDto actionDto) {
		if (actionDto != null) {
			PacMan pacMan = (PacMan) actionDto.getMainEntity();
			if (actionDto.getDirection() != null && canMove(actionDto)) {
				pacMan.setCurrentDirection(actionDto.getDirection());
				move(actionDto);
				eatFruit(actionDto);
				pacMan.setPreviousValidX(pacMan.getX());
				pacMan.setPreviousValidY(pacMan.getY());
			}
		}
	}

	// TODO criar componente para esses metodos

	private boolean canMove(ActionDto actionDto) {
		PacMan pacMan = (PacMan) actionDto.getMainEntity();
		move(actionDto);
		boolean colision = true;
		if (collisionEngine.detectColision(pacMan, actionDto.getSecondaryEntities().get("block"))) {
			colision = false;
		}
		// retorna valores originais
		if (actionDto.getDirection() == Direction.LEFT || actionDto.getDirection() == Direction.RIGHT) {
			pacMan.setX(pacMan.getPreviousValidX());
		} else if (actionDto.getDirection() == Direction.UP || actionDto.getDirection() == Direction.DOWN) {
			pacMan.setY(pacMan.getPreviousValidY());
		}
		return colision;
	}

	private void move(ActionDto actionDto) {
		PacMan pacMan = (PacMan) actionDto.getMainEntity();
		switch (actionDto.getDirection()) {
		case LEFT:
			movementEngine.moveLeft(pacMan);
			break;
		case RIGHT:
			movementEngine.moveRight(pacMan);
			break;
		case UP:
			movementEngine.moveUp(pacMan);
			break;
		case DOWN:
			movementEngine.moveDown(pacMan);
			break;
		}
	}

	private void eatFruit(ActionDto actionDto) {
		for (GameEntity entity : actionDto.getSecondaryEntities().get("fruit")) {
			if (collisionEngine.detectColision(actionDto.getMainEntity(), entity)) {
				entity.setAlive(false);
				break;
			}
		}
	}

}
