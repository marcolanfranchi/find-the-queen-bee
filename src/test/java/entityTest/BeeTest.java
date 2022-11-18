package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Bee;
import main.GamePanel;
import main.KeyHandler;

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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 96;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 96;
        int y = 48;
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
        int x = 96;
        int y = 48;
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
        int x = 96;
        int y = 48;
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
        int x = 96;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
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
        int x = 48;
        int y = 48;
        bee.setLocation(x, y);
        bee.setDirectionPressed("right", true);
        bee.setCanMove("right", true);
        bee.moveBee();
        int resultX = bee.worldX;
        assertEquals(x + bee.speed, resultX); // assert X value decreased by Bee's speed
        String resultDir = bee.direction; // initialized at "down"
        assertEquals("right", resultDir); // assert direction changed to "left"
    }

    
}
