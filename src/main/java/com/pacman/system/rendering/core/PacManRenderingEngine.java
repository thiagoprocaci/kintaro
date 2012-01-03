package com.pacman.system.rendering.core;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.PacMan;
import com.pacman.model.enumeration.Direction;
import com.pacman.model.enumeration.MouthState;
import com.pacman.model.support.GameEntity;
import com.pacman.system.rendering.IEntityRenderingEngine;

public class PacManRenderingEngine implements IEntityRenderingEngine {

	@Override
	public void paint(Graphics graphics, GameEntity entity) {
		PacMan pacMan = (PacMan) entity;
		if (pacMan.getCurrentDirection() == Direction.UP && pacMan.getCurrentMouthState() == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.UP && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_UP.value()), pacMan.getX(), pacMan.getY()	, null);
		} else if (pacMan.getCurrentDirection() == Direction.UP && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_UP.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.DOWN && pacMan.getCurrentMouthState() == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.DOWN && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_DOWN.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.DOWN && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_DOWN.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.LEFT && pacMan.getCurrentMouthState() == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.LEFT && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_LEFT.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.LEFT && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_LEFT.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.RIGHT && pacMan.getCurrentMouthState() == MouthState.MOUTH_CLOSED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_1.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.RIGHT && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENING) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_2_RIGHT.value()), pacMan.getX(), pacMan.getY(), null);
		} else if (pacMan.getCurrentDirection() == Direction.RIGHT && pacMan.getCurrentMouthState() == MouthState.MOUTH_OPENED) {
			graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.PAC_MAN_3_RIGHT.value()), pacMan.getX(), pacMan.getY(), null);
		}

	}

}
