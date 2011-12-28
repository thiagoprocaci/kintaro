package com.pacman.system.core;

import junit.framework.TestCase;

import com.pacman.model.support.GameEntity;
import com.pacman.model.support.IWorld;
import com.pacman.system.IMovement;

public class MovementTest extends TestCase {

	private IMovement movement;
	private GameEntity entity;


	public void setUp() {
		movement = new Movement();
		entity = new GameEntity(0, 0, 0);
	}

	public void testMoveUp() {
		entity.setX(2);
		entity.setY(2);
		entity.setSpeed(1);
		movement.moveUp(entity);
		assertEquals(1, entity.getY());
		assertEquals(2, entity.getX());
		assertEquals(1, entity.getSpeed());
	}

	public void testMoveDown() {
		entity.setX(2);
		entity.setY(2);
		entity.setSpeed(1);
		movement.moveDown(entity);
		assertEquals(3, entity.getY());
		assertEquals(2, entity.getX());
		assertEquals(1, entity.getSpeed());
	}

	public void testMoveLeft() {
		entity.setX(2);
		entity.setY(2);
		entity.setSpeed(1);
		movement.moveLeft(entity);
		assertEquals(2, entity.getY());
		assertEquals(1, entity.getX());
		assertEquals(1, entity.getSpeed());
	}

	public void testMoveRight() {
		entity.setX(2);
		entity.setY(2);
		entity.setSpeed(1);
		movement.moveRight(entity);
		assertEquals(2, entity.getY());
		assertEquals(3, entity.getX());
		assertEquals(1, entity.getSpeed());
	}

	public void testCanMoveUp(){
		// qualquer x menor que o min_x deve falhar
		entity.setX(IWorld.MIN_X - 1);
		assertFalse(movement.canMoveUp(entity));

		entity.setX(IWorld.MIN_X - 2);
		assertFalse(movement.canMoveUp(entity));

		// qualquer y - speed  < min_y deve falhar
		entity.setX(IWorld.MIN_X + 1);
		entity.setY(IWorld.MIN_Y);
		entity.setSpeed(1);
		assertFalse(movement.canMoveUp(entity));

		// x maior que min_x e y - speed > min_y deve passar
		entity.setX(IWorld.MIN_X + 1);
		entity.setY(IWorld.MIN_Y + 1);
		entity.setSpeed(0);
		assertTrue(movement.canMoveUp(entity));
	}

	public void testCanMoveDown() {
		// qualquer x maior que o max_x deve falhar
		entity.setX(IWorld.MAX_X + 1);
		assertFalse(movement.canMoveDown(entity));

		entity.setX(IWorld.MAX_X + 2);
		assertFalse(movement.canMoveDown(entity));

		// qualquer y + speed  > max_y deve falhar
		entity.setX(IWorld.MAX_X - 1);
		entity.setY(IWorld.MAX_Y);
		entity.setSpeed(1);
		assertFalse(movement.canMoveDown(entity));

		// qualquer x menor que o max_x e y + speed  < max_y deve passar
		entity.setX(IWorld.MAX_X - 1);
		entity.setY(IWorld.MAX_Y - 1);
		entity.setSpeed(0);
		assertTrue(movement.canMoveDown(entity));
	}

	public void testCanMoveLeft() {
		// x - speed < min_x deve falhar
		entity.setX(IWorld.MIN_X);
		entity.setSpeed(1);
		assertFalse(movement.canMoveLeft(entity));

		// y < min_y deve falhar
		entity.setX(IWorld.MIN_X + 1);
		entity.setSpeed(0);
		entity.setY(IWorld.MIN_Y - 1);
		assertFalse(movement.canMoveLeft(entity));

		// x - speed > min_x e y > min_y deve passar
		entity.setX(IWorld.MIN_X + 1);
		entity.setSpeed(0);
		entity.setY(IWorld.MIN_Y + 1);
		assertTrue(movement.canMoveLeft(entity));
	}

	public void testCanMoveRight() {
		// x + speed > max_x deve falhar
		entity.setX(IWorld.MAX_X);
		entity.setSpeed(1);
		assertFalse(movement.canMoveRight(entity));

		// y > max_y
		entity.setX(IWorld.MAX_X - 1);
		entity.setSpeed(0);
		entity.setY(IWorld.MAX_Y + 1);
		assertFalse(movement.canMoveRight(entity));

		entity.setX(IWorld.MAX_X - 1);
		entity.setSpeed(0);
		entity.setY(IWorld.MAX_Y - 1);
		assertTrue(movement.canMoveRight(entity));
	}




}
