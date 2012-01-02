package com.pacman.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pacman.model.enumeration.Direction;
import com.pacman.model.support.IWorld;
import com.pacman.system.physical.IEntityActionManager;
import com.pacman.system.physical.IMovementManager;
import com.pacman.system.physical.core.ColisionManager;
import com.pacman.system.physical.core.MovementManager;
import com.pacman.system.physical.core.PacManActionManager;

/**
 * Mundo
 */
public class World  implements IWorld {

	private boolean buildMode = false;
	// a direcao devera ser pegada do buffer
	private Direction direction;



	private Graphics graphics;

	// personagens do jogo manipuladas pelo mundo
	private Map<String, Block> map = null;
	private Map<String, Block> blocks = new HashMap<String, Block>();
	private Map<String, Fruit> fruits = new HashMap<String, Fruit>();
	private Map<String, Ghost> ghosts = new HashMap<String, Ghost>();
	private Scenario scenario = new  Scenario();
	private PacMan pacMan = new PacMan();

	private List<IEntityActionManager> entityActionManagerList = new ArrayList<IEntityActionManager>();

	public World(Graphics graphics) {
		this.graphics = graphics;

		// TODO a injecao de dependencia nao deve ficar aqui
		IMovementManager movementManager = new MovementManager();
		ColisionManager colisionManager =  new ColisionManager();
		PacManActionManager pacManActionManager = new PacManActionManager(pacMan, blocks.values(), fruits.values());
		pacManActionManager.setColisionManager(colisionManager);
		pacManActionManager.setMovementManager(movementManager);

		entityActionManagerList.add(pacManActionManager);
	}

	@Override
	public void update() {
		for (IEntityActionManager entityActionManager : entityActionManagerList) {
			entityActionManager.act(direction);
		}
		direction = null;
	}


	@Override
	public void render() {
		scenario.paint(graphics);
		synchronized (blocks) {
			for (Block block : blocks.values())
				block.paint(graphics);
		}
		synchronized (fruits) {
			for (Fruit fruit : fruits.values())
				fruit.paint(graphics);
		}
		synchronized (ghosts) {
			for (Ghost ghost : ghosts.values()) {
				ghost.paint(graphics);
			}
		}
		pacMan.paint(graphics);
	}

	public PacMan getPacMan() {
		return pacMan;
	}

	public Collection<Block> getBlocks() {
		return blocks.values();
	}

	public Collection<Fruit> getFruits() {
		return fruits.values();
	}

	public Collection<Ghost> getGhots() {
		return ghosts.values();
	}

	public Scenario getScenario() {
		return scenario;
	}





// ----------------------------- isso deve ficar separado

	/**
	 * Metodo utilizado para a adicao ou remocao de blocos no cenario
	 *
	 * @param x
	 * @param y
	 */
	public void addOrRemoveBlock(int x, int y) {
		if (buildMode)
			synchronized (blocks) {
				for (Block b : map.values())
					if ((b.getX() <= x) && (b.getY() <= y) && (b.getX() + b.getWidth() >= x) && (b.getY() + b.getHeight() >= y)) {
						x = b.getX() + (b.getWidth() / 2);
						y = b.getY() + (b.getHeight() / 2);
						if (!fruits.containsKey(x + "," + y) && !ghosts.containsKey(b.getX() + "," + b.getY())) {
							if (blocks.containsKey(b.getX() + "," + b.getY()))
								blocks.remove(b.getX() + "," + b.getY());
							else
								blocks.put(b.getX() + "," + b.getY(), new Block(b.getX(), b.getY()));
						}
						break;
					}
			}
	}



	/**
	 * Metodo utilizado para a adicao ou remocao de monstros no cenario
	 *
	 * @param x
	 * @param y
	 */
	public void addOrRemoveGhost(int x, int y) {
		if (buildMode)
			synchronized (ghosts) {
				for (Block b : map.values())
					if ((b.getX() <= x) && (b.getY() <= y) && (b.getX() + b.getWidth() >= x) && (b.getY() + b.getHeight() >= y)) {
						x = b.getX() + (b.getWidth() / 2);
						y = b.getY() + (b.getHeight() / 2);
						if (!fruits.containsKey(x + "," + y) && !blocks.containsKey(b.getX() + "," + b.getY())) {
							if (ghosts.containsKey(b.getX() + "," + b.getY()))
								ghosts.remove(b.getX() + "," + b.getY());
							else
								ghosts.put(b.getX() + "," + b.getY(), new Ghost(b.getX(), b.getY()));
						}
						break;
					}
			}
	}

	/**
	 * Metodo utilizado para a adicao ou remocao de frutas simples no cenario
	 *
	 * @param x
	 * @param y
	 */
	public void addOrRemoveSimpleFruit(int x, int y) {
		addOrRemoveFruit(x, y, false);
	}

	/**
	 * Metodo utilizado para a adicao ou remocao de frutas especiais no cenario
	 *
	 * @param x
	 * @param y
	 */
	public void addOrRemoveSpecialFruit(int x, int y) {
		addOrRemoveFruit(x, y, true);
	}






	/**
	 * Ativa e desativa modo de construcao do cenario
	 *
	 * @param buildMode
	 */
	public void setBuildMode(boolean buildMode) {
		this.buildMode = buildMode;
		if (buildMode)
			initMap();
	}

// melhorar esquema de eventos

	public void moveUp() {
		direction = Direction.UP;
	}

	public void moveDown() {
		direction = Direction.DOWN;
	}

	public void moveLeft() {
		direction = Direction.LEFT;
	}

	public void moveRight() {
		direction = Direction.RIGHT;
	}

	/**
	 * Metodo utilizado para a adicao ou remocao de frutas no cenario
	 *
	 * @param x
	 * @param y
	 * @param special
	 */
	private void addOrRemoveFruit(int x, int y, boolean special) {
		if (buildMode)
			synchronized (fruits) {
				for (Block b : map.values())
					if ((b.getX() <= x) && (b.getY() <= y) && (b.getX() + b.getWidth() >= x) && (b.getY() + b.getHeight() >= y)) {
						if (!blocks.containsKey(b.getX() + "," + b.getY()) && !ghosts.containsKey(b.getX() + "," + b.getY())) {
							x = b.getX() + (b.getWidth() / 2);
							y = b.getY() + (b.getHeight() / 2);
							if (fruits.containsKey(x + "," + y))
								fruits.remove(x + "," + y);
							else
								fruits.put(x + "," + y, new Fruit(x, y, special));
						}
						break;
					}
			}
	}

	/**
	 * Divide o cenario em blocos para facilitar a construcao
	 */
	private void initMap() {
		map = new HashMap<String, Block>();
		int x = 0;
		int y = 0;
		while (x <= IWorld.MAX_X) {
			while (y <= IWorld.MAX_Y) {
				map.put(x + "," + y, new Block(x, y));
				y += 30;
			}
			x += 30;
			y = 0;
		}
	}



}
