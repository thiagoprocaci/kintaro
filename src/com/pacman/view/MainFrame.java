package com.pacman.view;

import java.awt.Graphics2D;

import com.pacman.dao.WorldDao;
import com.pacman.model.World;

/**
 * Tela principal do jogo
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends DialogFrame implements Runnable {
	private boolean finish;
	private boolean shift;
	private Thread animationLoop;
	private Graphics2D graphics;
	private World world;

	private void initialize() {
		graphics = (Graphics2D) getGraphics();
	//	world = WorldDao.getInstance().getByName("teste");
		world = new World();
		world.setBuildMode(true);
		setSize(world.getDimension());
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
			world.paint(graphics);
			try {
				Thread.sleep(170);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDownPressed() {
		world.moveDownPacMan();
	}

	@Override
	protected void onLeftPressed() {
		world.moveLeftPacMan();
	}

	@Override
	protected void onRightPressed() {
		world.moveRightPacMan();
	}

	@Override
	protected void onUpPressed() {
		world.moveUpPacMan();
	}

	@Override
	protected void onEnterPressed() {
		// FIXME acao para enter
	}

	@Override
	protected void onEscapePressed() {
		// FIXME acao para esc
	}

	@Override
	protected void onPausePressed() {
		// FIXME acao para pause
	}

	@Override
	protected void onShiftPressed() {
		shift = !shift;
	}

	@Override
	protected void onLeftMouseClick() {
		if (!shift)
			world.addOrRemoveBlock((int) getMousePosition().getX(), (int) getMousePosition().getY());
		else
			world.addOrRemoveGhost((int) getMousePosition().getX(), (int) getMousePosition().getY());
	}

	@Override
	protected void onRightMouseClick() {
		world.addOrRemoveSimpleFruit((int) getMousePosition().getX(), (int) getMousePosition().getY());
	}

	@Override
	protected void onMiddleMouseClick() {
		world.addOrRemoveSpecialFruit((int) getMousePosition().getX(), (int) getMousePosition().getY());
	}

	@Override
	protected void saveWorld() {
		world.setName(value());
		WorldDao.getInstance().save(world);
	}
}