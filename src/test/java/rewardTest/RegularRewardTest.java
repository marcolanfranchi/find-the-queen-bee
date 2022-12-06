package rewardTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import entity.Bee;

import org.junit.jupiter.api.BeforeEach;

import main.GamePanel;
import main.KeyHandler;
import reward.RegularReward;

public class RegularRewardTest {

    private RegularReward regReward;
	GamePanel gp;

    @BeforeEach
    void setup() {
		gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        regReward = new RegularReward(gp);
    }

    @Test
    public void regRewardConstructorTest() throws IOException {
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

		int xResult = regReward.world.getX();
		int yResult = regReward.world.getY();
        BufferedImage imgResult = regReward.getImage();
        // assert the reward is now at the unaccessible point (0,0)
        assertEquals(0, xResult);
        assertEquals(0, yResult);
        // assert the reward's image is now null
        assertNull(imgResult);
    }

	@Test
	void testDraw() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		regReward.world.setX(-1000);
		regReward.draw(g2, gp);
		assertNotNull(regReward);

		regReward.world.setX(1000);
		regReward.draw(g2, gp);
		assertNotNull(regReward);

		regReward.world.setY(-1000);
		regReward.world.setX(0);
		regReward.draw(g2, gp);
		assertNotNull(regReward);

		regReward.world.setY(1000);
		regReward.draw(g2, gp);
		assertNotNull(regReward);

		regReward.collected = true;
		regReward.world.setY(0);
		regReward.draw(g2, gp);
		assertNotNull(regReward);
	}
}


