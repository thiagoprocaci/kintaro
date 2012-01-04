package com.pacman.system.event.core;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import com.pacman.model.enumeration.Direction;
import com.pacman.system.event.IEventEngine;

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
	// criar uma fila para comando gerais
	// criar uma fila de eventos do mouse


	// melhor ter um process keyboard event e um process mouse event
	@Override
	public void processEvent(int eventCode) {
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
			break;
		case KeyEvent.VK_P: // pause
			break;
		case KeyEvent.VK_S: // save
			break;
		case KeyEvent.VK_SHIFT:
			break;
		case KeyEvent.VK_ESCAPE:
			break;
		default:
			break;
		}

	}

	@Override
	public Queue<Direction> getDirectionEvents() {
		return directionEventQueue;
	}

}
