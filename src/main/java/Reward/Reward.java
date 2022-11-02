package Reward;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Reward {

    private GamePanel map;
    protected int value;
    protected BufferedImage image;
    protected int worldX;
    protected int worldY;
    public boolean collected;
    public int width;
    public int height;

    //Creates a reward given the parameters value, image, location
    public Reward(GamePanel gp) {
        map = gp;
        width = 60;
        height = 60;
    }

    public void checkCollected() {

    }

    public abstract void draw(Graphics2D g2, GamePanel gamePanel);

    //Mutators
    public void setMap(GamePanel map) {
        this.map = map;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTexture(BufferedImage image) {
        this.image = image;
    }

    //Accessors
    public GamePanel getMap() {
        return map;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getTexture() {
        return image;
    }
}