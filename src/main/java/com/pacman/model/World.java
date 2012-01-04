package com.pacman.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.pacman.model.enumeration.Direction;
import com.pacman.model.support.IWorld;
import com.pacman.system.event.core.EventEngine;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.IMovementEngine;
import com.pacman.system.physical.core.ColisionEngine;
import com.pacman.system.physical.core.MovementEngine;
import com.pacman.system.physical.core.PacManActionEngine;
import com.pacman.system.rendering.IEntityRenderingEngine;
import com.pacman.system.rendering.core.BlockRenderingEngine;
import com.pacman.system.rendering.core.FruitRenderingEngine;
import com.pacman.system.rendering.core.GhostRenderingEngine;
import com.pacman.system.rendering.core.PacManRenderingEngine;
import com.pacman.system.rendering.core.ScenarioRenderingEngine;

/**
 * Mundo
 */
public class World implements IWorld {

	private boolean buildMode = false;

	private Graphics graphics;

	// personagens do jogo manipuladas pelo mundo
	private Map<String, Block> map = null;
	private Map<String, Block> blocks = new HashMap<String, Block>();
	private Map<String, Fruit> fruits = new HashMap<String, Fruit>();
	private Map<String, Ghost> ghosts = new HashMap<String, Ghost>();
	private Scenario scenario = new Scenario();
	private PacMan pacMan = new PacMan();

	private List<IEntityActionEngine> entityActionEngineList = new ArrayList<IEntityActionEngine>();
	private List<IEntityRenderingEngine> entityRenderingEngineList = new ArrayList<IEntityRenderingEngine>();

	public World(Graphics graphics) {
		this.graphics = graphics;

		// TODO a injecao de dependencia nao deve ficar aqui
		IMovementEngine movementEngines = new MovementEngine();
		ColisionEngine colisionEngine = new ColisionEngine();
		PacManActionEngine pacManActionManager = new PacManActionEngine(pacMan, blocks.values(), fruits.values());
		pacManActionManager.setColisionEngine(colisionEngine);
		pacManActionManager.setMovementEngine(movementEngines);

		entityActionEngineList.add(pacManActionManager);

		// engine de renderizacao
		entityRenderingEngineList.add(new ScenarioRenderingEngine());
		entityRenderingEngineList.add(new BlockRenderingEngine());
		entityRenderingEngineList.add(new FruitRenderingEngine());
		entityRenderingEngineList.add(new GhostRenderingEngine());
		entityRenderingEngineList.add(new PacManRenderingEngine());

	}

	@Override
	public void update() {
		Queue<Direction> directionEventList = EventEngine.getInstance().getDirectionEvents();
		Direction direction = null;
		for (IEntityActionEngine entityActionEngine : entityActionEngineList) {
			if (directionEventList.isEmpty()) {
				entityActionEngine.act(null);
			} else {
				while (!directionEventList.isEmpty()) {
					direction = directionEventList.remove();
					entityActionEngine.act(direction);
				}
			}

		}

	}

	@Override
	public void render() {
		entityRenderingEngineList.get(0).paint(graphics, scenario);
		synchronized (blocks) {
			for (Block block : blocks.values())
				entityRenderingEngineList.get(1).paint(graphics, block);
		}
		synchronized (fruits) {
			for (Fruit fruit : fruits.values())
				entityRenderingEngineList.get(2).paint(graphics, fruit);
		}
		synchronized (ghosts) {
			for (Ghost ghost : ghosts.values()) {
				entityRenderingEngineList.get(3).paint(graphics, ghost);
			}
		}
		entityRenderingEngineList.get(4).paint(graphics, pacMan);
/*		synchronized (map) {
			for (Block block : map.values()) {
				graphics.setColor(Color.GREEN);
				graphics.drawRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());

			}
		}*/
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
