package com.pacman.model.support;

import java.awt.Dimension;

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
}
