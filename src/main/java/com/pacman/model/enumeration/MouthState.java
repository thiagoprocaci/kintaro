package com.pacman.model.enumeration;

public enum MouthState {
	MOUTH_CLOSED(0),
	MOUTH_OPENING(1),
	MOUTH_OPENED(2);

	private int value;

	private MouthState(int value) {
		this.value = value;
	}

	public int value(){
		return value;
	}
}
