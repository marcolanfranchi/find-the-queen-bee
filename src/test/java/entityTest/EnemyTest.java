package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Enemy;
import main.GamePanel;

public class EnemyTest {
    
    private Enemy enemy;

    @BeforeEach
    void setup(){
        GamePanel gp = new GamePanel();
        gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
        enemy = new Enemy(gp);
    }

    @Test
    void testEnemyToReachDecidedLocation(){
        enemy.instantiateNodes();
        GamePanel gamePanel = new GamePanel();
        int goalCol = gamePanel.bee.worldX;
        int goalRow = gamePanel.bee.worldY;
        int speed = gamePanel.tileSize / 2;
        enemy.setRandomStartPoint();
        enemy.searchPath(goalCol, goalRow);
        int showX = enemy.tempX;
        int showY = enemy.tempY;
        assertTrue(enemy.search());
    }
}
