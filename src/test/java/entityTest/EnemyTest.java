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

    @Test
    void testEnemyCollision(){
        enemy.enemyCheckCollision();
        if (enemy.tileNumUp() == 1) {
            enemy.enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "up";
        }

        if (enemy.tileNumDown() == 1) {
            enemy.enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "down";
        }

        if (enemy.tileNumLeft() == 1) {
            enemy.enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "left";
        }

        if (enemy.tileNumRight() == 1) {
            enemy.enemyCollision = true;
            enemy.direction = null;
        } else {
            enemy.direction = "right";
        }
        assertNotNull(enemy.tileNumUp());
        assertNotNull(enemy.direction);
        assertNotNull(enemy.enemyCollision);
    }

    @Test
    void testSearchPath(){
        testNodeIsInstantiated();
        testNodeIsSetted();
        int nextX = startNode.col * enemy.gamePanel.tileSize;
        int nextY = startNode.row * enemy.gamePanel.tileSize;
        int enLeftX = enemy.worldX + enemy.bounds.x;
        int enRightX = enemy.worldX + enemy.bounds.x + enemy.bounds.width;
        int enTopY = enemy.worldY + enemy.bounds.y;
        int enDownY = enemy.worldY + enemy.bounds.y + enemy.bounds.height;

        // Enemies' moving method which contains directions for different situations
        if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + enemy.gamePanel.tileSize) {
            enemy.worldY -= enemy.speed;
            enemy.direction = "up";
        } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + enemy.gamePanel.tileSize) {
            enemy.worldY += enemy.speed;
            enemy.direction = "down";
        } else if (enTopY >= nextY && enDownY < nextY + enemy.gamePanel.tileSize) {
            if (enLeftX > nextX) {
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
            if (enLeftX < nextX) {
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
            }
        }
        else if(enTopY > nextY && enLeftX > nextX){
            enemy.worldY -= enemy.speed;
            enemy.direction = "up";
            enemy.enemyCheckCollision();
            if(enemy.enemyCollision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else if(enTopY > nextY && enLeftX < nextX){
            enemy.direction = "up";
            enemy.enemyCheckCollision();
            if(enemy.enemyCollision == true){
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
            }
        }else if(enTopY < nextY && enLeftX > nextX){
            enemy.direction = "down";
            enemy.enemyCheckCollision();
            if(enemy.enemyCollision == true){
                enemy.worldX -= enemy.speed;
                enemy.direction = "left";
            }
        }else if(enTopY < nextY && enLeftX < nextX){
            enemy.direction = "down";
            enemy.enemyCheckCollision();
            if(enemy.enemyCollision == true){
                enemy.worldX += enemy.speed;
                enemy.direction = "right";
			}
		}
        assertNotNull(nextX);
        assertNotNull(nextY);
        assertNotNull(enLeftX);
        assertNotNull(enRightX);
        assertNotNull(enTopY);
        assertNotNull(enDownY);
        assertNotNull(enemy.direction);
        assertNotNull(enemy.speed);
        assertNotNull(enemy.worldX);
        assertNotNull(enemy.worldY);
    }
}
