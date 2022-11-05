package Reward;

import main.GamePanel; 

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Bee;

public abstract class Reward {

    private GamePanel map;
    public int value;
    protected BufferedImage image;
    public int worldX;
    public int worldY;
    public boolean collected;
    public int width;
    public int height;

    /*
     * Creates a reward given the parameters
     * @param value
     * @param image
     */

    //Creates a reward given the parameters value, image, location
    public Reward(GamePanel gp) {
        map = gp;
        width = 60;
        height = 60;
    }

    public abstract void draw(Graphics2D g2, GamePanel gamePanel);

    public abstract void collectReward(Bee bee);

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

    public void remove() {
        this.worldX = 0;
        this.worldY = 0;
        this.image = null;
    }
}