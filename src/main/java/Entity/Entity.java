package Entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;



abstract public class Entity {

    GamePanel gamePanel;

    public int worldX, worldY;
    public int speed;
    public int width = 32;
    public int height = 32;
    	
    public int spriteCounter = 0;
    public int spriteNum = 1;
	
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public Rectangle bounds;
    public boolean moveUp, moveDown, moveLeft, moveRight;

    public Rectangle getBounds() {
        bounds = new Rectangle(getX(), getY(), this.width, this.height);
        return bounds;
    }

    public int getX() {
        return this.worldX;
    }

    public int getY() {
        return this.worldY;
    }
    
    public int getTileNum() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    public int tileNumUp() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = (this.getY() - speed) / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    } 
    
    public int tileNumDown() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = (this.getY() + speed) / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    public int tileNumLeft() {
        int posX = (this.getX() - speed) / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    public int tileNumRight() {
        int posX = (this.getX() + speed) / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    public void checkCollision() {
		if (getTileNum() == 3) {
			System.out.println("Collision With Trap Tile");
            System.out.println("------");
		}

        if (this.tileNumUp() == 1) {
            moveUp = false;
        } else {
            moveUp = true;
        }

        if (this.tileNumDown() == 1) {
            moveDown = false;
        } else {
            moveDown= true;
        }

        if (this.tileNumLeft() == 1) {
            moveLeft = false;
        } else {
            moveLeft= true;
        }

        if (this.tileNumRight() == 1) {
            moveRight = false;
        } else {
            moveRight= true;
        }
	}
}
