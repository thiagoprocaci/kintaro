package com.pacman.system.physical.core;

import com.pacman.model.Ghost;
import com.pacman.model.PacMan;
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
		if(actionDto != null) {
			Ghost ghost = (Ghost) actionDto.getMainEntity();
			PacMan pacMan = (PacMan) actionDto.getTargetEntity();
			// setando X
			if(pacMan.getX() < ghost.getX()) {
				movementEngine.moveLeft(ghost);				
			} else if(pacMan.getX() > ghost.getX()) {
				movementEngine.moveRight(ghost);
			}			
			// setando y
			if(pacMan.getY() < ghost.getY()) {
				movementEngine.moveUp(ghost);
			} else if(pacMan.getY() > ghost.getY()) {
				movementEngine.moveDown(ghost);
			}
		}
	}

}
