package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Bee;
import main.GamePanel;
import main.KeyHandler;
import reward.BonusReward;
import reward.RegularReward;
import reward.Reward;

public class BeeTest {

    private Bee bee;
    
    @BeforeEach 
    void setup() {
        GamePanel gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        KeyHandler kh = new KeyHandler(gp);
        bee = new Bee(gp, kh);
    }

    @Test
    void testMoveBeeUpKeyNotPressedInvalidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("up", false);
        bee.setCanMove("up", false);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction; // initialized as "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeUpKeyNotPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("up", false);
        bee.setCanMove("up", true);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction; // initialized as "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeUpKeyPressedInValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("up", true);
        bee.setCanMove("up", false);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction; // initialized as "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeUpKeyPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 2 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("up", true);
        bee.setCanMove("up", true);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y - bee.speed, resultY); // assert Y value decreased by the Bee's speed
        String resultDir = bee.direction; // initialized as "down"
        assertEquals("up", resultDir); // assert direction changed to "up"
    }

    

    @Test
    void testMoveBeeDownKeyNotPressedInValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.direction = "up";
        bee.setDirectionPressed("down", false);
        bee.setCanMove("down", false);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction;
        assertEquals("up", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeDownKeyNotPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.direction = "up";
        bee.setDirectionPressed("down", false);
        bee.setCanMove("down", true);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction;
        assertEquals("up", resultDir); // assert direction did not change
    }
    
    @Test
    void testMoveBeeDownKeyPressedInValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.direction = "up";
        bee.setDirectionPressed("down", true);
        bee.setCanMove("down", false);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y, resultY); // assert Y value did not change
        String resultDir = bee.direction;
        assertEquals("up", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeDownKeyPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.direction = "up";
        bee.setDirectionPressed("down", true);
        bee.setCanMove("down", true);
        bee.moveBee();
        int resultY = bee.worldY;
        assertEquals(y + bee.speed, resultY); // assert Y value increased by Bee's speed
        String resultDir = bee.direction;
        assertEquals("down", resultDir); // assert direction changed to "down"
    }


    @Test
    void testMoveBeeLeftKeyNotPressedInValidMove() {
        int x = 2 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("left", false);
        bee.setCanMove("left", false);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeLeftKeyNotPressedValidMove() {
        int x = 2 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("left", false);
        bee.setCanMove("left", true);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }
    
    @Test
    void testMoveBeeLeftKeyPressedInValidMove() {
        int x = 2 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("left", true);
        bee.setCanMove("left", false);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeLeftKeyPressedValidMove() {
        int x = 2 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("left", true);
        bee.setCanMove("left", true);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x - bee.speed, resultX); // assert X value decreased by Bee's speed
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("left", resultDir); // assert direction changed to "left"
    }


    @Test
    void testMoveBeeRightKeyNotPressedInValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("right", false);
        bee.setCanMove("right", false);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeRightKeyNotPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("right", false);
        bee.setCanMove("right", true);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }
    
    @Test
    void testMoveBeeRightKeyPressedInValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("right", true);
        bee.setCanMove("right", false);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x, resultX); // assert X value did not change
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("down", resultDir); // assert direction did not change
    }

    @Test
    void testMoveBeeRightKeyPressedValidMove() {
        int x = 1 * bee.gamePanel.tileSize;
        int y = 1 * bee.gamePanel.tileSize;
        bee.setLocation(x, y);
        bee.setDirectionPressed("right", true);
        bee.setCanMove("right", true);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x + bee.speed, resultX); // assert X value decreased by Bee's speed
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("right", resultDir); // assert direction changed to "left"
    }

    @Test
    void testCheckReachedEndX_OnY_In() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int tileSize = bee.gamePanel.tileSize;
        int x_on = 23 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_on, y_in);
        boolean result = bee.checkReachedEnd();
        assertTrue(result);
    }

    @Test
    void testCheckReachedEndX_OffY_In() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int tileSize = bee.gamePanel.tileSize;
        int x_off = 22 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_off, y_in);
        boolean result = bee.checkReachedEnd();
        assertFalse(result);
    }

    @Test
    void testCheckReachedEndX_InY_On() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 23 * tileSize;
        int y_on = 23 * tileSize;
        bee.setLocation(x_in, y_on);
        boolean result = bee.checkReachedEnd();
        assertTrue(result);
    }

    @Test
    void testCheckReachedEndX_InY_Off() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 23 * tileSize;
        int y_off = 22 * tileSize;
        bee.setLocation(x_in, y_off);
        boolean result = bee.checkReachedEnd();
        assertFalse(result);
    }

    @Test
    void testCheckGameWonLocation_OnRewards_In() {
        int tileSize = bee.gamePanel.tileSize;
        int x_on = 23 * tileSize;
        int y_on = 23 * tileSize;
        bee.setLocation(x_on, y_on);
        int maxRegReward = 10;
        int rewardNumIn = maxRegReward;
        bee.addNumRewards(rewardNumIn);
        boolean result = bee.checkGameWon();
        assertTrue(result);
    }

    @Test
    void testCheckGameWonLocation_OffRewards_In() {
        int tileSize = bee.gamePanel.tileSize;
        int x_off = 22 * tileSize;
        int y_off = 22 * tileSize;
        bee.setLocation(x_off, y_off);
        int maxRegReward = 10;
        int rewardNumIn = maxRegReward;
        bee.addNumRewards(rewardNumIn);
        boolean result = bee.checkGameWon();
        assertFalse(result);
    }

    @Test
    void testCheckGameWonLocation_InRewards_On() {
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 23 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_in, y_in);
        int maxRegReward = 10;
        int rewardNumOn = maxRegReward;
        bee.addNumRewards(rewardNumOn);
        boolean result = bee.checkGameWon();
        assertTrue(result);
    }

    @Test
    void testCheckGameWonLocation_InRewards_Off() {
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 23 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_in, y_in);
        int maxRegReward = 10;
        int rewardNumOff = maxRegReward - 1;
        bee.addNumRewards(rewardNumOff);
        boolean result = bee.checkGameWon();
        assertFalse(result);
    }

    @Test
    void testPickupRewardLocation_OnDisplay_In() {
        int tileSize = bee.gamePanel.tileSize;
        int x_on = 2 * tileSize;
        int y_on = 4 * tileSize;
        bee.setLocation(x_on, y_on);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_on, y_on); // set reward location on same tile as the bee
        bee.gamePanel.rewards[0] = regReward; // add reward to gamePanel's reward list
        
        boolean display_in = true;
        regReward.displayNow = display_in;

        bee.pickUpReward(regReward);
        boolean collectResult = bee.rewardList.contains(regReward);
        assertTrue(collectResult);
    
        // assert the index in the gamepanel's reward list is now null after collecting the reward
        assertNull(bee.gamePanel.rewards[0].getImage());
    }

    @Test
    void testPickupRewardLocation_OffDisplay_In() {
        int tileSize = bee.gamePanel.tileSize;
        int x = 2 * tileSize;
        int y = 4 * tileSize;
        bee.setLocation(x, y);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x_off = x + tileSize; 
        int y_off = y + 2 * tileSize;
        regReward.setLocation(x_off, y_off); // set reward location on a different tile than the bee
        boolean display_in = true;
        regReward.displayNow = display_in;

        bee.pickUpReward(regReward);
        boolean result = bee.rewardList.contains(regReward);
        assertFalse(result);
    }

    @Test
    void testPickupRewardLocation_InDisplay_On() {
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 2 * tileSize;
        int y_in = 4 * tileSize;
        bee.setLocation(x_in, y_in);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_in, y_in); // set reward location on same tile as the bee
        boolean display_on = true;
        regReward.displayNow = display_on;

        bee.pickUpReward(regReward);
        boolean result = bee.rewardList.contains(regReward);
        assertTrue(result);
    }

    @Test
    void testPickupRewardLocation_InDisplay_Off() {
        int tileSize = bee.gamePanel.tileSize;
        int x_in = 2 * tileSize;
        int y_in = 4 * tileSize;
        bee.setLocation(x_in, y_in);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_in, y_in); // set reward location on same tile as the bee
        boolean display_off = false;
        regReward.displayNow = display_off;

        bee.pickUpReward(regReward);
        boolean result = bee.rewardList.contains(regReward);
        assertFalse(result);
    }


    
}
