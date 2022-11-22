package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    private int tileSize;
    
    @BeforeEach 
    void setup() {
        GamePanel gp = new GamePanel();
        // setting a specific map to use for testing
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        KeyHandler kh = new KeyHandler(gp);
        bee = new Bee(gp, kh);
        tileSize = gp.tileSize;
    }

    @Test
    void testMoveBeeUpKeyNotPressedInvalidMove() {
        int x = 1 * tileSize;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 2 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 1 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 2 * tileSize;;
        int y = 1 * tileSize;;
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
        int x = 2 * tileSize;
        int y = 1 * tileSize;
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
        int x = 2 * tileSize;
        int y = 1 * tileSize;
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
        int x = 2 * tileSize;
        int y = 1 * tileSize;
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
        int x = 1 * tileSize;
        int y = 1 * tileSize;
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
        int x = 1 * tileSize;
        int y = 1 * tileSize;
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
        int x = 1 * tileSize;
        int y = 1 * tileSize;
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
        int x = 1 * tileSize;
        int y = 1 * tileSize;
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
        int x_on = 23 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_on, y_in);
        boolean result = bee.checkReachedEnd();
        assertTrue(result);
    }

    @Test
    void testCheckReachedEndX_OffY_In() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int x_off = 22 * tileSize;
        int y_in = 23 * tileSize;
        bee.setLocation(x_off, y_in);
        boolean result = bee.checkReachedEnd();
        assertFalse(result);
    }

    @Test
    void testCheckReachedEndX_InY_On() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int x_in = 23 * tileSize;
        int y_on = 23 * tileSize;
        bee.setLocation(x_in, y_on);
        boolean result = bee.checkReachedEnd();
        assertTrue(result);
    }

    @Test
    void testCheckReachedEndX_InY_Off() {
        // End tile is at (23 * tileSize, 23 * tileSize)
        int x_in = 23 * tileSize;
        int y_off = 22 * tileSize;
        bee.setLocation(x_in, y_off);
        boolean result = bee.checkReachedEnd();
        assertFalse(result);
    }

    @Test
    void testCheckGameWonLocation_OnRewards_In() {
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
        int x_on = 2 * tileSize;
        int y_on = 4 * tileSize;
        bee.setLocation(x_on, y_on);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_on, y_on); // set reward location on same tile as the bee
        bee.gamePanel.rewards[0] = regReward; // add reward to gamePanel's reward list
        
        boolean display_in = true;
        regReward.displayNow = display_in;

        bee.pickUpReward(regReward);
        boolean collectedResult = bee.rewardList.contains(regReward);
        assertTrue(collectedResult);
    
        // assert the reward at index 0 in the gamepanel reward list now has a 
        // null image (indicating it has been collected)
        assertNull(bee.gamePanel.rewards[0].getImage());
    }

    @Test
    void testPickupRewardLocation_OffDisplay_In() {
        int x = 2 * tileSize;
        int y = 4 * tileSize;
        bee.setLocation(x, y);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x_off = x + tileSize; 
        int y_off = y + 2 * tileSize;
        regReward.setLocation(x_off, y_off); // set reward location on a different tile than the bee
        bee.gamePanel.rewards[0] = regReward; // add reward to gamePanel's reward list

        boolean display_in = true;
        regReward.displayNow = display_in;

        bee.pickUpReward(regReward);
        boolean collectedResult = bee.rewardList.contains(regReward);
        assertFalse(collectedResult);

        // assert the reward at index 0 in the gamepanel reward list's image remained 
        // (indicating it has not been collected)
        assertNotNull(bee.gamePanel.rewards[0].getImage());
    }

    @Test
    void testPickupRewardLocation_InDisplay_On() {
        int x_in = 2 * tileSize;
        int y_in = 4 * tileSize;
        bee.setLocation(x_in, y_in);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_in, y_in); // set reward location on same tile as the bee
        bee.gamePanel.rewards[0] = regReward; // add reward to gamePanel's reward list

        boolean display_on = true;
        regReward.displayNow = display_on;

        bee.pickUpReward(regReward);
        boolean collectedResult = bee.rewardList.contains(regReward);
        assertTrue(collectedResult);

        // assert the reward at index 0 in the gamepanel reward list now has a 
        // null image (indicating it has been collected)
        assertNull(bee.gamePanel.rewards[0].getImage());
    }

    @Test
    void testPickupRewardLocation_InDisplay_Off() {
        int x_in = 2 * tileSize;
        int y_in = 4 * tileSize;
        bee.setLocation(x_in, y_in);
        RegularReward regReward = new RegularReward(bee.gamePanel);
        regReward.setLocation(x_in, y_in); // set reward location on same tile as the bee
        bee.gamePanel.rewards[0] = regReward; // add reward to gamePanel's reward list

        boolean display_off = false;
        regReward.displayNow = display_off;

        bee.pickUpReward(regReward);
        boolean collectedResult = bee.rewardList.contains(regReward);
        assertFalse(collectedResult);

        // assert the reward at index 0 in the gamepanel reward list's image remained 
        // (indicating it has not been collected)
        assertNotNull(bee.gamePanel.rewards[0].getImage());
    }


    @Test
    void testOnRewardX_OffY_Off() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int differentX = 2 * x;
        int differentY = 2 * y;
        bee.setLocation(differentX, differentY);

        boolean result = bee.onReward(regReward);
        assertFalse(result);
    }

    @Test
    void testOnRewardX_OnY_Off() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int differentY = 2 * y;
        bee.setLocation(x, differentY);

        boolean result = bee.onReward(regReward);
        assertFalse(result);
    }

    @Test
    void testOnRewardX_OffY_On() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int differentX = 2 * x;
        bee.setLocation(differentX, y);

        boolean result = bee.onReward(regReward);
        assertFalse(result);
    }

    @Test
    void testOnRewardOnTopLeft() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int topLeftX = x;
        int topLeftY = y;
        bee.setLocation(topLeftX, topLeftY);

        boolean result = bee.onReward(regReward);
        assertTrue(result);
    }

    @Test
    void testOnRewardOnTopRight() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int topRightX = x + bee.speed;
        int topRightY = y;
        bee.setLocation(topRightX, topRightY);

        boolean result = bee.onReward(regReward);
        assertTrue(result);
    }

    @Test
    void testOnRewardOnBottomLeft() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int bottomLeftX = x;
        int bottomLeftY = y + bee.speed;
        bee.setLocation(bottomLeftX, bottomLeftY);

        boolean result = bee.onReward(regReward);
        assertTrue(result);
    }

    @Test
    void testOnRewardOnBottomRight() {
        RegularReward regReward = new RegularReward(bee.gamePanel);
        int x = 2 * tileSize;
        int y = 2 * tileSize;
        regReward.setLocation(x, y);
        int bottomRightX = x + bee.speed;
        int bottomRightY = y + bee.speed;
        bee.setLocation(bottomRightX, bottomRightY);

        boolean result = bee.onReward(regReward);
        assertTrue(result);
    }

    @Test
    void testHasAllRegRewardsNoRewards() {
        // bee has 0 regular rewards at its instantiation
        //int allRewards = bee.gamePanel.rewardGenerator.maxRegReward;
        boolean result = bee.hasAllRegRewards();
        assertFalse(result);
    }

    @Test
    void testHasAllRegRewardsSomeRewards() {
        int allRewards = bee.gamePanel.rewardGenerator.maxRegReward;
        int someRewards = allRewards - 1;
        bee.addNumRewards(someRewards);
        boolean result = bee.hasAllRegRewards();
        assertFalse(result);
    }

    @Test
    void testHasAllRegRewardsSatisfied() {
        int allRewards = bee.gamePanel.rewardGenerator.maxRegReward;
        bee.addNumRewards(allRewards);
        boolean result = bee.hasAllRegRewards();
        assertTrue(result);
    }

    // Used MC/DC to choose the following test cases for testing
    // Bee's nearQueenMissingRewards() method
    // variable p: bee's x is near the queen bee
    // variable q: bee's y is near the queen bee
    // variable r: bee has all regular rewards
    // expression: p ^ q ^ ~r, therefore X_On, Y_On, Rewards_Off is the only true case
    // 3, 5, 7, 8
    @Test
    void testNearQueenMissingRewardsX_OnY_OnRewards_Off() {
        int x_on = 23 * tileSize - (3 * tileSize);
        int y_on = 23 * tileSize;
        bee.setLocation(x_on, y_on);
        int rewards_off = bee.gamePanel.rewardGenerator.maxRegReward / 2;
        bee.addNumRewards(rewards_off);
        boolean result = bee.nearQueenMissingRewards();
        assertTrue(result);
    }

    @Test
    void testNearQueenMissingRewardsX_OnY_OnRewards_On() {
        int x_on = 23 * tileSize;
        int y_on = 23 * tileSize - (1 * tileSize);
        bee.setLocation(x_on, y_on);
        int rewards_on = bee.gamePanel.rewardGenerator.maxRegReward;
        bee.addNumRewards(rewards_on);
        boolean result = bee.nearQueenMissingRewards();
        assertFalse(result);
    }
    
    @Test
    void testNearQueenMissingRewardsX_OffY_OnRewards_Off() {
        // End tile with Queen Bee is at (23 * tileSize, 23 * tileSize)
        // any location within 3 tileSizes of this point is considered 'near'
        int x_off = 23 * tileSize - (4 * tileSize);
        int y_on = 23 * tileSize - (3 * tileSize);
        bee.setLocation(x_off, y_on);
        int rewards_off = bee.gamePanel.rewardGenerator.maxRegReward / 2;
        bee.addNumRewards(rewards_off);
        boolean result = bee.nearQueenMissingRewards();
        assertFalse(result);
    }

    @Test
    void testNearQueenMissingRewardsX_OnY_OffRewards_Off() {
        int x_on = 23 * tileSize - (2 * tileSize);
        int y_off = 23 * tileSize - (5 * tileSize);
        bee.setLocation(x_on, y_off);
        int rewards_off = bee.gamePanel.rewardGenerator.maxRegReward / 2;
        bee.addNumRewards(rewards_off);
        boolean result = bee.nearQueenMissingRewards();
        assertFalse(result);
    }

    @Test
    void testCheckPunishmentCollisionValid() {
        int trapTileX = 3 * tileSize;
        int trapTileY = 3 * tileSize;
        // set bee's location on a trap tile from the txt map used for this test class
        bee.setLocation(trapTileX, trapTileY);
        int original_score = bee.beeScore;
        bee.checkPunishmentCollision();
        int result = bee.beeScore;
        // assert score decreased by the amount ofthe punishment
        assertEquals(original_score - bee.punishment, result);
    }

    @Test
    void testCheckPunishmentCollisionInvalid() {
        int floorTileX = 2 * tileSize;
        int floorTileY = 2 * tileSize;
        // set bee's location on a trap tile from the txt map used for this test class
        bee.setLocation(floorTileX, floorTileY);
        int original_score = bee.beeScore;
        bee.checkPunishmentCollision();
        int result = bee.beeScore;
        // assert score did not change
        assertEquals(original_score, result);
    }

    @Test
    void testReduceScoreValid() {
        // a buffer is used in Bee's reduceScore() method to only decrease the score every 
        // other time this method is called, with the first call executing the score decrease
        int original_score = bee.beeScore;
        bee.reduceScore();
        int result = bee.beeScore;
        // assert reduceScore() reduced the bee's score on the first call
        assertEquals(original_score - bee.punishment, result);
    }

    @Test
    void testReduceScoreInvalid() {
        // a buffer is used in Bee's reduceScore() method to only decrease the score every 
        // other time this method is called, with the first call executing the score decrease
        bee.reduceScore();
        int resultAfterOneCall = bee.beeScore;
        // call reduceScore() a second time:
        bee.reduceScore();
        int resultAfterTwoCalls = bee.beeScore;
        // assert the second call of reduceScore() did not change the bee's score
        assertEquals(resultAfterOneCall, resultAfterTwoCalls);
    }




    
}
