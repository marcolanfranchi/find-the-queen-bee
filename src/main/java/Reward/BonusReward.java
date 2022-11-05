package reward;
import main.GamePanel;

import java.io.IOException;

import javax.swing.Timer;

import entity.Bee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import java.awt.*;

public class BonusReward extends Reward {

    public static int bonusRewardVal = 50;
    public boolean displayNow;
    public int drawBuffer = 10;
	public Timer timer;

    public BonusReward(GamePanel gp) {
        super(gp);
        this.value = bonusRewardVal;
        this.displayNow = false;
        this.value = bonusRewardVal;
		switchVisibility();
		timer.start();

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
			if (this.collected == false && this.displayNow == true) {
				g2.drawImage(image, screenX, screenY, width, height, null);
			}
		}
    }

	public void switchVisibility() {
		timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayNow == true) {
					displayNow = false;
				} else {
					displayNow = true;
				}
			}
		});

	}


    public void collectReward(Bee bee) {
        bee.beeScore += this.value;
    }
}
