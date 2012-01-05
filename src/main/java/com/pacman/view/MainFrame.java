package com.pacman.view;

import java.awt.Graphics2D;

import com.pacman.model.World;
import com.pacman.view.event.EventHandler;

/**
 * Tela principal do jogo
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends EventHandler implements Runnable {
	private boolean finish;
	private Thread animationLoop;
	private Graphics2D graphics;
	private World world;

	private void initialize() {
		graphics = (Graphics2D) getGraphics();
	//	world = WorldDao.getInstance().getByName("teste");
		world = new World(graphics);
		world.setBuildMode(true);
		setSize(world.getScenario().getDimension());
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void start() {
		initialize();
		animationLoop = new Thread(this);
		animationLoop.start();
	}

	@Override
	public void stop() {
	}

	@Override
	public void run() {
		while (!finish) {
			world.update();
			world.render();
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

/*	@Override
	protected void saveWorld() {
		// world.setName(value());
		WorldDao.getInstance().save(world);
	}*/
}