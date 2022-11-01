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

        // drawing multiple honey drop rewards


        for (int i = 2; i < 13; i++) {

            gp.objects[i] = new OBJ_HoneyDropReward();
            gp.objects[i].worldX = getRandomNum(1, 23) * gp.tileSize;
            gp.objects[i].worldY = getRandomNum(1, 23) * gp.tileSize;

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
