package rewardTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import entity.Bee;
import main.GamePanel;
import main.KeyHandler;
import reward.BonusReward;


public class BonusRewardTest {
    
    private BonusReward bonusReward;

    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        bonusReward = new BonusReward(gp);
    }

    // @Test
    // public void regRewardConstructorTest() {
    //     assertNotNull(regularReward, "Failed to create the regular reward");
    //     assertEquals(regularReward.getImage(), regRewardImg, "Failed to connect the object with the right texture");
    //     assertEquals(regularReward.getValue(), 10, "Failed to create an object with the correct value");
    //     assertEquals(regularReward.getMap(), panel, "Failed to create an object on the correct panel");
    //     //assertEquals(regularReward.getLocation(), point, "Failed to create an object in the correct cell location");
    // }

    @Test
    public void bonusRewardConstructorTest() throws IOException {
        //BufferedImage img = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
        assertNotNull(bonusReward);
        assertNotNull(bonusReward.getImage());
        int valueResult = bonusReward.getValue();
        assertEquals(50, valueResult);
        int widthResult = bonusReward.width;
        assertEquals(60, widthResult);
        int heightResult = bonusReward.height;
        assertEquals(60, heightResult);
    }

    @Test
    void testCollectReward() {
        GamePanel gp = bonusReward.getMap();
        KeyHandler kh = new KeyHandler(gp);
        Bee bee = new Bee(gp, kh);

        // assert the bee's reward list does not already contain this reward
        boolean inListInitially = bee.rewardList.contains(bonusReward);
        assertFalse(inListInitially); 
        int initial_score = bee.beeScore;
        
        bonusReward.collectReward(bee);

        // assert the bee's reward list contains this reward after calling collectReward(bee)
        boolean inListResult = bee.rewardList.contains(bonusReward);
        assertTrue(inListResult);
        // assert the bee's score increased by the value of a bonus reward
        int scoreResult = bee.beeScore;
        assertEquals(initial_score + bonusReward.value ,scoreResult); 
    }

    @Test
    void testRemove() {
        int x = 4 * bonusReward.getMap().tileSize;
        int y = 3 * bonusReward.getMap().tileSize;
        bonusReward.setLocation(x, y);

        bonusReward.remove();

        int xResult = bonusReward.worldX;
        int yResult = bonusReward.worldY;
        BufferedImage imgResult = bonusReward.getImage();
        // assert the reward is now at the unaccessible point (0,0)
        assertEquals(0, xResult);
        assertEquals(0, yResult);
        // assert the reward's image is now null
        assertNull(imgResult);
    }
}
