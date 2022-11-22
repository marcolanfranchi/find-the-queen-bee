package rewardTest;

import main.GamePanel;
import main.KeyHandler;

import org.junit.jupiter.api.Test;

import reward.Reward;
import reward.RewardGenerator;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class RewardGeneratorTest {

    GamePanel map = new GamePanel();

    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
    }

    @Test
    void generateAllRewards()
    {
        assertEquals(12,RewardGenerator.getRewardsList().size(), "Failed to create all rewards by the generator");
    }

    @Test
    void generateRegularReward()
    {
        // Test generateRegularReward
    
        RewardGenerator rewardGenerator = new RewardGenerator(map);
        assertNotNull(rewardGenerator.getRewardsList(), "Failed to create a regular reward by the generator");
    }

    @Test
    void generateBonusReward()
    {
        // Test generateBonusReward
        RewardGenerator rewardGenerator = new RewardGenerator(map);
        assertNotNull(rewardGenerator.getRewardsList(), "Failed to create a bonus reward by the generator");
    }

    @Test
    void rewardOnRoad()
    {
    
        RewardGenerator rewardGenerator = new RewardGenerator(map);
        ArrayList<Reward> rewardsList = rewardGenerator.getRewardsList();
        boolean check = true;
        for(Reward e:rewardsList)
        {
            //if(!reward.gameObjects[(int) e.getMap().getY()][(int) e.getMap().getX()].getClass().getSimpleName().equals("Road"))
            {
                check = false;
            }
        }
        assertTrue(check, "Some rewards have not been generated on the road");
    }

    @Test
    void rewardInTheSamePosition()
    {

        RewardGenerator rewardGenerator = new RewardGenerator(map);
        ArrayList<Reward> rewardsList = rewardGenerator.getRewardsList();
        boolean check = true;
        for(Reward e:rewardsList)
        {
            for(Reward e2:rewardsList)
                if (e != e2 & e.getMap().equals(e2.getMap())) {
                    check = false;
                    break;
                }
        }
        assertTrue(check, "Some rewards have been generated in the same location");
    }
}
