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
				// TODO criar constantes
				EventEngine.getInstance().processEvent(evt.getKeyCode());
				if (evt.getKeyCode() == 27) {
					onEscapePressed();
				} else if (evt.getKeyCode() == 38) {
					onUpPressed();
				} else if (evt.getKeyCode() == 40) {
					onDownPressed();
				} else if (evt.getKeyCode() == 37) {
					onLeftPressed();
				} else if (evt.getKeyCode() == 39) {
					onRightPressed();
				} else if (evt.getKeyCode() == 10) {
					onEnterPressed();
				} else if (evt.getKeyCode() == 80) {
					onPausePressed();
				} else if (evt.getKeyCode() == 83) {
					onSavePressed();
				} else if (evt.getKeyCode() == 16) {
					onShiftPressed();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getButton() == 1)
					onLeftMouseClick();
				else if (me.getButton() == 2)
					onMiddleMouseClick();
				else if (me.getButton() == 3)
					onRightMouseClick();
			}
		});
	}

	/**
	 * Acao realizada quando a tecla esc e pressionada
	 */
	protected abstract void onEscapePressed();

	/**
	 * Acao realizada quando a tecla up e pressionada
	 */
	protected abstract void onUpPressed();

	/**
	 * Acao realizada quando a tecla down e pressionada
	 */
	protected abstract void onDownPressed();

	/**
	 * Acao realizada quando a tecla left e pressionada
	 */
	protected abstract void onLeftPressed();

	/**
	 * Acao realizada quando a tecla right e pressionada
	 */
	protected abstract void onRightPressed();

	/**
	 * Acao realizada quando a tecla enter e pressionada
	 */
	protected abstract void onEnterPressed();

	/**
	 * Acao realizada quando a tecla P e pressionada
	 */
	protected abstract void onPausePressed();

	/**
	 * Acao realizada quando a tecla shift e pressionada
	 */
	protected abstract void onShiftPressed();

	/**
	 * Acao realizada quando a tecla S e pressionada
	 */
	protected abstract void onSavePressed();

	/**
	 * Acao realizada quando o botao esquerdo do mouse e pressionado
	 */
	protected abstract void onLeftMouseClick();

	/**
	 * Acao realizada quando o botao direito do mouse e pressionado
	 */
	protected abstract void onRightMouseClick();

	/**
	 * Acao realizada quando o botao do meio do mouse e pressionado
	 */
	protected abstract void onMiddleMouseClick();
}
