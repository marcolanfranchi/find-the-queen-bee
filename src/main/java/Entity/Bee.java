package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
//import Reward.RewardGenerator;

public class Bee extends Entity {

    //GamePanel gamePanel;
    KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;


    public Bee(GamePanel gp, KeyHandler kh) {
        super(gp);
        //this.gamePanel = gp;
        this.keyHandler = kh;
        bounds = new Rectangle();
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = this.width;
		bounds.height = this.height;
		this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // System.out.println("Test2");
		// System.out.println(bounds.x = getX());
		// System.out.println(bounds.y = getY());
		// System.out.println(bounds.width);
		// System.out.println(bounds.height);
        // default values
		//worldX = gp.tileSize * 2;
		//worldY = gp.tileSize * 2;
        worldX = 48;
        worldY = 48;
		speed = 48 / 2;
        direction = "down";
        getBeeImage();
    }

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

    public void update() {

        this.checkCollision();

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

        // To check if the Bee hits the enemy
        // int collisionNPC = checkEntity(this, gamePanel.enemy);
        // if(collisionNPC != 999){
        //     reduceScore();
        // }

        spriteCounter ++;
        if (spriteCounter > 2) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        //System.out.println(this.getBounds().toString());
        //System.out.println(this.getTileNum());
        //System.out.println(this.tileNumUp());
        //System.out.println(this.tileNumDown());
        //System.out.println(this.tileNumLeft());
        //System.out.println(this.tileNumRight());
    }

    //TODO: reduce score
    public void reduceScore(){
    //     BeeScoreNum -= Punishment
    }

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


}
