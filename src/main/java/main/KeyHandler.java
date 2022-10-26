package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public KeyHandler(GamePanel gp) {
        this.gamePanel = gp;
    }

    public boolean upPressed, downPressed, 
                    leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) { // up
            upPressed = true;
        }

        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) { // down
            downPressed = true;
        }

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) { // left
            leftPressed = true;
        }

        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) { // right
            rightPressed = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) { // up
            upPressed = false;
        }

        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) { // down
            downPressed = false;
        }

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) { // left
            leftPressed = false;
        }

        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) { // right
            rightPressed = false;
        }
        
    }

    
}
