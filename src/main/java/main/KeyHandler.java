package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, 
                    leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) { // up
            upPressed = true;
        }

        if (keyCode == KeyEvent.VK_S) { // down
            downPressed = true;
        }

        if (keyCode == KeyEvent.VK_A) { // left
            leftPressed = true;
        }

        if (keyCode == KeyEvent.VK_D) { // right
            rightPressed = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) { // up
            upPressed = false;
        }

        if (keyCode == KeyEvent.VK_S) { // down
            downPressed = false;
        }

        if (keyCode == KeyEvent.VK_A) { // left
            leftPressed = false;
        }

        if (keyCode == KeyEvent.VK_D) { // right
            rightPressed = false;
        }
        
    }

    
}
