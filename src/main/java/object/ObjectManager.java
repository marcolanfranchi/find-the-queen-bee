package object;

import java.util.Random;

import main.GamePanel;

public class ObjectManager {

    GamePanel gp;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objects[0] = new OBJ_QueenBee();
        gp.objects[0].worldX = 23 * gp.tileSize; 
        gp.objects[0].worldY = 23 * gp.tileSize;

        gp.objects[1] = new OBJ_TextBubble();
        gp.objects[1].worldX = 23 * gp.tileSize - 24;
        gp.objects[1].worldY = 22 * gp.tileSize;

        // drawing multiple honey drop rewards randomly, avoiding map walls
        for (int i = 2; i < 13; i++) {

            gp.objects[i] = new OBJ_HoneyDropReward();
            int randomX = getRandomNum(0, 24);
            int randomY = getRandomNum(0, 24);
            if (randomX % 6 == 0) {
                randomX++;
                if (randomX == 25) {
                    randomX -= 2;
                }
            }
            if (randomY % 6 == 0) {
                randomY++;
                if (randomY == 25) {
                    randomY -= 2;
                }
            }
            gp.objects[i].worldX = randomX * gp.tileSize;
            gp.objects[i].worldY = randomY * gp.tileSize;
        }

        // gp.objects[2] = new OBJ_HoneyDropReward();
        // gp.objects[2].worldX = 2 * gp.tileSize;
        // gp.objects[2].worldY = 2 * gp.tileSize;

        // gp.objects[3] = new OBJ_HoneyDropReward();
        // gp.objects[3].worldX = 2 * gp.tileSize;
        // gp.objects[3].worldY = 2 * gp.tileSize;


        
        
        
    }

    public static int getRandomNum(int min, int max) {
        Random num = new Random();
        return num.nextInt(max - min + 1) + min;
    }
}
