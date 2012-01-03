package com.pacman.system.rendering;

import java.awt.Graphics;

import com.pacman.model.support.GameEntity;

/**
 *
 * Interface principal da engine de renderizacao.
 * Responsavel por renderizar todos os personagens do jogo.
 *
 */
public interface IEntityRenderingEngine {

	/**
	 * Renderizas personagens na tela
	 *
	 * @param graphics objeto responsavel pelo desenho
	 * @param entity entidade a ser renderizada
	 */
	void paint(Graphics graphics, GameEntity entity);

}
