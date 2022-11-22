package rewardTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import main.GamePanel;
import reward.RegularReward;
import reward.Reward;

public class RegularRewardTest {


    private final GamePanel panel = new GamePanel();
    private Object regRewardImg;


    @Test
    public void regRewardConstructorTest() {
        Reward regularReward = new RegularReward(panel);
        assertNotNull(regularReward, "Failed to create the regular reward");
        assertEquals(regularReward.getImage(), regRewardImg, "Failed to connect the object with the right texture");
        assertEquals(regularReward.getValue(), 10, "Failed to create an object with the correct value");
        assertEquals(regularReward.getMap(), panel, "Failed to create an object on the correct panel");
        //assertEquals(regularReward.getLocation(), point, "Failed to create an object in the correct cell location");
    }
}


