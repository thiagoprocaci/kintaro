package com.pacman.model.support;

import java.awt.Dimension;
import java.util.Collection;

import com.pacman.model.Block;
import com.pacman.model.Fruit;
import com.pacman.model.Ghost;
import com.pacman.model.PacMan;
import com.pacman.model.Scenario;
import com.pacman.system.event.IEventEngine;


/**
 *
 * Interface que define qual entidade eh o mundo.
 *
 */
public interface IWorld {

	// limites do mundo
	public static final int MAX_X = 540;
	public static final int MAX_Y = 430;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;


	void render();

	void update();

	IEventEngine getEventEngine();

	Dimension getDimension();

	PacMan getPacMan();

	void setPanMan(PacMan pacMan);

	Scenario getScenario();

	void setScenario(Scenario scenario);

	Collection<Ghost> getGhosts();

	void setGhosts(Collection<Ghost> ghosts);

	Collection<Fruit> getFruits();

	void setFruits(Collection<Fruit> fruits);

	Collection<Block> getBlocks();

	void setBlocks(Collection<Block> blocks);

}
