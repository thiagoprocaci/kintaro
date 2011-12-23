package com.pacman.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.pacman.model.World;

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
		String path = getClass().getResource("/").toString();
		path = path.replaceAll("file:", "");
		path = path.replaceAll("bin", "files");
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
	 */
	public void save(World world) {
		try {
			String path = getPath() + world.getName();
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(world);
			out.close();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(world);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
