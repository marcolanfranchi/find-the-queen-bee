package Reward;
import main.GamePanel;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;


public class RegularReward extends Reward {

    public static int regRewardVal = 10;

    public RegularReward(GamePanel gp) {
        super(gp);
        this.value = regRewardVal;
        this.collected = false;
        try {
            this.image = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel g) {
        int screenX = worldX - g.bee.worldX + g.bee.screenX;
        int screenY = worldY - g.bee.worldY + g.bee.screenY;

        if (worldX + g.tileSize > g.bee.worldX - g.bee.screenX &&
                    worldX - g.tileSize < g.bee.worldX + g.bee.screenX &&
                    worldY + g.tileSize > g.bee.worldY - g.bee.screenY &&
                    worldY - g.tileSize < g.bee.worldY + g.bee.screenY) {
                        if (this.collected == false) {
                            g2.drawImage(image, screenX, screenY, width, height, null);
                        }
                    }

    }

    public void update() {
        checkCollected();
    }
}
