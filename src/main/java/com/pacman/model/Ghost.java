package com.pacman.model;

import java.awt.Graphics;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;

/**
 * Entidade que representa o mostro do jogo
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class Ghost extends GameEntity {
	private boolean scared;
	private boolean shift;

	public Ghost(int x, int y) {
		super(x, y, 5, 23, 23);
		setMoving(true);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!scared) {
			if (shift)
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_1.value()), getX(), getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_2.value()), getX(), getY(), null);
		} else {
			if (shift)
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_1.value()), getX(), getY(), null);
			else
				graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.GHOST_SCARED_2.value()), getX(), getY(), null);
		}
		shift = !shift;
		//TODO mover o mostro
	}

	public void setScared(boolean scared) {
		this.scared = scared;
	}

}
