package com.pacman.builder.core;

import java.awt.Graphics;

import com.pacman.builder.IWorldBuilder;
import com.pacman.dao.WorldDao;
import com.pacman.model.World;
import com.pacman.model.support.IWorld;

public class WorldBuilder implements IWorldBuilder {
	private static WorldBuilder INSTANCE = new WorldBuilder();

	private WorldBuilder() {

	}

	public static WorldBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public IWorld giveMeAWorld(Graphics graphics) {
		World world = WorldDao.getInstance().loadWord(graphics);
		world.setBuildMode(true);
		world.setEntityActionEngineList(EngineBuilder.getInstance().buildEntityActionEngine());
		world.setEntityRenderingEngineList(EngineBuilder.getInstance().buildEntityRenderingEngine());
		world.setEventEngine(EngineBuilder.getInstance().buildEventEngine());
		return world;
	}

}
