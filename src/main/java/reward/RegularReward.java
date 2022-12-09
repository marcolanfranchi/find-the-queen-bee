package reward;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Bee;
import main.GamePanel;

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
            this.image = ImageIO.read(getClass().getResource("/ui/images/HoneyDrop.png"));
        } catch (IOException e) {
        }
    }
    /**
     * Draws regular rewards on the given Graphics2D
     * @param g2 Graphics2D object used 
     * @param g a Graphics object used to paint on all components in the GamePanel
     */
    public void draw(Graphics2D g2, GamePanel g) {
		int screenX = world.getX() - g.bee.world.getX() + g.bee.screen.getX();
		int screenY = world.getY() - g.bee.world.getY() + g.bee.screen.getY();

		if (world.getX() + g.tileSize > g.bee.world.getX() - g.bee.screen.getX() &&
				world.getX() - g.tileSize < g.bee.world.getX() + g.bee.screen.getX() &&
				world.getY() + g.tileSize > g.bee.world.getY() - g.bee.screen.getY() &&
				world.getY() - g.tileSize < g.bee.world.getY() + g.bee.screen.getY()) {
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
