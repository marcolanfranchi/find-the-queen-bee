package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;
import java.awt.Graphics2D;



/** 
     * This Class represents an Entity in the game which is to be extended
     * by the Game's different types of characters.
     * 
     * @author Marco Lanfranchi
     */
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

    
    /**
     * Creates an instance of an Entity and sets it's GamePanel to the given GamePanel
     * @param gp a GamePanel which will contain this Bee.
     */
    public Entity(GamePanel gp){
        this.gamePanel = gp;
    }

    // abstract methods:

    /**
     * Abstact method for drawing an entity on the given Graphics2D object
     * @param g Graphics2D object used to contain all images and text for the UI.
     */
    public abstract void draw(Graphics2D g);

    /**
     * Abstract setImages method for setting an entities images.
     */
    public abstract void setImages();

    /**
     * Abstract update method called every frame per second updating an Entity.
     */
    public abstract void update();

    public void setX(int x) {
        this.worldX = x;
    }

    public void setY(int y) {
        this.worldY = y;
    }

    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }
    
    /**
     * Returns this Entity's X position on the map.
     * @return an int representing the number of pixels in the horizontal
     *          direction that this Entity is located.
     */
    public int getX() {
        return this.worldX;
    }

    /**
     * Returns this Entity's Y position on the map.
     * @return an int representing the number of pixels in the horizontal
     *          direction that this Entity is located.
     */
    public int getY() {
        return this.worldY;
    }
    
    /**
     * Returns the int representing the index used in TileManager
     * to represent different types of tiles. 
     * @return an int 0, 1, 2, or 3 which represents a type of Tile.
     */
    public int getTileNum() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    /**
     * Returns the int representing the type of Tile above this Entity.
     * @return an int 0, 1, 2, or 3 which represents a type of Tile.
     */
    public int tileNumUp() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = (this.getY() - speed) / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    } 
    
    /**
     * Returns the int representing the type of Tile under this Entity.
     * @return an int 0, 1, 2, or 3 which represents a type of Tile.
     */
    public int tileNumDown() {
        int posX = this.getX() / gamePanel.tileSize;
        int posY = (this.getY() + speed) / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    /**
     * Returns the int representing the type of Tile to the left of this Entity.
     * @return an int 0, 1, 2, or 3 which represents a type of Tile.
     */
    public int tileNumLeft() {
        int posX = (this.getX() - speed) / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    /**
     * Returns the int representing the type of Tile to the right of this Entity.
     * @return an int 0, 1, 2, or 3 which represents a type of Tile.
     */
    public int tileNumRight() {
        int posX = (this.getX() + speed) / gamePanel.tileSize;
        int posY = this.getY() / gamePanel.tileSize;
        return gamePanel.tileM.mapTileNum[posX][posY];
    }

    /**
     * Check the distance between the Bee and enemies, if they hit each other which means
     * the enemy reaches the Bee and the game is over.
     * @param entity    The Bee's current location
     * @param enemies   Enemies' current location
     */
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

    /**
     * For all 4 directions, checks if the tile 1 position away from this Entity
     * is a Wall Tile. If it is, sets the boolean move_DIRECTION value to False,
     * otherwise sets it as True.
     */
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

    

    /**
     * Returns true if the given x and y ints give a location of a tile number in
     * the top left room of the map
     * @param x an int representing a tile number's x position
     * @param y an int representing a tile number's y position
     * @return a boolean value indicating if x and y correspond to a tile number the 
     *          the top left room
     */
    public boolean inFirstRoom(int x, int y) {
		if ((x >= 0 && x <= (5)) &&
				(y >= 0 && y <= (5))) {
                return true;
            } else {
                return false;
            }
    }
}
