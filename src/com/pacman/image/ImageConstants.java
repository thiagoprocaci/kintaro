package com.pacman.image;

/**
 * Constantes de Imagens
 * @author Thiago Baesso Procaci
 *
 */
public enum ImageConstants {
	GHOST_1("/com/pacman/image/resource/Ghost1.png"),
	GHOST_2("/com/pacman/image/resource/Ghost2.png"),
	GHOST_SCARED_1("/com/pacman/image/resource/GhostScared1.png"),
	GHOST_SCARED_2("/com/pacman/image/resource/GhostScared2.png"),
	PAC_MAN_1("/com/pacman/image/resource/PacMan1.png"),
	PAC_MAN_2_DOWN("/com/pacman/image/resource/PacMan2down.png"),
	PAC_MAN_2_LEFT("/com/pacman/image/resource/PacMan2left.png"),
	PAC_MAN_2_RIGHT("/com/pacman/image/resource/PacMan2right.png"),
	PAC_MAN_2_UP("/com/pacman/image/resource/PacMan2up.png"),
	PAC_MAN_3_DOWN("/com/pacman/image/resource/PacMan3down.png"),
	PAC_MAN_3_LEFT("/com/pacman/image/resource/PacMan3left.png"),
	PAC_MAN_3_RIGHT("/com/pacman/image/resource/PacMan3right.png"),
	PAC_MAN_3_UP("/com/pacman/image/resource/PacMan3up.png"),
	PAC_MAN_4_DOWN("/com/pacman/image/resource/PacMan4down.png"),
	PAC_MAN_4_LEFT("/com/pacman/image/resource/PacMan4left.png"),
	PAC_MAN_4_RIGHT("/com/pacman/image/resource/PacMan4right.png"),
	SPACE_PAC("/com/pacman/image/resource/Space_pac.jpg"),
	PAC_MAN_4_UP("/com/pacman/image/resource/PacMan4up.png");	
	
	private String value;

	private ImageConstants(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
