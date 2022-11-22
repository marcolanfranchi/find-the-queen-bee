package rewardTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import entity.Bee;
import main.GamePanel;
import main.KeyHandler;
import reward.RegularReward;
import reward.Reward;

public class RegularRewardTest {

    private RegularReward regReward;

    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        regReward = new RegularReward(gp);
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
    public void regRewardConstructorTest() throws IOException {
        //BufferedImage img = ImageIO.read(getClass().getResource("../ui/images/HoneyDrop.png"));
        assertNotNull(regReward);
        assertNotNull(regReward.getImage());
        int valueResult = regReward.getValue();
        assertEquals(10, valueResult);
        int widthResult = regReward.width;
        assertEquals(60, widthResult);
        int heightResult = regReward.height;
        assertEquals(60, heightResult);
    }

    @Test
    void testCollectReward() {
        GamePanel gp = regReward.getMap();
        KeyHandler kh = new KeyHandler(gp);
        Bee bee = new Bee(gp, kh);

        // assert the bee's reward list does not already contain this reward
        boolean inListInitially = bee.rewardList.contains(regReward);
        assertFalse(inListInitially); 
        int initial_score = bee.beeScore;
        
        regReward.collectReward(bee);

        // assert the bee's reward list contains this reward after calling collectReward(bee)
        boolean inListResult = bee.rewardList.contains(regReward);
        assertTrue(inListResult);
        // assert the bee's score increased by the value of a regular reward
        int scoreResult = bee.beeScore;
        assertEquals(initial_score + regReward.value ,scoreResult); 
    }

    @Test
    void testRemove() {
        int x = 4 * regReward.getMap().tileSize;
        int y = 3 * regReward.getMap().tileSize;
        regReward.setLocation(x, y);

        regReward.remove();

        int xResult = regReward.worldX;
        int yResult = regReward.worldY;
        BufferedImage imgResult = regReward.getImage();
        // assert the reward is now at the unaccessible point (0,0)
        assertEquals(0, xResult);
        assertEquals(0, yResult);
        // assert the reward's image is now null
        assertNull(imgResult);
    }
}


