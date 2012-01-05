package com.pacman.view.event;

import java.applet.Applet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.pacman.builder.core.WorldBuilder;
import com.pacman.model.World;
import com.pacman.model.support.IWorld;

/**
 * Classe responsavel por escutar os eventos disparados no frame
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public abstract class EventHandler extends Applet {

	private IWorld world;

	public EventHandler() {

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				// JOptionPane.showMessageDialog(null, "" + evt.getKeyCode());
				world.getEventEngine().processKeyboardEvent(evt.getKeyCode());
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				world.getEventEngine().processMouseEvent(me.getButton(), getMousePosition().getX(), (int) getMousePosition().getY());
			}
		});
	}

	public void initialize() {
		world = WorldBuilder.getInstance().giveMeAWorld(getGraphics());
		((World) world).setBuildMode(true);
		setSize(world.getDimension());
	}

	public IWorld getWorld() {
		return world;
	}

}
