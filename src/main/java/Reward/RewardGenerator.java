package reward;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a RewardGenerator which has a list
 * of Rewards, fields defining numbers of types of rewards, and a
 * gamepanel to display the rewards.
 *
 * @author Sana Dallalzadeh Atoufi
 * @author Marco Lanfranchi
 */
public class RewardGenerator {

	private static ArrayList<Reward> rewardsList = new ArrayList<>();

	public GamePanel gamePanel;

	public int maxRegReward = 10;
	public final int maxBonusReward = 3;
	public final int totalRewards = maxRegReward + maxBonusReward;
	final int maxCordX = 25;
	final int maxCordY = 15;
	final int regRewardVal = 10; // bee collects 10 points for collecting honey drops
	final int bonusRewardVal = 25; // bee collects 25 points for collecting honey pots

	/**
	 * Creates an instance of a RewardGenerator whos GamePanel is the given
	 * GamePanel
	 * 
	 * @param gp a GamePanel which will contain this RewardGenerator's reward list.
	 */
	public RewardGenerator(GamePanel gp) {
		this.gamePanel = gp;
	}

	/**
	 * Returns this RewardGenerator's list of rewards.
	 * 
	 * @return An ArrayList of Rewards.
	 */
	public static ArrayList<Reward> getRewardsList() {
		return rewardsList;
	}

	/**
	 * generating regular rewards at some point and bonus reward at some random
	 * point
	 * checking generated reward's location and add maxRegReward # of regular
	 * rewards to rewards list
	 * avoding walls,tile, and door and avoid drawing on queenBee (bottom corner)
	 */
	public void setRegRewards() {
		int randomX = 0;
		int randomY = 0;

		// add maxRegReward # of regular rewards to rewards list
		for (int i = 0; i < maxRegReward; i++) {
			gamePanel.rewards[i] = new RegularReward(gamePanel);

			randomX = getRandom(1, gamePanel.tileM.mapTileNum.length - 1);
			randomY = getRandom(1, gamePanel.tileM.mapTileNum[0].length - 1);

			// Avoiding door, walls, and punishment tiles
			if (gamePanel.tileM.mapTileNum[randomX][randomY] == 3 || gamePanel.tileM.mapTileNum[randomX][randomY] == 1
					|| gamePanel.tileM.mapTileNum[randomX][randomY] == 2) {
				i--;
				continue;
			}

			//avoid drawing on queenBee (bottom corner)
			if (randomX == gamePanel.tileM.mapTileNum.length - 1
					&& randomY == gamePanel.tileM.mapTileNum[0].length - 1) {
				i--;
				continue;
			}
			gamePanel.rewards[i].worldX = randomX * gamePanel.tileSize;
			gamePanel.rewards[i].worldY = randomY * gamePanel.tileSize;
		}
	}

	public void setBonusRewards() {
		int randomX = 0;
		int randomY = 0;

		// add maxBonusReward # of bonus rewards to rewards list
		for (int i = maxRegReward; i < maxBonusReward + maxRegReward; i++) {
			gamePanel.rewards[i] = new BonusReward(gamePanel);

			randomX = getRandom(0, gamePanel.tileM.mapTileNum.length - 1);
			randomY = getRandom(0, gamePanel.tileM.mapTileNum[0].length - 1);

			// Avoiding the door and walls
			if (gamePanel.tileM.mapTileNum[randomX][randomY] == 1
					|| gamePanel.tileM.mapTileNum[randomX][randomY] == 2) {
				i--;
				continue;
			}

			// // avoid drawing on queenBee (bottom corner)
			if (randomX == gamePanel.tileM.mapTileNum.length - 1
					&& randomY == gamePanel.tileM.mapTileNum[0].length - 1) {
				i--;
				continue;
			}
			gamePanel.rewards[i].worldX = randomX * gamePanel.tileSize;
			gamePanel.rewards[i].worldY = randomY * gamePanel.tileSize;
		}
	}

	/**
	 * Returns a Random int in the range of the given int min to the given int max
	 * 
	 * @param min an int and lower bound for the range of the Random int
	 * @param max an int and an upper-bound for the range of the Random int
	 * @return a randomly generated int greater than or equal to min and less than
	 *         or equal to max.
	 */
	public static int getRandom(int min, int max) {
		Random num = new Random();
		return num.nextInt(max - min + 1);
	}
}