package com.pacman.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;

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
				buffer.append(getGameEntityAsString(ghost, "ghost", true));
			}
		}
		return buffer.toString();
	}

	private String getFruitsAsString(Collection<Fruit> fruits) {
		StringBuffer buffer = new StringBuffer();
		if (fruits != null) {
			for (Fruit fruit : fruits) {
				buffer.append(getGameEntityAsString(fruit, "fruit", false));
				buffer.append(", ");
				buffer.append(fruit.isSpecial());
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	private String getScenarioAsString(Scenario scenario) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getGameEntityAsString(scenario, "scenario", false));
		buffer.append(", ");
		buffer.append(scenario.getName());
		buffer.append("\n");
		return buffer.toString();
	}

	private String getBlocksAsString(Collection<Block> blocks) {
		StringBuffer buffer = new StringBuffer();
		if (blocks != null) {
			for (Block block : blocks) {
				buffer.append(getGameEntityAsString(block, "block", true));
			}
		}
		return buffer.toString();
	}

	private String getPacManAsString(PacMan pacMan) {
		return getGameEntityAsString(pacMan, "pacMan", true);
	}

	private String getGameEntityAsString(GameEntity gameEntity, String identifier, boolean newLine) {
		StringBuffer buffer = new StringBuffer();
		if (gameEntity != null) {
			buffer.append(identifier);
			buffer.append(", ");
			buffer.append(gameEntity.getX());
			buffer.append(", ");
			buffer.append(gameEntity.getY());
			buffer.append(", ");
			buffer.append(gameEntity.getSpeed());
			buffer.append(", ");
			buffer.append(gameEntity.getWidth());
			buffer.append(", ");
			buffer.append(gameEntity.getHeight());
			buffer.append(", ");
			buffer.append(gameEntity.isAlive());
			if (newLine) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	/**
	 *
	 * @param name
	 * @return Retorna cenario atraves do nome
	 */
	public World getByName(String name) {
		try {
			File file = new File(getPath() + name);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (World) in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
