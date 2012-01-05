package com.pacman.builder;

import java.awt.Graphics;

import com.pacman.model.support.IWorld;

public interface IWorldBuilder {

	IWorld giveMeAWorld(Graphics graphics);

}
