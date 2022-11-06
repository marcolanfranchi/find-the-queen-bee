package reward;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;


/**
* Creates RewardGenerator in the given GamePanel
* and gets the appropriate image.
* Generates and adds the new rewards to the list
* @param gp a GamePanel which will contain Reward
* @return new list of rewards
* @author Sana Dallalzadeh Atoufi
*/


public class RewardGenerator {


    private static ArrayList<Reward> rewardsList = new ArrayList<>();

    public static ArrayList<Reward> getRewardsList() {
        return rewardsList;
    }

    GamePanel map;

    public int maxRegReward = 10;
    public final int maxBonusReward = 2;
    public final int totalRewards = maxRegReward + maxBonusReward;
    final int maxCordX = 25;
    final int maxCordY = 15;
    final int regRewardVal = 10; //bee collects 10 points for collecting honey drops
    final int bonusRewardVal = 25; //bee collects 25 points for collecting honey pots



    
    public RewardGenerator(GamePanel gp) {
        this.map = gp;
    }


    /**
     * generating regular rewards at some point and bonus reward at some random point
     * checking generated reward's location and add maxRegReward # of regular rewards to rewards list
     * avoding walls,tile, and door and avoid drawing on queenBee (bottom corner)
     */



    public void setRewards() {
	int randomX = 0;
	int randomY = 0;

        for (int i = 0; i < maxRegReward; i++) {
            map.rewards[i] = new RegularReward(map);
            
			randomX = getRandom(0, map.tileM.mapTileNum.length - 1);
			randomY = getRandom(0, map.tileM.mapTileNum[0].length - 1);

			if (map.tileM.mapTileNum[randomX][randomY] == 3 || map.tileM.mapTileNum[randomX][randomY] == 1
					|| map.tileM.mapTileNum[randomX][randomY] == 2) {
				i--;
				continue;
			}

			if (randomX == map.tileM.mapTileNum.length - 1 && randomY == map.tileM.mapTileNum[0].length - 1) {
				i--;
				continue;
			}
            map.rewards[i].worldX = randomX * map.tileSize;
            map.rewards[i].worldY = randomY * map.tileSize;
        }
    }

    public void setBonusRewards() {
		int randomX = 0;
		int randomY = 0;

        // add maxBonusReward # of regular rewards to rewards list
        for (int i = maxRegReward; i < maxBonusReward + maxRegReward; i++) {
            map.rewards[i] = new BonusReward(map);

			randomX = getRandom(0, map.tileM.mapTileNum.length - 1);
			randomY = getRandom(0, map.tileM.mapTileNum[0].length - 1);

			// Avoiding the door and walls
			if (map.tileM.mapTileNum[randomX][randomY] == 1
					|| map.tileM.mapTileNum[randomX][randomY] == 2) {
				i--;
				continue;
			}


            // avoid drawing on queenBee (bottom corner)
			if (randomX == map.tileM.mapTileNum.length - 1 && randomY == map.tileM.mapTileNum[0].length - 1) {
				i--;
				continue;
			}

            map.rewards[i].worldX = randomX * map.tileSize;
            map.rewards[i].worldY = randomY * map.tileSize;
        }
    }

    public static int getRandom(int min, int max) {
        Random num = new Random();
        return num.nextInt(max - min + 1);
    }
}