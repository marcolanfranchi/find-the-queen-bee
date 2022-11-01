package object;

import main.GamePanel;

public class ObjectManager {

    GamePanel gp;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objects[0] = new QueenBee();
        gp.objects[0].worldX = 22 * gp.tileSize; 
        gp.objects[0].worldY = 22 * gp.tileSize; 
    }
    
}
