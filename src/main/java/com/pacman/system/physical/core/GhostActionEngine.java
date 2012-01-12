package com.pacman.system.physical.core;

import com.pacman.model.Ghost;
import com.pacman.model.PacMan;
import com.pacman.model.enumeration.Direction;
import com.pacman.system.physical.ICollisionEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.physical.support.ActionDto;

public class GhostActionEngine implements IEntityActionEngine {

	private IMovementEngine movementEngine;
	private ICollisionEngine collisionEngine;

	public void setMovementEngine(IMovementEngine movementEngine) {
		this.movementEngine = movementEngine;
	}

	public void setColisionEngine(ICollisionEngine collisionEngine) {
		this.collisionEngine = collisionEngine;
	}

	@Override
	public void act(ActionDto actionDto) {
		if (actionDto != null) {
			Ghost ghost = (Ghost) actionDto.getMainEntity();
			PacMan pacMan = (PacMan) actionDto.getTargetEntity();
			// setando X
			int diffX = Math.abs(pacMan.getX() - ghost.getX());
			if (diffX < ghost.getSpeed()) {
				ghost.setSpeed(diffX);
			}
			if (pacMan.getX() < ghost.getX()) {
				actionDto.setDirection(Direction.LEFT);
				if (canMove(actionDto)) {
					movementEngine.moveLeft(ghost);
				}
			} else if (pacMan.getX() > ghost.getX()) {
				actionDto.setDirection(Direction.RIGHT);
				if (canMove(actionDto)) {
					movementEngine.moveRight(ghost);
				}
			}
			ghost.setSpeed(Ghost.GHOST_DEFAULT_SPEED);

			// setando y
			int diffY = Math.abs(pacMan.getY() - ghost.getY());
			if (diffY < ghost.getSpeed()) {
				ghost.setSpeed(diffY);
			}
			if (pacMan.getY() < ghost.getY()) {
				actionDto.setDirection(Direction.UP);
				if (canMove(actionDto)) {
					movementEngine.moveUp(ghost);
				}
			} else if (pacMan.getY() > ghost.getY()) {
				actionDto.setDirection(Direction.DOWN);
				if (canMove(actionDto)) {
					movementEngine.moveDown(ghost);
				}
			}
			ghost.setSpeed(Ghost.GHOST_DEFAULT_SPEED);
			ghost.setPreviousValidX(ghost.getX());
			ghost.setPreviousValidY(ghost.getY());

		}
	}


	// TODO criar componente para esses metodos
	private boolean canMove(ActionDto actionDto) {
		Ghost ghost = (Ghost) actionDto.getMainEntity();
		move(actionDto);
		boolean colision = true;
		if (collisionEngine.detectColision(ghost, actionDto.getSecondaryEntities().get("block"))) {
			colision = false;
		}
		// retorna valores originais
		if (actionDto.getDirection() == Direction.LEFT || actionDto.getDirection() == Direction.RIGHT) {
			ghost.setX(ghost.getPreviousValidX());
		} else if (actionDto.getDirection() == Direction.UP || actionDto.getDirection() == Direction.DOWN) {
			ghost.setY(ghost.getPreviousValidY());
		}
		return colision;
	}

	private void move(ActionDto actionDto) {
		Ghost ghost = (Ghost) actionDto.getMainEntity();
		switch (actionDto.getDirection()) {
		case LEFT:
			movementEngine.moveLeft(ghost);
			break;
		case RIGHT:
			movementEngine.moveRight(ghost);
			break;
		case UP:
			movementEngine.moveUp(ghost);
			break;
		case DOWN:
			movementEngine.moveDown(ghost);
			break;
		}
	}

}
