package com.pacman.model.support;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *  Interface que define coisas pintaveis do jogos
 */
public interface IPaintable extends Serializable {

	/**
	 * Desenha personagens na tela
	 *
	 * @param graphics objeto responsavel pelo desenho
	 */
	void paint(Graphics graphics);

}
