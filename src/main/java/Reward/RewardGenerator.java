package Reward;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;

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
    final int regRewardVal = 10;
    final int bonusRewardVal = 25;

    // Generating and adding the new rewards to the list
    public RewardGenerator(GamePanel gp) {
        this.map = gp;
    }

// Generate a regular reward at some  point 
// Generate a bonus reward at some random point
public void setRewards() {
	int randomX = 0;
	int randomY = 0;

        // Checking generated reward's location
        // add maxRegReward # of regular rewards to rewards list
        for (int i = 0; i < maxRegReward; i++) {
            map.rewards[i] = new RegularReward(map);
            
			randomX = getRandom(0, map.tileM.mapTileNum.length - 1);
			randomY = getRandom(0, map.tileM.mapTileNum[0].length - 1);
			// System.out.println("randomX: " + randomX + " randomY: " + randomY);
			// System.out.println("Len X: " + map.tileM.mapTileNum.length + " Len Y: " +
			// map.tileM.mapTileNum[0].length);

			// Avoiding the traps and walls and door
			// System.out.println("mapTileNum: " + map.tileM.mapTileNum[randomX][randomY]);
			if (map.tileM.mapTileNum[randomX][randomY] == 3 || map.tileM.mapTileNum[randomX][randomY] == 1
					|| map.tileM.mapTileNum[randomX][randomY] == 2) {
				// System.out.println("-------Trap or Wall or Door-------");
				i--;
				continue;
			}

            // avoid drawing on queenBee (bottom corner)
			if (randomX == map.tileM.mapTileNum.length - 1 && randomY == map.tileM.mapTileNum[0].length - 1) {
				// System.out.println("-------Queen Bee-------");
				i--;
				continue;
			}
            

			// System.out.println("-------Setting reward-------");
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


    // public int avoidWalls(int i) {
    //     if (i % 6 == 0) {
    //         i++;
    //         if (i == 25) {
    //             i -= 2;
    //         }
    //     }

    //     return i;
    // }
}