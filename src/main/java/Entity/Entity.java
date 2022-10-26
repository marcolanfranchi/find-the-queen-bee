package Entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;


abstract public class Entity {

    public int worldX, worldY;
    public int speed;
    public int width = 32;
    public int height = 32;
    	
    public int spriteCounter = 0;
    public int spriteNum = 1;
	
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle(worldX, worldY, this.width, this.height);
        return bounds;
    }

    public void checkWallCollision() {
    }



    




}
