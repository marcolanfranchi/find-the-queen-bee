package Reward;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.awt.Point;


public abstract class Reward {

    private GamePanel map;
    private int value;
    private BufferedImage texture;
    private Point location;

    //Creates a reward given the parameters value, texture, location

    public Reward(int value, BufferedImage texture, Point location, GamePanel map) {
        this.value = value;
        this.texture = texture;
        this.location = location;
        this.map = map;
    }

    //Mutators
    public void setMap(GamePanel map) {
        this.map = map;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    //Accessors
    public GamePanel getMap() {
        return map;
    }

    public Point getLocation() {
        return location;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getTexture() {
        return texture;
    }
}