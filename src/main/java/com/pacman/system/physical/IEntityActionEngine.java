package com.pacman.system.physical;

import com.pacman.model.enumeration.Direction;

public interface IEntityActionEngine {


	// TODO passar dto para o entity action manager
	void act(Direction direction);

}
