package com.pacman.model.enumeration;

public enum Direction {
	UP(0), DOWN(1), LEFT(2), RIGHT(3);

	private int value;

	private Direction(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}
}
