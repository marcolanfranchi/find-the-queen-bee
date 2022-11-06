package object;

import main.GamePanel;

/**
 * This class represents an Object Manager which sets the objects at 
 * the specific indexes of this ObjectManager's GamePanel's object list.
 * 
 * @author Marco Lanfranchi
 */
public class ObjectManager {

    GamePanel gp;

    /**
     * Creates an instance of ObjectManager with it's GamePanel as the given GamePanel.
     * @param gp a GamePanel which contains a list of objects managed by this ObjectManager.
     */
    public ObjectManager(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Sets the specific SuperObject subclasses at this ObjectManager's GamePanel's
     * objects list indexes and sets their X and Y positions.
     */
    public void setObjects() {
        gp.objects[0] = new OBJ_QueenBee();
        gp.objects[0].worldX = 23 * gp.tileSize; 
        gp.objects[0].worldY = 23 * gp.tileSize;

        gp.objects[1] = new OBJ_TextBubble();
        gp.objects[1].worldX = 23 * gp.tileSize - 24;
        gp.objects[1].worldY = 22 * gp.tileSize;
     }
}
