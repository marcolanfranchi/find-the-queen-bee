package Entity;

import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Bee extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Bee(GamePanel gp, KeyHandler kh) {

        this.gamePanel = gp;
        this.keyHandler = kh;

        // default values
        x = 100;
        y = 100;
        speed = 4;
    }


    public void update() {
        if (keyHandler.upPressed == true) {
            y -= speed;
        } else if (keyHandler.downPressed == true) {
            y += speed;
        } else if (keyHandler.leftPressed == true) {
            x -= speed;
        } else if (keyHandler.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {

        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);


    }
    
}
