package com.pacman.system.event.core;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import com.pacman.model.enumeration.Direction;
import com.pacman.system.event.IEventEngine;
import com.pacman.system.event.enumeration.KeyboardCommand;
import com.pacman.system.event.enumeration.MouseButton;
import com.pacman.system.event.support.MouseEvent;

/**
 * Modulo para processar eventos
 */
public class EventEngine implements IEventEngine {
	// TODO nao sera singleton
	private static EventEngine EVENT_ENGINE = new EventEngine();


	private EventEngine(){

	}

	public static EventEngine getInstance(){
		return EVENT_ENGINE;
	}

	private Queue<Direction> directionEventQueue = new LinkedList<Direction>();
	private Queue<com.pacman.system.event.support.MouseEvent> mouseEventQueue = new LinkedList<com.pacman.system.event.support.MouseEvent>();
	private Queue<KeyboardCommand> keyboardCommandEvent = new LinkedList<KeyboardCommand>();


	@Override
	public void processKeyboardEvent(int eventCode) {
		switch (eventCode) {
		case KeyEvent.VK_UP:
			directionEventQueue.add(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			directionEventQueue.add(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			directionEventQueue.add(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			directionEventQueue.add(Direction.RIGHT);
			break;
		case KeyEvent.VK_ENTER:
			keyboardCommandEvent.add(KeyboardCommand.ENTER);
			break;
		case KeyEvent.VK_P: // pause
			keyboardCommandEvent.add(KeyboardCommand.PAUSE);
			break;
		case KeyEvent.VK_S: // save
			keyboardCommandEvent.add(KeyboardCommand.SAVE);
			break;
		case KeyEvent.VK_SHIFT:
			keyboardCommandEvent.add(KeyboardCommand.SHIFT);
			break;
		case KeyEvent.VK_ESCAPE:
			keyboardCommandEvent.add(KeyboardCommand.ESC);
			break;
		default:
			break;
		}

	}

	@Override
	public void processMouseEvent(int eventCode, double xMousePosition, double yMousePosition) {
		switch (eventCode) {
		case java.awt.event.MouseEvent.BUTTON1:
			// left
			mouseEventQueue.add(new MouseEvent(MouseButton.LEFT, (int) xMousePosition, (int) yMousePosition));
			break;
		case java.awt.event.MouseEvent.BUTTON2:
			// middle
			mouseEventQueue.add(new MouseEvent(MouseButton.MIDDLE, (int) xMousePosition, (int) yMousePosition));
			break;
		case java.awt.event.MouseEvent.BUTTON3:
			// right
			mouseEventQueue.add(new MouseEvent(MouseButton.RIGHT, (int) xMousePosition, (int) yMousePosition));
			break;
		default:
			break;
		}

	}

	@Override
	public Queue<Direction> getDirectionEvents() {
		return directionEventQueue;
	}

	@Override
	public Queue<MouseEvent> getMouseEvents() {
		return mouseEventQueue;
	}

	@Override
	public Queue<KeyboardCommand> getGeneralKeyboardEvents() {
		return keyboardCommandEvent;
	}

}
