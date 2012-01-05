package com.pacman.system.physical.support;

import java.util.Collection;
import java.util.Map;

import com.pacman.model.enumeration.Direction;
import com.pacman.model.support.GameEntity;

public class ActionDto {

	private Direction direction;
	private GameEntity mainEntity;


	// criar um target entity

	// entidades para deteccao de colisoes
	private Map<String, Collection<? extends GameEntity>> secondaryEntities;

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public GameEntity getMainEntity() {
		return mainEntity;
	}
	public void setMainEntity(GameEntity mainEntity) {
		this.mainEntity = mainEntity;
	}
	public Map<String, Collection<? extends GameEntity>> getSecondaryEntities() {
		return secondaryEntities;
	}
	public void setSecondaryEntities(Map<String, Collection<? extends GameEntity>> secondaryEntities) {
		this.secondaryEntities = secondaryEntities;
	}


}
