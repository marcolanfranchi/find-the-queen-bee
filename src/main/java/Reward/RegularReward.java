package reward;
import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Bee;

import java.awt.*;

/**
* Sets Regular Reward's image
* @author Sana Dallalzadeh Atoufi
*/
public class RegularReward extends Reward {

    public static int regRewardVal = 10;

    public RegularReward(GamePanel gp) {
        super(gp);
        this.value = regRewardVal;
        try {
            this.image = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Draws regular rewards on the given Graphics2D
     * @param g2 Graphics2D object used 
     * @param g a Graphics object used to paint on all components in the GamePanel
     */
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
    /**
     * collects rewards and add it to the score
     * @param bee bee's location
     */
    public void collectReward(Bee bee) {
        bee.rewardList.add(this);
        bee.beeScore += this.value;
        
    }
}
