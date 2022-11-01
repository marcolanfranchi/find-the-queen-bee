package object;

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
        
        
    }
    
}
