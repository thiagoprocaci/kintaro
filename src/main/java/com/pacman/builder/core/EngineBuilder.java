package com.pacman.builder.core;

import java.util.ArrayList;
import java.util.List;

import com.pacman.builder.IEngineBuilder;
import com.pacman.system.event.IEventEngine;
import com.pacman.system.event.core.EventEngine;
import com.pacman.system.physical.ICollisionEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.physical.core.CollisionEngine;
import com.pacman.system.physical.core.GhostActionEngine;
import com.pacman.system.physical.core.MovementEngine;
import com.pacman.system.physical.core.PacManActionEngine;
import com.pacman.system.rendering.IEntityRenderingEngine;
import com.pacman.system.rendering.core.BlockRenderingEngine;
import com.pacman.system.rendering.core.FruitRenderingEngine;
import com.pacman.system.rendering.core.GhostRenderingEngine;
import com.pacman.system.rendering.core.PacManRenderingEngine;
import com.pacman.system.rendering.core.ScenarioRenderingEngine;

public class EngineBuilder implements IEngineBuilder {
	private static EngineBuilder INSTANCE = new EngineBuilder();

	// fisica
	private IMovementEngine movementEngine;
	private ICollisionEngine collisionEngine;
	private PacManActionEngine pacManActionEngine;
	private GhostActionEngine ghostActionEngine;
	private List<IEntityActionEngine>  actionEngineList = new ArrayList<IEntityActionEngine>();

	// renderizacao
	private BlockRenderingEngine blockRenderingEngine;
	private FruitRenderingEngine fruitRenderingEngine;
	private GhostRenderingEngine ghostRenderingEngine;
	private PacManRenderingEngine pacManRenderingEngine;
	private ScenarioRenderingEngine scenarioRenderingEngine;
	List<IEntityRenderingEngine> renderingEngineList = new ArrayList<IEntityRenderingEngine>();

	private EngineBuilder() {
		// engine fisica
		movementEngine = new MovementEngine();
		collisionEngine = new CollisionEngine();
		pacManActionEngine = new PacManActionEngine();
		pacManActionEngine.setColisionEngine(collisionEngine);
		pacManActionEngine.setMovementEngine(movementEngine);

		ghostActionEngine = new GhostActionEngine();
		ghostActionEngine.setColisionEngine(collisionEngine);
		ghostActionEngine.setMovementEngine(movementEngine);

		actionEngineList.add(pacManActionEngine);
		actionEngineList.add(ghostActionEngine);

		// engine de renderizacao
		scenarioRenderingEngine = new ScenarioRenderingEngine();
		blockRenderingEngine = new BlockRenderingEngine();
		fruitRenderingEngine = new FruitRenderingEngine();
		ghostRenderingEngine = new GhostRenderingEngine();
		pacManRenderingEngine = new PacManRenderingEngine();

		renderingEngineList.add(scenarioRenderingEngine);
		renderingEngineList.add(blockRenderingEngine);
		renderingEngineList.add(fruitRenderingEngine);
		renderingEngineList.add(ghostRenderingEngine);
		renderingEngineList.add(pacManRenderingEngine);
	}

	public static EngineBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public IMovementEngine buildMovementEngine() {
		return movementEngine;
	}

	@Override
	public ICollisionEngine buildCollisionEngine() {
		return collisionEngine;
	}

	@Override
	public List<IEntityActionEngine> buildEntityActionEngine() {
		return actionEngineList;
	}

	@Override
	public List<IEntityRenderingEngine> buildEntityRenderingEngine() {
		return renderingEngineList;
	}

	@Override
	public IEventEngine buildEventEngine() {
		// esse cara eh prototype pq eh stateful
		return new EventEngine();
	}

}
