package com.pacman.model;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.pacman.image.ImageConstants;
import com.pacman.image.ImageManager;
import com.pacman.model.support.GameEntity;
import com.pacman.model.support.IWorld;

/**
 * Cenario do jogo
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public class World extends GameEntity implements IWorld {
	private String name;
	private boolean buildMode = false;
	private Map<String, Block> map = null;
	private Map<String, Block> blocks = new HashMap<String, Block>();
	private Map<String, Fruit> fruits = new HashMap<String, Fruit>();
	private Map<String, Ghost> ghosts = new HashMap<String, Ghost>();
	private PacMan pacMan = new PacMan();

	public World() {
		super(0, 0, 0, 570, 450);
	}

	/**
	 * Desenha o mundo
	 */
	public void paint(Graphics graphics) {
		graphics.drawImage(ImageManager.getInstance().loadImage(ImageConstants.SPACE_PAC.value()), getX(), getY(), null);
		synchronized (blocks) {
			for (Block block : blocks.values())
				block.paint(graphics);
		}
		synchronized (fruits) {
			for (Fruit fruit : fruits.values())
				fruit.paint(graphics);
		}
		synchronized (ghosts) {
			for (Ghost ghost : ghosts.values()){
				ghost.paint(graphics);
			}
		}
		pacMan.paint(graphics);
	}

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
	 *
	 * @return Retorna pacMan
	 */
	@Override
	public PacMan getPacMan() {
		return pacMan;
	}


	@Override
	public Collection<Block> getBlocks() {
		if(blocks != null) {
			return blocks.values();
		}
		return null;
	}

	@Override
	public Collection<Fruit> getFruits() {
		if(fruits != null) {
			return fruits.values();
		}
		return null;
	}

	@Override
	public Collection<Ghost> getGhots() {
		if(ghosts != null) {
			return ghosts.values();
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public void moveUp(IWorld world) {
		pacMan.moveUp(this);
	}

	@Override
	public void moveDown(IWorld world) {
		pacMan.moveDown(this);
	}

	@Override
	public void moveLeft(IWorld world) {
		pacMan.moveLeft(this);
	}

	@Override
	public void moveRight(IWorld world) {
		pacMan.moveRight(this);
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
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

}
