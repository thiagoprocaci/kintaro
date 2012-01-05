package com.pacman.builder;

import java.util.List;

import com.pacman.system.event.IEventEngine;
import com.pacman.system.physical.ICollisionEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.rendering.IEntityRenderingEngine;

public interface IEngineBuilder {

	IMovementEngine buildMovementEngine();

	ICollisionEngine buildCollisionEngine();

	List<IEntityActionEngine> buildEntityActionEngine();

	List<IEntityRenderingEngine> buildEntityRenderingEngine();

	IEventEngine buildEventEngine();

}
