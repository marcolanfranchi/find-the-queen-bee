package reward;


import util.Point;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Bee;
import main.GamePanel;

/**
 * This class represents Rewards in the game which has to be
 * collected by the bee.
 * 
 * @author Sana Dallalzadeh Atoufi
 */

public abstract class Reward {

	private GamePanel map;
	public int value;
	protected BufferedImage image;
	public Point world;
	public boolean collected;
	public int width;
	public int height;
	public boolean displayNow = true;

	public Reward(GamePanel gp) {
		map = gp;
		width = 60;
		height = 60;
		world = new Point(0, 0);
	}

	/**
	 * @param g2        Graphics2D object used to
	 * @param gamePanel gamepanel access
	 */
	public abstract void draw(Graphics2D g2, GamePanel gamePanel);

	/**
	 * @param bee bee's location
	 */
	public abstract void collectReward(Bee bee);

	/**
	 * Creates a reward given the parameters (mutators)
	 * 
	 * @param value
	 * @param image
	 * @param map
	 */

	public void setMap(GamePanel map) {
		this.map = map;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return accessors
	 */

	public GamePanel getMap() {
		return map;
	}

	public int getValue() {
		return value;
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Set this Reward's image to null, and sets it's location to an
	 * unaccessible position on the map.
	 */
	public void remove() {
		world.setLocation(0, 0);
		this.image = null;
	}

	// method used for testing
	public void setLocation(int x, int y) {
		world.setLocation(x, y);
	}
}