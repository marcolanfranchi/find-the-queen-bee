package object;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;


public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int width, height;


	public void draw(Graphics2D g2, GamePanel gp) {

		int screenX = worldX - gp.bee.worldX + gp.bee.screenX;
		int screenY = worldY - gp.bee.worldY + gp.bee.screenY;

		if (worldX + gp.tileSize > gp.bee.worldX - gp.bee.screenX &&
					worldX - gp.tileSize < gp.bee.worldX + gp.bee.screenX &&
					worldY + gp.tileSize > gp.bee.worldY - gp.bee.screenY &&
					worldY - gp.tileSize < gp.bee.worldY + gp.bee.screenY) {
				g2.drawImage(image, screenX, screenY, width, height, null);
					}
	}
}
