package rewardTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import entity.Bee;

import org.junit.jupiter.api.BeforeEach;

import main.GamePanel;
import main.KeyHandler;
import reward.BonusReward;


public class BonusRewardTest {
    
    private BonusReward bonusReward;
	GamePanel gp;

    @BeforeEach
    void setup() {
		gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        bonusReward = new BonusReward(gp);
    }

    @Test
    public void bonusRewardConstructorTest() throws IOException {
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
    void testCollectRewardNotDisplayed() {
        GamePanel gp = bonusReward.getMap();
        KeyHandler kh = new KeyHandler(gp);
        Bee bee = new Bee(gp, kh);

        int initial_score = bee.beeScore;
        // set the Bonus Rewards displayNow field to false;
        bonusReward.displayNow = false;
        
        bonusReward.collectReward(bee);

        // assert the bee's reward list does not contains this bonus reward after calling collectReward(bee)
        // because Bonus Rewards are not to be added to the rewardList
        boolean inListResult = bee.rewardList.contains(bonusReward);
        assertFalse(inListResult);
        // assert the bee's score did not increase by the value of a bonus reward because it is not
        // being displayed
        int scoreResult = bee.beeScore;
        assertEquals(initial_score, scoreResult); 
    }

    @Test
    void testCollectRewardDisplayed() {
        GamePanel gp = bonusReward.getMap();
        KeyHandler kh = new KeyHandler(gp);
        Bee bee = new Bee(gp, kh);

        int initial_score = bee.beeScore;
        // set the Bonus Rewards displayNow field to true;
        bonusReward.displayNow = true;
        
        bonusReward.collectReward(bee);

        // assert the bee's reward list does not contains this bonus reward after calling collectReward(bee)
        // because Bonus Rewards are not to be added to the rewardList
        boolean inListResult = bee.rewardList.contains(bonusReward);
        assertFalse(inListResult);
        // assert the bee's score did increased by the value of a bonus reward because it is 
        // being displayed
        int scoreResult = bee.beeScore;
        assertEquals(initial_score + bonusReward.getValue(), scoreResult); 
    }

    @Test
    void testRemove() {
        int x = 4 * bonusReward.getMap().tileSize;
        int y = 3 * bonusReward.getMap().tileSize;
        bonusReward.setLocation(x, y);

        bonusReward.remove();

		int xResult = bonusReward.world.getX();
		int yResult = bonusReward.world.getY();
        BufferedImage imgResult = bonusReward.getImage();
        // assert the reward is now at the unaccessible point (0,0)
        assertEquals(0, xResult);
        assertEquals(0, yResult);
        // assert the reward's image is now null
        assertNull(imgResult);
    }

	@Test
	void testUpdateTimer() {
		bonusReward.switchVisibility();
	}

	@Test
	void testDrawMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		bonusReward.world.setX(-1000);
		bonusReward.draw(g2, gp);
		assertNotNull(bonusReward);

		bonusReward.world.setX(1000);
		bonusReward.draw(g2, gp);
		assertNotNull(bonusReward);

		bonusReward.world.setY(-1000);
		bonusReward.world.setX(0);
		bonusReward.draw(g2, gp);
		assertNotNull(bonusReward);

		bonusReward.world.setY(1000);
		bonusReward.draw(g2, gp);
		assertNotNull(bonusReward);

		bonusReward.world.reset();

		bonusReward.collected = false;
		bonusReward.displayNow = true;
		bonusReward.draw(g2, gp);

	}
}
