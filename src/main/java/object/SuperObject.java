package object;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;

/**
 * This class is extended by all objects in the game. It contains the basic
 * variables and methods that all objects will need. Such as the name, x and y,
 * width and height, and the image.
 */
public class SuperObject {
	public BufferedImage image;
	public String name;
	public int worldX, worldY;
	public int width, height;


	/**
	 * This method is used to draw the image to the screen. The if statement is
	 * used to check if the image is visibile within the players view. If it is
	 * then it will draw the image to the screen. To help with processing time.
	 * 
	 * @param g2
	 * @param gp
	 */
	public void draw(Graphics2D g2, GamePanel gp) {

		int screenX = worldX - gp.bee.world.getX() + gp.bee.screen.getX();
		int screenY = worldY - gp.bee.world.getY() + gp.bee.screen.getY();

		if (worldX + gp.tileSize > gp.bee.world.getX() - gp.bee.screen.getX() &&
				worldX - gp.tileSize < gp.bee.world.getX() + gp.bee.screen.getX() &&
				worldY + gp.tileSize > gp.bee.world.getY() - gp.bee.screen.getY() &&
				worldY - gp.tileSize < gp.bee.world.getY() + gp.bee.screen.getY()) {
				g2.drawImage(image, screenX, screenY, width, height, null);
					}
	}
}
