package com.pacman.image;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Gerenciador de imagens
 *
 *
 */
public class ImageManager {
	private static ImageManager INSTANCE = new ImageManager();
	private Map<String, Image> buffer = new HashMap<String, Image>();

	private ImageManager() {
	}

	public static ImageManager getInstance() {
		return INSTANCE;
	}

	public Image loadImage(String path) {
		if (!buffer.containsKey(path)) {
			// cria buffer para evitar idas ao disco..
			Image image = new ImageIcon(getClass().getResource(path)).getImage();
			buffer.put(path, image);
			return image;
		}
		return buffer.get(path);
	}
}
