package com.pacman.image;

/**
 * Constantes de Imagens
 * @author Thiago Baesso Procaci
 *
 */
public enum ImageConstants {
	GHOST_1("/image/Ghost1.png"),
	GHOST_2("/image/Ghost2.png"),
	GHOST_SCARED_1("/image/GhostScared1.png"),
	GHOST_SCARED_2("/image/GhostScared2.png"),
	PAC_MAN_1("/image/PacMan1.png"),
	PAC_MAN_2_DOWN("/image/PacMan2down.png"),
	PAC_MAN_2_LEFT("/image/PacMan2left.png"),
	PAC_MAN_2_RIGHT("/image/PacMan2right.png"),
	PAC_MAN_2_UP("/image/PacMan2up.png"),
	PAC_MAN_3_DOWN("/image/PacMan3down.png"),
	PAC_MAN_3_LEFT("/image/PacMan3left.png"),
	PAC_MAN_3_RIGHT("/image/PacMan3right.png"),
	PAC_MAN_3_UP("/image/PacMan3up.png"),
	PAC_MAN_4_DOWN("/image/PacMan4down.png"),
	PAC_MAN_4_LEFT("/image/PacMan4left.png"),
	PAC_MAN_4_RIGHT("/image/PacMan4right.png"),
	SPACE_PAC("/image/Space_pac.jpg"),
	PAC_MAN_4_UP("/image/PacMan4up.png");

	private String value;

	private ImageConstants(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
