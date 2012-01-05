package com.pacman.system.physical.core;

import com.pacman.model.PacMan;
import com.pacman.model.support.GameEntity;
import com.pacman.system.physical.ICollisionEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.physical.support.ActionDto;

public class PacManActionEngine implements IEntityActionEngine {

	private IMovementEngine movementEnginer;
	private ICollisionEngine collisionEngine;

	public void setMovementEngine(IMovementEngine movementEngine) {
		this.movementEnginer = movementEngine;
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
			}
		}
	}

	private boolean canMove(ActionDto actionDto) {
		PacMan pacMan = (PacMan) actionDto.getMainEntity();
		move(actionDto);
		if (collisionEngine.detectColision(pacMan, actionDto.getSecondaryEntities().get("block"))) {
			pacMan.setX(pacMan.getPreviousValidX());
			pacMan.setY(pacMan.getPreviousValidY());
			return false;
		}
		pacMan.setPreviousValidX(pacMan.getX());
		pacMan.setPreviousValidY(pacMan.getY());
		return true;
	}

	private void move(ActionDto actionDto) {
		PacMan pacMan = (PacMan) actionDto.getMainEntity();
		switch (actionDto.getDirection()) {
		case LEFT:
			movementEnginer.moveLeft(pacMan);
			break;
		case RIGHT:
			movementEnginer.moveRight(pacMan);
			break;
		case UP:
			movementEnginer.moveUp(pacMan);
			break;
		case DOWN:
			movementEnginer.moveDown(pacMan);
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
