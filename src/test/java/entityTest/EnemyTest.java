package entityTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    void setup(){
        GamePanel gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        enemy = new Enemy(gp);
    }

    @Test
    void testEnemyIsPlacedOnMap(){
        enemy.setRandomStartPoint();
        assertTrue(enemy.worldX > 0 && enemy.worldX <= 23 * enemy.gamePanel.tileSize);
        assertTrue(enemy.worldY > 0  && enemy.worldY <= 23 * enemy.gamePanel.tileSize);
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

}
