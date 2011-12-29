package com.pacman.system;

import com.pacman.model.support.GameEntity;


/**
*
* Entidade que representa um o movimento geral de qualquer personagem do jogo.
*
*/
public interface IMovementManager {

	/**
	 * Move para cima
	 * @param entity personagem do jogo
	 */
	void moveUp(GameEntity entity);

	/**
	 * Move para baixo
	 * @param entity personagem do jogo
	 */
	void moveDown(GameEntity entity);

	/**
	 * Move para esquerda
	 * @param entity personagem do jogo
	 */
	void moveLeft(GameEntity entity);

	/**
	 * Move para direita
	 * @param entity personagem do jogo
	 */
	void moveRight(GameEntity entity);

	/**
	 * Verifica movimento para cima
	 * @param entity personagem do jogo
	 * @return true caso o personagem possa realizar o movimento.
	 */
	boolean canMoveUp(GameEntity entity);

	/**
	 * Verifica movimento para baixo
	 * @param entity personagem do jogo
	 * @return true caso o personagem possa realizar o movimento.
	 */
	boolean canMoveDown(GameEntity entity);

	/**
	 * Verifica movimento para esquerda
	 * @param entity personagem do jogo
	 * @return true caso o personagem possa realizar o movimento.
	 */
	boolean canMoveLeft(GameEntity entity);

	/**
	 * Verifica movimento para direita
	 * @param entity personagem do jogo
	 * @return true caso o personagem possa realizar o movimento.
	 */
	boolean canMoveRight(GameEntity entity);
}
