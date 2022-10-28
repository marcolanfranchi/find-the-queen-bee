package Reward;
import main.Entity.Bee;
import main.GamePanel;
import java.awt.image.BufferedImage;


public abstract class Reward {
 
    private GamePanel map;
    private int value;
    private Image texture; 
    
    /*Creates a reward given the parameters
     * @param value
     * @param texture
     * @param location
     */

    public Reward(int value, Image texture, GamePanel map) {
        this.value = value;
        this.texture = texture;
        this.map = map;
    }

    //Mutators
    public void setMap(GamePanel map) {
        this.map = map;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    //Accessors
    public GamePanel getMap() {
        return map;
    }

    public int getValue() {
        return value;
    }

    public Image getTexture() {
        return texture;
    }
}

