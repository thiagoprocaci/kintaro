package com.pacman.system.physical;

import java.util.Collection;

import com.pacman.model.support.GameEntity;

/**
 *
 * Gerenciador de colisoes
 *
 */
public interface IColisionEngine {

	/**
	 * Verifica se houve colisao entre dois personagens do jogo.
	 * @param entity personagem
	 * @param entity2 personagem
	 * @return true se houver colisao
	 *
	 */
	boolean detectColision(GameEntity entity, GameEntity entity2);

	/**
	 * Verifica se houve colisao entre um personagem com uma colecao de
	 * personagens
	 *
	 * @param collection colecao de personagens
	 * @param entity personagem
	 * @return true se houver colisao
	 *
	 */
	boolean detectColision(GameEntity entity, Collection<? extends GameEntity> collection);

}
