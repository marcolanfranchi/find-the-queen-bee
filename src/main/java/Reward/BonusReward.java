package Reward;
import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Bee;

import java.awt.*;

public class BonusReward extends Reward {

    public static int bonusRewardVal = 100;
    public boolean displayNow;

    public BonusReward(GamePanel gp) {
        super(gp);
        this.value = bonusRewardVal;
        this.collected = false;
        this.displayNow = false;
        this.value = bonusRewardVal;
        try {
            this.image = ImageIO.read(getClass().getResource("../ui/images/HoneyPot.png"));
        } catch (IOException e) {
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

    // public void update() {
    //     checkCollected();
    // }

    public void collectReward(Bee bee) {
        bee.beeScore += this.value;
    }
}
