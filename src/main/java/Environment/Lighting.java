package environment;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

import main.GamePanel;

/**
 * This class is used to create the lighting effect for the game. It creates a
 * black circle around the player that is used to create the illusion of
 * darkness
 * in the game.
 * 
 * @author Satvik Garg
 */
public class Lighting {
	GamePanel gp;
	BufferedImage darknessFilter;

	/**
	 * Constructor for the Lighting class
	 * 
	 * @param gp     The GamePanel object
	 * @param radius The radius of the circle
	 */
	public Lighting(GamePanel gp, int circleSize) {

		// Create a buffered image
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

		// Get the center x and y of the light circle
		int centerX = gp.bee.screenX + (gp.tileSize) / 2;
		int centerY = gp.bee.screenY + (gp.tileSize) / 2;

		// Create a gradation effect
		Color color[] = new Color[12];
		float fraction[] = new float[12];

		// Create the color array
		initColors(color);

		// Create the fraction array
		initFractions(fraction);

		// Create a gradation paint settings
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize / 2), fraction, color);

		// Set the gradient data on g2
		g2.setPaint(gPaint);

		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.dispose();
	}

	/**
	 * This method is used to create the color array for the gradation effect
	 * 
	 * @param color The color array
	 */
	private void initColors(Color[] color) {
		color[0] = new Color(0, 0, 0, 0.1f);
		color[1] = new Color(0, 0, 0, 0.42f);
		color[2] = new Color(0, 0, 0, 0.52f);
		color[3] = new Color(0, 0, 0, 0.61f);
		color[4] = new Color(0, 0, 0, 0.69f);
		color[5] = new Color(0, 0, 0, 0.76f);
		color[6] = new Color(0, 0, 0, 0.82f);
		color[7] = new Color(0, 0, 0, 0.87f);
		color[8] = new Color(0, 0, 0, 0.91f);
		color[9] = new Color(0, 0, 0, 0.94f);
		color[10] = new Color(0, 0, 0, 0.96f);
		color[11] = new Color(0, 0, 0, 0.98f);
	}

	/**
	 * This method is used to create the fraction array for the gradation effect
	 * 
	 * @param fraction The fraction array
	 */
	private void initFractions(float[] fraction) {
		fraction[0] = 0f;
		fraction[1] = 0.4f;
		fraction[2] = 0.5f;
		fraction[3] = 0.6f;
		fraction[4] = 0.65f;
		fraction[5] = 0.7f;
		fraction[6] = 0.75f;
		fraction[7] = 0.8f;
		fraction[8] = 0.85f;
		fraction[9] = 0.9f;
		fraction[10] = 0.95f;
		fraction[11] = 1f;
	}

	/**
	 * This method is used to draw the lighting effect to the screen
	 * 
	 * @param g2d The Graphics2D object
	 */
	public void draw(Graphics2D g2) {
		g2.drawImage(darknessFilter, 0, 0, null);
	}
}
