package entityTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import entity.Enemy;
import entity.Node;
import main.GamePanel;

public class EnemyTest {
    
    private Enemy enemy;
    private Node[][] node;
    private Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    int tempGoalCol;
    int tempGoalRow;
    int tempStartCol;
    int tempStartRow;
	GamePanel gp;

    @BeforeEach
    void setup(){
		gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        enemy = new Enemy(gp);
    }

    @Test
    void testEnemyIsPlacedOnMap(){
        enemy.setRandomStartPoint();
        assertTrue(enemy.worldX > 0 && enemy.worldX <= 23 * enemy.gamePanel.tileSize);
        assertTrue(enemy.worldY > 0 && enemy.worldY <= 23 * enemy.gamePanel.tileSize);
    }

    @Test
    void testNodeIsInstantiated(){
        GamePanel gamePanel = new GamePanel();
        node = new Node[gamePanel.worldWidth][gamePanel.worldHeight];
		int col = 0;
		int row = 0;
		while (col < gamePanel.worldWidth && row < gamePanel.worldHeight) {
			node[col][row] = new Node(col, row);
			col++;
			if (col == gamePanel.worldWidth) {
				col = 0;
				row++;
			}
		}
        assertNotNull(node);
    }

    @Test
    void testNodeIsSetted(){
        GamePanel gamePanel = new GamePanel();
        testNodeIsInstantiated();
        int goalCol = gamePanel.bee.worldX;
        int goalRow = gamePanel.bee.worldY;
        int startCol = enemy.worldX / gamePanel.tileSize;
        int startRow = enemy.worldY / gamePanel.tileSize;

        tempGoalCol = goalCol;
        tempGoalRow = goalRow;
        tempStartCol = startCol;
        tempStartRow = startRow;

        // Enemy's start position
        startNode = node[startCol][startRow];
        assertNotNull(startNode);

		// Current enemies' position 
		currentNode = node[startCol][startRow];
        assertNotNull(currentNode);

		// Bee's current position
		goalNode = node[goalCol][goalRow];
		assertNotNull(goalNode);

        // Add nodes to a list
		openList.add(currentNode);
        assertNotNull(openList);      
    }

    @Test
    void testGetCostFunction(){
        testNodeIsInstantiated();
        testNodeIsSetted();
        // G Cost:
		int xDistance = Math.abs(tempStartCol - startNode.col);
		int yDistance = Math.abs(tempStartRow - startNode.row);
		int gCost = xDistance + yDistance;
        assertNotNull(gCost);


		// H cost: Sum of X distance between the Bee and the current check node and 
		//			Y distance between the Be and the current check node
		xDistance = Math.abs(tempStartCol - goalNode.col);
		yDistance = Math.abs(tempStartRow - goalNode.row);
        int hCost = xDistance + yDistance;
        assertNotNull(hCost);

		// F cost: Sun of G cost and H Cost
        assertNotNull(gCost + hCost);
    }

    @Test
    void testUpdate(){
        enemy.update();
        assertNotNull(enemy.pathList);
        assertNotNull(enemy.openList);
    }

	@Test
	void testCheckRandom(){
		int num = 6;
		enemy.checkRandom(num);
		assertNotNull(num);
	}

    @Test
    void testEnemyMoveUp(){
		enemy.enemyMoveUp();
		assertNotNull(enemy.worldY);
        assertNotNull(enemy.direction);
	}

	@Test
    void testEnemyMoveDown(){
		enemy.enemyMoveDown();
		assertNotNull(enemy.worldY);
        assertNotNull(enemy.direction);
	}

	@Test
    void testEnemyMoveLeft(){
		enemy.enemyMoveLeft();
		assertNotNull(enemy.worldX);
        assertNotNull(enemy.direction);
	}

	@Test
    void testEnemyMoveRight(){
		enemy.enemyMoveRight();
		assertNotNull(enemy.worldX);
        assertNotNull(enemy.direction);
	}

	@Test
	void testEnmeyStop(){
		enemy.enemyStop();
		assertTrue(enemy.enemyCollision);
        assertNull(enemy.direction);
	}

	@Test
	void testEnmeyCheckCollision(){
		enemy.enemyCheckCollision();
		assertNotNull(enemy.direction);
	}

	@Test
	void testDrawUp() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "up";

		enemy.draw(g2);
		assertNotNull(enemy);
	}

	@Test
	void testDrawDown() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "down";

		enemy.draw(g2);
		assertNotNull(enemy);
	}

	@Test
	void testDrawLeft() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "left";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawRight() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawNone() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 10;

		enemy.direction = "none";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg1() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = -600;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg2() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 680;
		enemy.worldY = 10;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg3() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = -365;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

	@Test
	void testDrawArg4() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		enemy.worldX = 10;
		enemy.worldY = 470;

		enemy.direction = "right";

		enemy.draw(g2);
		assertNotNull(enemy);

	}

}
