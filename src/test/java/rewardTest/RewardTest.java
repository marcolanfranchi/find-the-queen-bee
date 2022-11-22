package rewardTest;

import main.GamePanel;
import main.KeyHandler;

import org.junit.jupiter.api.Test;

import Reward.Reward;
import Reward.RewardGenerator;

import org.junit.jupiter.api.BeforeEach;


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
}