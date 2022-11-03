package Entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;



abstract public class Entity {

    GamePanel gamePanel;

    public int worldX;
    public int worldY;
    public int speed;
    public int width = 32;
    public int height = 32;
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, enemyUp, enemyDown, enemyLeft, enemyRight;
    public String direction;

    public Rectangle bounds;
    public boolean moveUp, moveDown, moveLeft, moveRight;

    public boolean onPath = false;
    public boolean enemyCollision;

    public Entity(GamePanel gp){
        this.gamePanel = gp;
    }

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

    // 
    public double checkGameOver(Entity entity, Entity[] enemies){
        double index = 999;
        for(int i = 0; i < enemies.length; i++){
            index = Math.sqrt(Math.pow((entity.worldX - enemies[i].worldX), 2) + Math.pow((entity.worldY - enemies[i].worldY), 2));
            if(index <= 34){
                break;
            }
        }
        return index;
    }



    public void checkWallCollision() {
        if (this.tileNumUp() == 1) {
            moveUp = false;
        } else {
            moveUp = true;
        }

        if (this.tileNumDown() == 1) {
            moveDown = false;
        } else {
            moveDown = true;
        }

        if (this.tileNumLeft() == 1) {
            moveLeft = false;
        } else {
            moveLeft = true;
        }

        if (this.tileNumRight() == 1) {
            moveRight = false;
        } else {
            moveRight = true;
        }
	}

    public void enemyCheckCollision() {

        if (this.tileNumUp() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = "up";
        }

        if (this.tileNumDown() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = "down";
        }

        if (this.tileNumLeft() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = "left";
        }

        if (this.tileNumRight() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = "right";
        }
	}
}
