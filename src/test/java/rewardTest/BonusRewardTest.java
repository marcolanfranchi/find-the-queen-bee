package rewardTest;

import main.GamePanel;
import reward.RegularReward;
import reward.Reward;

import org.junit.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BonusRewardTest {
    int maxCordX = 25;
    int maxCordY = 15;

    Random r = new Random();

    int xCord = r.nextInt(maxCordX - 1);
    int yCord = r.nextInt(maxCordY - 1);

    private final GamePanel panel = new GamePanel();

   

    // @Test
    // public void bonusRewardConstructorTest() {
    //     Reward bonusReward = new RegularReward(panel);
    //     assertNotNull(bonusReward, "Failed to create the bonus reward object");
    //     assertEquals(bonusReward.getImage(), bonusReward, "Failed to connect the object with the right texture");
    //     assertEquals(bonusReward.getValue(), 25, "Failed to create an object with the correct value");
    //     assertEquals(bonusReward.getMap(), panel, "Failed to create an object on the correct panel");
    //     //assertEquals(bonusReward.getLocation(), point, "Failed to create an object in the correct cell location");
    // }
}

