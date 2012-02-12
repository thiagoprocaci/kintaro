package com.pacman.dao;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.pacman.model.Block;
import com.pacman.model.Fruit;
import com.pacman.model.Ghost;
import com.pacman.model.PacMan;
import com.pacman.model.Scenario;
import com.pacman.model.World;
import com.pacman.model.support.GameEntity;

/**
 * WorldDao
 * 
 * 
 * 
 */
public class WorldDao {
	private static final String VIRGULA = ", ";
	private static final String PAC_MAN = "pacMan";
	private static final String BLOCK = "block";
	private static final String SCENARIO = "scenario";
	private static final String FRUIT = "fruit";
	private static final String GHOST = "ghost";
	private static WorldDao INSTANCE;

	private WorldDao() {
	}

	private String getPath() {
		String path = getClass().getResource("/map/").toString();
		path = path.replaceAll("file:", "");
		return path;
	}

	public static WorldDao getInstance() {
		if (INSTANCE == null)
			INSTANCE = new WorldDao();
		return INSTANCE;
	}

	/**
	 * Salva mundo
	 * 
	 * @param world
	 * @throws IOException
	 */
	public void save(World world) throws IOException {
		StringBuffer fileContent = new StringBuffer();
		fileContent.append(getScenarioAsString(world.getScenario()));
		fileContent.append(getPacManAsString(world.getPacMan()));
		fileContent.append(getBlocksAsString(world.getBlocks()));
		fileContent.append(getFruitsAsString(world.getFruits()));
		fileContent.append(getGhostsAsString(world.getGhosts()));

		String path = getPath() + world.getScenario().getName() + ".txt";
		Writer out = new OutputStreamWriter(new FileOutputStream(path));
		try {
			out.write(fileContent.toString());
		} finally {
			out.close();
		}

	}

	private String getGhostsAsString(Collection<Ghost> ghosts) {
		StringBuffer buffer = new StringBuffer();
		if (ghosts != null) {
			for (Ghost ghost : ghosts) {
				buffer.append(getGameEntityAsString(ghost, GHOST, true));
			}
		}
		return buffer.toString();
	}

	private String getFruitsAsString(Collection<Fruit> fruits) {
		StringBuffer buffer = new StringBuffer();
		if (fruits != null) {
			for (Fruit fruit : fruits) {
				buffer.append(getGameEntityAsString(fruit, FRUIT, false));
				buffer.append(VIRGULA);
				buffer.append(fruit.isSpecial());
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	private String getScenarioAsString(Scenario scenario) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getGameEntityAsString(scenario, SCENARIO, false));
		buffer.append(VIRGULA);
		buffer.append(scenario.getName());
		buffer.append("\n");
		return buffer.toString();
	}

	private String getBlocksAsString(Collection<Block> blocks) {
		StringBuffer buffer = new StringBuffer();
		if (blocks != null) {
			for (Block block : blocks) {
				buffer.append(getGameEntityAsString(block, BLOCK, true));
			}
		}
		return buffer.toString();
	}

	private String getPacManAsString(PacMan pacMan) {
		return getGameEntityAsString(pacMan, PAC_MAN, true);
	}

	private String getGameEntityAsString(GameEntity gameEntity,
			String identifier, boolean newLine) {
		StringBuffer buffer = new StringBuffer();
		if (gameEntity != null) {
			buffer.append(identifier);
			buffer.append(VIRGULA);
			buffer.append(gameEntity.getX());
			buffer.append(VIRGULA);
			buffer.append(gameEntity.getY());
			buffer.append(VIRGULA);
			buffer.append(gameEntity.getSpeed());
			buffer.append(VIRGULA);
			buffer.append(gameEntity.getWidth());
			buffer.append(VIRGULA);
			buffer.append(gameEntity.getHeight());
			buffer.append(VIRGULA);
			buffer.append(gameEntity.isAlive());
			if (newLine) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	public World loadWord(Graphics graphics) {
		try {
			World world = new World(graphics);
			Scanner sc = new Scanner(new File(getPath() + "map.txt"));
			String line = null;
			Collection<Block> blocks = new ArrayList<Block>();
			Collection<Fruit> fruits = new ArrayList<Fruit>();
			Collection<Ghost> ghosts = new ArrayList<Ghost>();
			Scenario scenario = new Scenario();
			PacMan pacMan = new PacMan();

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line.startsWith(BLOCK)) {
					blocks.add(loadBlock(line));
				} else if (line.startsWith(FRUIT)) {
					fruits.add(loadFruit(line));
				} else if (line.startsWith(GHOST)) {
					ghosts.add(loadGhost(line));
				} else if (line.startsWith(SCENARIO)) {
					scenario = loadScenario(line);
				} else if (line.startsWith(PAC_MAN)) {
					pacMan = loadPacMan(line);
				}
			}
			world.setBlocks(blocks);
			world.setFruits(fruits);
			world.setGhosts(ghosts);
			world.setScenario(scenario);
			world.setPanMan(pacMan);
			return world;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PacMan loadPacMan(String line) {
		PacMan pacMan = new PacMan();
		populateGameEntity(pacMan, line);
		pacMan.setPreviousValidX(pacMan.getX());
		pacMan.setPreviousValidY(pacMan.getY());
		return pacMan;
	}

	private Scenario loadScenario(String line) {
		Scenario scenario = new Scenario();
		populateGameEntity(scenario, line);
		// TODO nao contempla o nome
		return scenario;
	}

	private Ghost loadGhost(String line) {
		Ghost ghost = new Ghost(0, 0);
		populateGameEntity(ghost, line);
		ghost.setPreviousValidX(ghost.getX());
		ghost.setPreviousValidY(ghost.getY());
		return ghost;
	}

	private Fruit loadFruit(String line) {
		Fruit fruit = new Fruit(0, 0, false);
		populateGameEntity(fruit, line);
		// TODO a questao da fruta especial nao esta contemplada
		return fruit;
	}

	private Block loadBlock(String line) {
		Block entity = new Block(0, 0);
		populateGameEntity(entity, line);
		return entity;
	}

	private void populateGameEntity(GameEntity entity, String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(VIRGULA);
		String[] data = new String[7];
		int i = 0;
		while (sc.hasNext()) {
			if (i < 7) {
				data[i] = sc.next();
				i++;
			} else {
				break;
			}

		}
		entity.setX(Integer.parseInt(data[1]));
		entity.setY(Integer.parseInt(data[2]));
		entity.setSpeed(Integer.parseInt(data[3]));
		entity.setWidth(Integer.parseInt(data[4]));
		entity.setHeight(Integer.parseInt(data[5]));
		entity.setAlive(Boolean.parseBoolean(data[6]));
	}

	public static void main(String[] args) {
		WorldDao.getInstance().loadWord(null);
	}
}
