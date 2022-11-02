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

    public Entity(GamePanel gp){
        this.gamePanel = gp;
    }

    public int checkEntity(Entity entity, Entity target){
        int index = 999;
        //for(int i = 0; i < target.length; i++){
        entity.bounds.x = entity.worldX + entity.bounds.x;
        entity.bounds.y = entity.worldY + entity.bounds.y;
        target.bounds.x = target.worldX + target.bounds.x;
        target.bounds.y = target.worldY + target.bounds.y;
        if(direction == "up"){
            entity.bounds.y -= entity.speed;
            //System.out.println("test3");
		    //System.out.println(entity.bounds.intersects(target.bounds));
            if(entity.bounds.intersects(target.bounds)){
                index = 1;
                moveUp = false;
            }
            //break;
        }else if(direction == "down"){
            entity.bounds.y += entity.speed;
            if(entity.bounds.intersects(target.bounds)){
                index = 2;
                moveDown = false;
            }
            //break;
        }else if(direction == "left"){
            entity.bounds.x -= entity.speed;
            if(entity.bounds.intersects(target.bounds)){
                index = 3;
                moveLeft = false;
            }
            //break;
        }else if(direction == "right"){
            entity.bounds.x += entity.speed;
            if(entity.bounds.intersects(target.bounds)){
                index = 4;
                moveRight = false;
            }
            //break;
        }
        entity.bounds.x = getX();
        entity.bounds.y = getY();
        target.bounds.x = getX();
        target.bounds.y = getY();
    
        return index;

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

    public void enemyCheckCollision() {
		if (getTileNum() == 3) {
			System.out.println("Collision With Trap Tile");
            System.out.println("------");
		}

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
