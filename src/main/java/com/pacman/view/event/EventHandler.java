package com.pacman.view.event;

import java.applet.Applet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.pacman.system.event.core.EventEngine;

/**
 * Classe responsavel por escutar os eventos disparados no frame
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public abstract class EventHandler extends Applet {
	public EventHandler() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				//JOptionPane.showMessageDialog(null, "" + evt.getKeyCode());
				EventEngine.getInstance().processKeyboardEvent(evt.getKeyCode());
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				EventEngine.getInstance().processMouseEvent(me.getButton(), getMousePosition().getX(), (int) getMousePosition().getY());
			}
		});
	}

}
