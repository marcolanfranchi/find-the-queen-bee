package rewardTest;

import main.GamePanel;

import org.junit.jupiter.api.Test;

import reward.BonusReward;
import reward.RegularReward;
import reward.Reward;
import reward.RewardGenerator;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class RewardGeneratorTest {

    private RewardGenerator rewardGenerator;

    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        rewardGenerator = new RewardGenerator(gp);
    }

    @Test
    void testSetRegRewards() {
        rewardGenerator.setRegRewards();
        RegularReward r = new RegularReward(null);
        for (int i = 0; i < rewardGenerator.maxRegReward; i++) {
            Reward result = rewardGenerator.gamePanel.rewards[i];
            assertInstanceOf(r.getClass(), result);    
        }
    }

    @Test
    void testSetBonusRewards() {
        rewardGenerator.setBonusRewards();
        BonusReward b = new BonusReward(null);
        for (int i = rewardGenerator.maxRegReward; i < rewardGenerator.totalRewards; i++) {
            Reward result = rewardGenerator.gamePanel.rewards[i];
            assertInstanceOf(b.getClass(), result);
        }
    }

    @Test
    void rewardOnRoad()
    {
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
