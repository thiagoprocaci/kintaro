package com.pacman.view;

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
			getWorld().update();
			getWorld().render();
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