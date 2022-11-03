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
    final int maxCordX = 25;
    final int maxCordY = 15;
    final int regRewardVal = 10;
    final int bonusRewardVal = 25;

    // Generating and adding the new rewards to the list
    public RewardGenerator(GamePanel gp) {
        this.map = gp;
    }

    
public void setRewards() {
        // add maxRegReward # of regular rewards to rewards list
        for (int i = 0; i < maxRegReward; i++) {
            map.rewards[i] = new RegularReward(map);
            
            int randomX = getRandom(1, 23);
            int randomY = getRandom(1, 23);

            randomX = avoidWalls(randomX); 
            randomY = avoidWalls(randomY);

            
            for (int j = 0; j < 3; j++) {
                if (map.tileM.mapTileNum[randomX][randomY] == 3) {
                    randomX +=2;
                }
            }


            // avoid drawing on queenBee (bottom corner)
            if (randomX == 23 && randomY == 23) {
                randomX--;
            }            
            

            map.rewards[i].worldX = randomX * map.tileSize;
            map.rewards[i].worldY = randomY * map.tileSize;
        }


        // add maxBonusReward # of regular rewards to rewards list
        for (int i = maxRegReward; i < maxRegReward + maxBonusReward; i++) {
            map.rewards[i] = new BonusReward(map);

            int randomX = getRandom(1, 23);
            int randomY = getRandom(1, 23);
            randomX = avoidWalls(randomX); 
            randomY = avoidWalls(randomY);
            
            for (int j = 0; j < 3; j++) {
                if (map.tileM.mapTileNum[randomX][randomY] == 3) {
                    randomX +=2;
                }
            }

            // avoid drawing on queenBee (bottom corner)
            if (randomX == 23 && randomY == 23) {
                randomX--;
            }            

            map.rewards[i].worldX = randomX * map.tileSize;
            map.rewards[i].worldY = randomY * map.tileSize;
        }
    }

    public static int getRandom(int min, int max) {
        Random num = new Random();
        return num.nextInt(max - min + 1);
    }


    public int avoidWalls(int i) {
        if (i % 6 == 0) {
            i++;
            if (i == 25) {
                i -= 2;
            }
        }

        return i;
    }
}