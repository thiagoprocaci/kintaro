package com.pacman.system.event;

import java.util.Queue;

import com.pacman.model.enumeration.Direction;
import com.pacman.system.event.enumeration.KeyboardCommand;
import com.pacman.system.event.support.MouseEvent;

/**
 * Modulo para processar eventos
 */
public interface IEventEngine {

	/**
	 * Metodo principal que coordena os eventos que foram disparados pelo teclado na aplicacao.
	 *
	 * @param eventCode codigo do evento
	 */
	void processKeyboardEvent(int eventCode);


	/**
	 * Metodo principal que coordena os eventos que foram disparados pelo mouse na aplicacao.
	 * @param eventCode codigo do evento
	 * @param xMousePosition coordenada x do mouse
	 * @param yMousePosition coordenada y do mouse
	 */
	void processMouseEvent(int eventCode, double xMousePosition, double yMousePosition);

	/**
	 * @return Retorna os eventos relacionados a direcao (cima, baixo, esquerda, direita)
	 */
	Queue<Direction> getDirectionEvents();

	/**
	 *
	 * @return Retorna os eventos relacionados ao mouse (botao direito, esquerdo e do meio)
	 */
	Queue<MouseEvent> getMouseEvents();

	/**
	 *
	 * @return Retorna os eventos gerais do teclado (enter, esc, shift ...)
	 */
	Queue<KeyboardCommand> getGeneralKeyboardEvents();



}
