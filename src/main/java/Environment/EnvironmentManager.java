package environment;

import java.awt.Graphics2D;

import main.GamePanel;

/**
 * This class is used to create the environment for the game.
 * Using the Lighting class it instantiates the black circle around the player
 * 
 * @author Satvik Garg
 */
public class EnvironmentManager {
	GamePanel gp;

	Lighting lighting;

	/**
	 * Constructor for the EnvironmentManager class
	 * 
	 * @param gp The GamePanel object
	 */
	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Instantiates the Lighting class
	 * 
	 * @param g2d The Graphics2D object
	 */
	public void setup() {
		lighting = new Lighting(gp, 575);
	}

	/**
	 * Draws the lighting effect to the screen using the Lighting class
	 * 
	 * @param g2d The Graphics2D object
	 */
	public void draw(Graphics2D g2) {
		lighting.draw(g2);
	}
}
