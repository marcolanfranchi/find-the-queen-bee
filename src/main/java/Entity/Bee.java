package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

import Reward.Reward;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_TextBubble;

public class Bee extends Entity {

    KeyHandler keyHandler;
	public final int screenX;
	public final int screenY;
    public ArrayList<Reward> rewardList = new ArrayList<>();

    public int beeScore;
    public int punishment = 10;
    public int punishmentBuffer = 0;


    public Bee(GamePanel gp, KeyHandler kh) {
        super(gp);
        this.keyHandler = kh;
        beeScore = 30; // bee starts with 30 points
        bounds = new Rectangle();
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = this.width;
		bounds.height = this.height;
		this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        worldX = 1 * gp.tileSize;
        worldY = 1 * gp.tileSize;
		speed = gp.tileSize / 2;
        direction = "down";
        getBeeImage();
    }

    /**
     * 
     */
    public void getBeeImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @param g2
     */
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1; 
            } else image = up2;
        } else if (direction == "down") {
            if (spriteNum == 1) {
                image = down1; 
            } else image = down2;
        } else if (direction == "left") {
            if (spriteNum == 1) {
                image = left1; 
            } else image = left2;
        } else if (direction == "right") {
            if (spriteNum == 1) {
                image = right1; 
            } else image = right2;
        }
		g2.drawImage(image, screenX, screenY, width, height, null);
    }

    
    /**
     * 
     */
    public void update() {

        checkWallCollision();
        checkPunishmentCollision();
        
        for (int i = 0; i < gamePanel.rewards.length; i++) {
            if (gamePanel.rewards[i] != null) {
                pickUpReward(gamePanel.rewards[i]);
            }
        }

        // System.out.println("bee X: " + this.worldX);
        // System.out.println("bee Y: " + this.worldY);

        updateDirection();

        // counter used to switch bee images to flap wings over and over
        spriteCounter ++;
        if (spriteCounter > 2) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    

    /**
     * 
     */
    private void updateDirection() {
        if (keyHandler.upPressed && moveUp) {
			worldY -= speed;
            direction = "up";
        } else if (keyHandler.downPressed && moveDown) {
			worldY += speed;
            direction = "down";
        } else if (keyHandler.leftPressed && moveLeft) {
			worldX -= speed;
            direction = "left";
        } else if (keyHandler.rightPressed && moveRight) {
			worldX += speed;
            direction = "right";
        }
    }


    /**
     * 
     */
    public boolean checkReachedEnd() {
    
        int endTileX = gamePanel.objects[0].worldX;
        int endTileY = gamePanel.objects[0].worldY;


        if (this.worldX >= endTileX && this.worldY >= endTileY) {
            return true;
        } else {
            return false;
                        }
    }


    /**
     * 
     */
    public boolean checkDoneGameWon() {
        if (checkReachedEnd() && hasAllRewards()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 
     */
    public void pickUpReward(Reward reward) {
        if (onReward(reward)) {
            reward.collectReward(this);
            reward.remove();
        }
    }


    /**
     * Adds 
     * @param regReward
     */
    public void addReward(Reward reward) {
        rewardList.add(reward);
        beeScore += reward.value;
    }


    /**
     * 
     */
    public boolean onReward(Reward reward) {
        int rewardX = reward.worldX;
        int rewardY = reward.worldY;

        final boolean inTopLeft = this.worldX == rewardX && this.worldY == rewardY;  
        final boolean inTopRight = this.worldX - 24 == rewardX && this.worldY == rewardY;
        final boolean inBottomLeft = this.worldX == rewardX && this.worldY == rewardY + 24;
        final boolean inBottomRight = this.worldX - 24 == rewardX && this.worldY == rewardY + 24;              

        if (inTopLeft || inTopRight || inBottomLeft || inBottomRight) {
            return true;
        } else {
            return false;
                        }
    }


    /**
     * 
     * @return
     */
    private boolean hasAllRewards() {
        if (rewardList.size() == gamePanel.rewardGenerator.maxRegReward) {
            return true;
        } else {
            return false;
        }
    }

   /**
    * if Bee is two tiles away from Queen Bee and doesnt not have all rewards,
    * show alternate text bubble reminding to collect all rewards 
    */
    public boolean nearQueenMissingRewards() {
        if (this.worldX >= 20 * gamePanel.tileSize && 
            this.worldY >= 20 * gamePanel.tileSize) {
                if (rewardList.size() < 10) {
                    ((OBJ_TextBubble) gamePanel.objects[1]).setAltImage();
                    return true;
                }
            }
        return false;
    }

    // public boolean touchingEnemy(Enemy enemy) {
    //     int enemyX = enemy.worldX;
    //     int enemyY = enemy.worldY; 

    //     final boolean enemyOverTop = this.worldX == enemyX && this.worldY == enemyY;
    //     final boolean enemyLeft = this.worldX - 12 == enemyX && this.worldY == enemyY;  
    //     final boolean enemyRight = this.worldX + 12 == enemyX && this.worldY == enemyY;
    //     final boolean enemyAbove = this.worldX == enemyX && this.worldY + 12 == enemyY;
    //     final boolean enemyUnder = this.worldX == enemyX && this.worldY - 12 == enemyY;

    //     // if (this.worldX == enemyX && this.worldY == enemyY && this.worldX == en) {
    //     //     return true;
    //     // } else {
    //     //     return false;
    //     // }

    //     if (enemyOverTop || enemyLeft || enemyRight || enemyAbove || enemyUnder) {
    //         //System.out.println("touching enemy");
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    


    /**
     * 
     */
    public void checkPunishmentCollision() {
        punishmentBuffer++;
        if (getTileNum() == 3) {
			reduceScore();
		}
    }

    /**
     * 
     */
    public void reduceScore(){
        if (punishmentBuffer % 2 == 0) {
            beeScore -= punishment;
        }
    }
}
