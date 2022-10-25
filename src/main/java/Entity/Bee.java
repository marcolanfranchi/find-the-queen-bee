package Entity;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import main.GamePanel;
//import main.KeyHandler;

public class Bee extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Bee(GamePanel gp, KeyHandler kh) {

        this.gamePanel = gp;
        this.keyHandler = kh;
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }


    public void update() {

    }

    public void draw() {

    }
    
}
