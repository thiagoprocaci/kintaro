package com.pacman.model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.pacman.dao.WorldDao;
import com.pacman.model.enumeration.Direction;
import com.pacman.model.support.GameEntity;
import com.pacman.model.support.IWorld;
import com.pacman.system.event.IEventEngine;
import com.pacman.system.event.enumeration.KeyboardCommand;
import com.pacman.system.event.support.MouseEvent;
import com.pacman.system.physical.IEntityActionEngine;
import com.pacman.system.physical.core.GhostActionEngine;
import com.pacman.system.physical.core.PacManActionEngine;
import com.pacman.system.physical.support.ActionDto;
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
	private boolean shift = false;

	private Graphics graphics;

	// personagens do jogo manipuladas pelo mundo
	private Map<String, Block> map = null;
	private Map<String, Block> blocks = new HashMap<String, Block>();
	private Map<String, Fruit> fruits = new HashMap<String, Fruit>();
	private Map<String, Ghost> ghosts = new HashMap<String, Ghost>();
	private Scenario scenario = new Scenario();
	private PacMan pacMan = new PacMan();

	// injecao
	private List<IEntityActionEngine> entityActionEngineList;
	private List<IEntityRenderingEngine> entityRenderingEngineList;
	private IEventEngine eventEngine;

	// variaveis auxiliares
	private IEntityRenderingEngine blockRenderingEngine;
	private IEntityRenderingEngine fruitRenderingEngine;
	private IEntityRenderingEngine ghostRenderingEngine;
	private IEntityRenderingEngine pacManRenderingEngine;
	private IEntityRenderingEngine scenarioRenderingEngine;

	private IEntityActionEngine pacManActionEngine;
	private IEntityActionEngine ghostActionEngine;

	private void initActionEngine() {
		for (IEntityActionEngine engine : entityActionEngineList) {
			if (engine instanceof PacManActionEngine) {
				pacManActionEngine = engine;
			} else if (engine instanceof GhostActionEngine) {
				ghostActionEngine = engine;
			}
		}
	}

	private void initRenderingEngine() {
		for (IEntityRenderingEngine engine : entityRenderingEngineList) {
			if (engine instanceof BlockRenderingEngine) {
				blockRenderingEngine = engine;
			} else if (engine instanceof FruitRenderingEngine) {
				fruitRenderingEngine = engine;
			} else if (engine instanceof GhostRenderingEngine) {
				ghostRenderingEngine = engine;
			} else if (engine instanceof PacManRenderingEngine) {
				pacManRenderingEngine = engine;
			} else if (engine instanceof ScenarioRenderingEngine) {
				scenarioRenderingEngine = engine;
			}
		}
	}

	private void processPacManAction() {
		Queue<Direction> directionEventList = eventEngine.getDirectionEvents();
		ActionDto actionDto = new ActionDto();
		actionDto.setMainEntity(pacMan);
		Map<String, Collection<? extends GameEntity>> secondaryEntities = new HashMap<String, Collection<? extends GameEntity>>();
		secondaryEntities.put("block", blocks.values());
		secondaryEntities.put("fruit", fruits.values());
		actionDto.setSecondaryEntities(secondaryEntities);
		if (directionEventList.isEmpty()) {
			pacManActionEngine.act(actionDto);
		} else {
			while (!directionEventList.isEmpty()) {
				actionDto.setDirection(directionEventList.remove());
				pacManActionEngine.act(actionDto);
			}
		}
	}

	private void processGhostAction() {
		ActionDto actionDto = new ActionDto();
		actionDto.setTargetEntity(pacMan);
		Map<String, Collection<? extends GameEntity>> secondaryEntities = new HashMap<String, Collection<? extends GameEntity>>();
		secondaryEntities.put("block", blocks.values());
		actionDto.setSecondaryEntities(secondaryEntities);
		for (Ghost ghost : ghosts.values()) {
			actionDto.setMainEntity(ghost);
			ghostActionEngine.act(actionDto);
		}
	}

	private void processGeneralKeyboardEvents() {
		Queue<KeyboardCommand> keyboardCommandList = eventEngine.getGeneralKeyboardEvents();
		KeyboardCommand keyboardCommand = null;
		while (!keyboardCommandList.isEmpty()) {
			keyboardCommand = keyboardCommandList.remove();
			if (keyboardCommand == KeyboardCommand.SHIFT) {
				shift = !shift;
			} else if(keyboardCommand == KeyboardCommand.SAVE) {
				scenario.setName("space");
				try {
					WorldDao.getInstance().save(this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void processMouseEvents() {
		Queue<MouseEvent> mouseEventList = eventEngine.getMouseEvents();
		MouseEvent mouseEvent = null;
		while (!mouseEventList.isEmpty()) {
			mouseEvent = mouseEventList.remove();
			switch (mouseEvent.getPressedButton()) {
			case LEFT:
				if (!shift)
					addOrRemoveBlock(mouseEvent.getX(), mouseEvent.getY());
				else
					addOrRemoveGhost(mouseEvent.getX(), mouseEvent.getY());
				break;
			case RIGHT:
				addOrRemoveSimpleFruit(mouseEvent.getX(), mouseEvent.getY());
				break;
			case MIDDLE:
				addOrRemoveSpecialFruit(mouseEvent.getX(), mouseEvent.getY());
				break;
			default:
				break;
			}
		}
	}

	public World(Graphics graphics) {
		this.graphics = graphics;
	}

	public void setEntityActionEngineList(List<IEntityActionEngine> entityActionEngineList) {
		this.entityActionEngineList = entityActionEngineList;
		initActionEngine();
	}

	public void setEntityRenderingEngineList(List<IEntityRenderingEngine> entityRenderingEngineList) {
		this.entityRenderingEngineList = entityRenderingEngineList;
		initRenderingEngine();
	}

	public void setEventEngine(IEventEngine eventEngine) {
		this.eventEngine = eventEngine;
	}

	@Override
	public IEventEngine getEventEngine() {
		return eventEngine;
	}

	@Override
	public Dimension getDimension() {
		return scenario.getDimension();
	}

	@Override
	public void update() {
		processPacManAction();
		processGhostAction();
		processGeneralKeyboardEvents();
		processMouseEvents();
	}

	@Override
	public void render() {
		scenarioRenderingEngine.paint(graphics, scenario);
		synchronized (blocks) {
			for (Block block : blocks.values())
				blockRenderingEngine.paint(graphics, block);
		}
		synchronized (fruits) {
			for (Fruit fruit : fruits.values())
				fruitRenderingEngine.paint(graphics, fruit);
		}
		synchronized (ghosts) {
			for (Ghost ghost : ghosts.values()) {
				ghostRenderingEngine.paint(graphics, ghost);
			}
		}
		pacManRenderingEngine.paint(graphics, pacMan);
	}

	// ----------------------------- isso deve ficar separado (vai morrer?)

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
								System.out.println("x : " + b.getX() + " y : " + b.getY() + " width : " + b.getWidth() + " height : " + b.getHeight());
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

	@Override
	public PacMan getPacMan() {
		return pacMan;
	}

	@Override
	public void setPanMan(PacMan pacMan) {
		this.pacMan = pacMan;
	}

	@Override
	public Scenario getScenario() {
		return scenario;
	}

	@Override
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;

	}

	@Override
	public Collection<Ghost> getGhosts() {
		return ghosts.values();
	}

	@Override
	public void setGhosts(Collection<Ghost> ghosts) {
		if(ghosts != null) {
			for (Ghost ghost : ghosts) {
				this.ghosts.put(ghost.getX() + "," + ghost.getY(), ghost);
			}
		}
	}

	@Override
	public Collection<Fruit> getFruits() {
		return fruits.values();
	}

	@Override
	public void setFruits(Collection<Fruit> fruits) {
		if(fruits != null){
			for (Fruit fruit : fruits) {
				this.fruits.put(fruit.getX() + "," + fruit.getY(), fruit);
			}
		}
	}

	@Override
	public Collection<Block> getBlocks() {
		return blocks.values();
	}

	@Override
	public void setBlocks(Collection<Block> blocks) {
		if(blocks != null) {
			for (Block block : blocks) {
				this.blocks.put(block.getX() + "," + block.getY(), block);
			}
		}
	}

}
