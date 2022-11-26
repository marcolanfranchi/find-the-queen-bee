package entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Bee;
import entity.Enemy;
import entity.Entity;
import main.GamePanel;
import main.KeyHandler;


public class EntityTest {

   private Entity entity;

   @BeforeEach
   void setup() {
      GamePanel gp = new GamePanel();
      // setting a specific map to use for testing
      gp.tileM.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
      KeyHandler kh = new KeyHandler(gp);
      entity = new Bee(gp, kh);
   }

   @Test
   void testSetX() {
      int x = 96;
      entity.setX(x);
      int result = entity.getX();
      assertEquals(x, result);
   }

   @Test
   void testSetY() {
      int y = 48;
      entity.setY(y);
      int result = entity.getY();
      assertEquals(y, result);
   }

   @Test
   void testGetTileNumWall() {
      int wallX = 0;
      int wallY = 0;
      entity.setLocation(wallX, wallY);  // (0,0) location on the txt map, which is a wall
      int result = entity.getTileNum();
      // wall num is 1 on txt map
      assertEquals(1, result);
   }

   @Test
   void testGetTileNumFloorTile() {
      int wallX = 1 * 48;
      int wallY = 1 * 48; 
      entity.setLocation(wallX, wallY); // (1,1) location on the txt map, which is a floor tile (tile size is 48)
      int result = entity.getTileNum();
      // floor tile num is 0 on txt map
      assertEquals(0, result);
   }

   @Test
   void testGetTileNumTrapTile() {
      int wallX = 3 * 48;
      int wallY = 3 * 48; 
      entity.setLocation(wallX, wallY); // (3,3) location on the txt map, which is a trap tile
      int result = entity.getTileNum();
      // trap tile num is 3 on txt map
      assertEquals(3, result);
   }

   @Test
   void testGetTileNumDoor() {
      int wallX = 6 * 48;
      int wallY = 3 * 48; 
      entity.setLocation(wallX, wallY); // (6,3) location on the txt map, which is a door
      int result = entity.getTileNum();
      // door num is 2 on txt map
      assertEquals(2, result);
   }

   @Test
   void testTileNumUp() {
      int wallX = 1 * 48;
      int wallY = 1 * 48; 
      entity.setLocation(wallX, wallY); // (1,1) location on the txt map, which is the top left corner
      // above this position is a wall (1 on txt map)
      int result = entity.tileNumUp();
      assertEquals(1, result);
   }

   @Test
   void testTileNumRight() {
      int wallX = 1 * 48;
      int wallY = 1 * 48; 
      entity.setLocation(wallX, wallY); // (1,1) location on the txt map, which is the top left corner
      // right of this position is a floor tile (0 on txt map)
      int result = entity.tileNumRight();
      assertEquals(0, result);
   }

   @Test
   void testTileNumDown() {
      int wallX = 1 * 48;
      int wallY = 1 * 48; 
      entity.setLocation(wallX, wallY); // (1,1) location on the txt map, which is the top left corner
      // under this position is a floor tile (0 on txt map)
      int result = entity.tileNumDown();
      assertEquals(0, result);
   }

   @Test
   void testTileNumLeft() {
      int wallX = 1 * 48;
      int wallY = 1 * 48; 
      entity.setLocation(wallX, wallY); // (1,1) location on the txt map, which is the top left corner
      // left of this position is a wall (1 on txt map)
      int result = entity.tileNumLeft();
      assertEquals(1, result);
   }

   @Test
   void testCheckGameOverValid() {
      int x = 8 * 48;
      int y = 8 * 48;
      entity.setLocation(x, y);
      Enemy touchingEnemy = new Enemy(entity.gamePanel);
      touchingEnemy.setLocation(x, y);
      Enemy otherEnemy = new Enemy(entity.gamePanel);
      otherEnemy.setLocation(2 * x, 2 * y);
      Enemy[] enemies = new Enemy[2];
      enemies[0] = touchingEnemy;
      enemies[1] = otherEnemy;
      double checkResult = entity.checkGameOver(entity, enemies);
      // assert this value is <= 45, indicating the entity is touching an enemy
      assertTrue(checkResult <= 45);
   }

   @Test
   void testCheckGameOverInvalid() {
      int x = 8 * 48;
      int y = 8 * 48;
      entity.setLocation(x, y);
      Enemy nonTouchingEnemy = new Enemy(entity.gamePanel);
      nonTouchingEnemy.setLocation(x + 48, y);
      Enemy nonTouchingEnemy2 = new Enemy(entity.gamePanel);
      nonTouchingEnemy2.setLocation(2 * x, 2 * y);
      Enemy[] enemies = new Enemy[2];
      enemies[0] = nonTouchingEnemy;
      enemies[1] = nonTouchingEnemy2;
      double checkResult = entity.checkGameOver(entity, enemies);
      // assert this value is > 45, indicating the entity not is touching an enemy
      assertTrue(checkResult > 45);
   }

   @Test
   void testCheckWallCollisionUpTrue() {
      int x = 1 * 48;
      int y = 1 * 48;
      entity.setLocation(x, y);  // location on the map with a wall directly above the entity
      entity.checkWallCollision();
      boolean result = entity.moveUp;
      assertEquals(false, result);
   }

   @Test
   void testCheckWallCollisionUpFalse() {
      int x = 1 * 48;
      int y = 2 * 48;
      entity.setLocation(x, y); // location on the map with no wall directly above the entity
      entity.checkWallCollision();
      boolean result = entity.moveUp;
      assertEquals(true, result);
   }

   @Test
   void testCheckWallCollisionRightTrue() {
      int x = 264;
      int y = 1 * 48;
      entity.setLocation(x, y);  // location on the map with a wall directly to the right of the entity
      entity.checkWallCollision();
      boolean result = entity.moveRight;
      assertEquals(false, result);
   }

   @Test
   void testCheckWallCollisionRightFalse() {
      int x = 4 * 48;
      int y = 1 * 48;
      entity.setLocation(x, y); // location on the map with no wall directly to the right of the entity
      entity.checkWallCollision();
      boolean result = entity.moveRight;
      assertEquals(true, result);
   }

   @Test
   void testCheckWallCollisionDownTrue() {
      int x = 1 * 48;
      int y = 264;
      entity.setLocation(x, y);  // location on the map with a wall directly under the entity
      entity.checkWallCollision();
      boolean result = entity.moveDown;
      assertEquals(false, result);
   }

   @Test
   void testCheckWallCollisionDownFalse() {
      int x = 1 * 48;
      int y = 4 * 48;
      entity.setLocation(x, y); // location on the map with no wall directly under the entity
      entity.checkWallCollision();
      boolean result = entity.moveDown;
      assertEquals(true, result);
   }

   @Test
   void testCheckWallCollisionLeftTrue() {
      int x = 1 * 48;
      int y = 1 * 48;
      entity.setLocation(x, y);  // location on the map with a wall directly to the left of the entity
      entity.checkWallCollision();
      boolean result = entity.moveLeft;
      assertEquals(false, result);
   }

   @Test
   void testCheckWallCollisionLeftFalse() {
      int x = 2 * 48;
      int y = 1 * 48;
      entity.setLocation(x, y); // location on the map with no wall directly to the left of the entity
      entity.checkWallCollision();
      boolean result = entity.moveLeft;
      assertEquals(true, result);
   }

   @Test
   void testinFirstRoomX1_onY_in() {
      int x1_on = 0;
      int y_in = 3;
      boolean result = entity.inFirstRoom(x1_on, y_in);
      assertTrue(result);
   }

   @Test
   void testinFirstRoomX1_offY_in() {
      int x1_off = -1;
      int y_in = 3;
      boolean result = entity.inFirstRoom(x1_off, y_in);
      assertFalse(result);
   }

   @Test
   void testinFirstRoomX2_onY_in() {
      int x2_on = 5;
      int y_in = 3;
      boolean result = entity.inFirstRoom(x2_on, y_in);
      assertTrue(result);
   }

   @Test
   void testinFirstRoomX2_offY_in() {
      int x2_off = 6;
      int y_in = 3;
      boolean result = entity.inFirstRoom(x2_off, y_in);
      assertFalse(result);
   }

   @Test
   void testinFirstRoomX_inY1_on() {
      int x_in = 3;
      int y1_on = 0;
      boolean result = entity.inFirstRoom(x_in, y1_on);
      assertTrue(result);
   }

   @Test
   void testinFirstRoomX_inY1_off() {
      int x_in = 3;
      int y1_off = -1;
      boolean result = entity.inFirstRoom(x_in, y1_off);
      assertFalse(result);
   }

   @Test
   void testinFirstRoomX_inY2_on() {
      int x_in = 3;
      int y2_on = 5;
      boolean result = entity.inFirstRoom(x_in, y2_on);
      assertTrue(result);
   }

   @Test
   void testinFirstRoomX_inY2_off() {
      int x_in = 3;
      int y2_off = 6;
      boolean result = entity.inFirstRoom(x_in, y2_off);
      assertFalse(result);
   }
}
