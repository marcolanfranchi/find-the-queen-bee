package reward;

import main.GamePanel;

import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;

import entity.Bee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import java.awt.*;

// Randomly appears!!
public class BonusReward extends Reward {

	public static int bonusRewardVal = 50;
	public boolean displayNow;
	private Timer timer;

	private int minimal = 1000;
	private int max = 4000;
	private int randomTimeOutput;
	Random randomTime;

	public BonusReward(GamePanel gp) {
		super(gp);
		this.value = bonusRewardVal;
		this.displayNow = false;
		this.value = bonusRewardVal;

		randomTime = new Random();
		randomTimeOutput = (minimal + randomTime.nextInt(max - minimal + 1));
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
		Random rand = new Random();
		int randomNum = rand.nextInt((10000 - 2000) + 1) + 20000;

		timer = new Timer(randomNum, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (displayNow == true) {
					displayNow = false;
				} else {
					displayNow = true;
				}

				updateTimer();
			}
		});

	}

	private void updateTimer() {
		timer.stop();
		randomTimeOutput = (minimal + randomTime.nextInt(max - minimal + 1));
		timer.setDelay(randomTimeOutput);
		timer.restart();
	}

	public void collectReward(Bee bee) {
		bee.beeScore += this.value;
	}
}
