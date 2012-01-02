package com.pacman.system.physical.core;

import java.util.Collection;

import com.pacman.model.Block;
import com.pacman.model.Fruit;
import com.pacman.model.PacMan;
import com.pacman.model.enumeration.Direction;
import com.pacman.model.enumeration.MouthState;
import com.pacman.system.physical.IColisionManager;
import com.pacman.system.physical.IEntityActionManager;
import com.pacman.system.physical.IMovementManager;

public class PacManActionManager implements IEntityActionManager {

	private IMovementManager movementManager;
	private IColisionManager colisionManager;
	private PacMan pacMan;
	private Collection<Block> blockList;
	private Collection<Fruit> fruitList;

	public PacManActionManager(PacMan pacMan, Collection<Block> blockList, Collection<Fruit> fruitList) {
		this.pacMan = pacMan;
		this.blockList = blockList;
		this.fruitList = fruitList;
	}

	public void setMovementManager(IMovementManager movementManager) {
		this.movementManager = movementManager;
	}

	public void setColisionManager(IColisionManager colisionManager) {
		this.colisionManager = colisionManager;
	}

	@Override
	public void act(Direction direction) {
		updateMouthState();
		if(direction != null && canMove(direction)) {
			pacMan.setCurrentDirection(direction);
			move(direction);
			eatFruit(fruitList);
		}

	}

	private boolean canMove(Direction direction) {
		move(direction);
		if (colisionManager.detectColision(pacMan, blockList)) {
			pacMan.setX(pacMan.getPreviousValidX());
			pacMan.setY(pacMan.getPreviousValidY());
			return false;
		}
		pacMan.setPreviousValidX(pacMan.getX());
		pacMan.setPreviousValidY(pacMan.getY());
		return true;
	}

	private void move(Direction direction) {
		switch (direction) {
		case LEFT:
			movementManager.moveLeft(pacMan);
			break;
		case RIGHT:
			movementManager.moveRight(pacMan);
			break;
		case UP:
			movementManager.moveUp(pacMan);
			break;
		case DOWN:
			movementManager.moveDown(pacMan);
			break;
		}
	}

	private void eatFruit(Collection<Fruit> fruits) {
		if (fruits != null)
			for (Fruit fruit : fruits) {
				if (colisionManager.detectColision(pacMan, fruit)) {
					fruit.setAlive(false);
					break;
				}
			}
	}

	/**
	 * Atualiza o estado da boca do pacMan
	 */
	private void updateMouthState() {
		switch (pacMan.getCurrentMouthState()) {
		case MOUTH_CLOSED:
			pacMan.setCurrentMouthState(MouthState.MOUTH_OPENING);
			break;
		case MOUTH_OPENING:
			pacMan.setCurrentMouthState(MouthState.MOUTH_OPENED);
			break;
		default:
			pacMan.setCurrentMouthState(MouthState.MOUTH_CLOSED);
			break;
		}
	}



}
