package com.pacman.system.event;

import java.util.Queue;

import com.pacman.model.enumeration.Direction;

/**
 * Modulo para processar eventos
 */
public interface IEventEngine {

	/**
	 * Metodo principal que coordena os eventos que foram disparados na aplicacao.
	 *
	 * @param eventCode codigo do evento
	 */
	void processEvent(int eventCode);

	/**
	 * @return Retorna os eventos relacionados a direcao (cima, baixo, esquerda, direita)
	 */
	Queue<Direction> getDirectionEvents();

}
