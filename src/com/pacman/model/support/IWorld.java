package com.pacman.model.support;

import java.util.Collection;

import com.pacman.model.Block;
import com.pacman.model.Fruit;
import com.pacman.model.Ghost;
import com.pacman.model.PacMan;

/**
 *
 * Interface que define qual entidade eh o mundo. Atraves da interface eh
 * possivel ter acesso a tudo do mundo.
 *
 */
public interface IWorld {

	PacMan getPacMan();

	Collection<Block> getBlocks();

	Collection<Fruit> getFruits();

	Collection<Ghost> getGhots();
}
