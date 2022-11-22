package mainTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.GamePanel;
import main.KeyHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;


public class KeyHandlerTest {

    private KeyHandler keyHandler;
    
    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        keyHandler = new KeyHandler(gp);
    }

    @Test
    void testKeyPressedPlayStateEsc_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // create instance of pressing esc key
        JPanel j = new JPanel();
        KeyEvent escEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_ESCAPE, (char) 1);

        keyHandler.keyPressed(escEvent);
        // assert pressing the esc key sets the game state to pause state
        int result = keyHandler.gamePanel.gameState;
        assertEquals(GamePanel.pauseState, result);
    }

    @Test
    void testKeyPressedPauseStateEsc_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.pauseState;
        // create instance of pressing esc key
        JPanel j = new JPanel();
        KeyEvent escEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_ESCAPE, (char) 1);

        keyHandler.keyPressed(escEvent);
        // assert pressing the esc key sets the game state to play state
        int result = keyHandler.gamePanel.gameState;
        assertEquals(GamePanel.playState, result);
    }

    @Test
    void testKeyPressedControlStateEsc_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.controlState;
        // create instance of pressing esc key
        JPanel j = new JPanel();
        KeyEvent escEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_ESCAPE, (char) 1);
                                    
        keyHandler.keyPressed(escEvent);
        // assert pressing the esc key sets the game state to the title state
        int result = keyHandler.gamePanel.gameState;
        assertEquals(GamePanel.titleState, result);
    }

    @Test
    void testKeyPressedPlayStateW_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's upPressed to false
        KeyHandler.upPressed = false;
        // create instance of pressing w key
        JPanel j = new JPanel();
        KeyEvent wEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_W, (char) 1);
                                    
        keyHandler.keyPressed(wEvent);
        // assert pressing the w key sets the KeyHandler's upPressed to true
        boolean result = KeyHandler.upPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateUp_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's upPressed to false
        KeyHandler.upPressed = false;
        // create instance of pressing up arrow key
        JPanel j = new JPanel();
        KeyEvent upEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_UP, (char) 1);
                                    
        keyHandler.keyPressed(upEvent);
        // assert pressing the up arrow key sets the KeyHandler's upPressed to true
        boolean result = KeyHandler.upPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateS_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's downPressed to false
        KeyHandler.downPressed = false;
        // create instance of pressing s key
        JPanel j = new JPanel();
        KeyEvent sEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_S, (char) 1);
                                    
        keyHandler.keyPressed(sEvent);
        // assert pressing the s key sets the KeyHandler's downPressed to true
        boolean result = KeyHandler.downPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateDown_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's downPressed to false
        KeyHandler.downPressed = false;
        // create instance of pressing down arrow key
        JPanel j = new JPanel();
        KeyEvent downEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_DOWN, (char) 1);
                                    
        keyHandler.keyPressed(downEvent);
        // assert pressing the down arrow key sets the KeyHandler's downPressed to true
        boolean result = KeyHandler.downPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateA_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's leftPressed to false
        KeyHandler.leftPressed = false;
        // create instance of pressing the a key
        JPanel j = new JPanel();
        KeyEvent aEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_A, (char) 1);
                                    
        keyHandler.keyPressed(aEvent);
        // assert pressing the a key sets the KeyHandler's leftPressed to true
        boolean result = KeyHandler.leftPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateLeft_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's leftPressed to false
        KeyHandler.leftPressed = false;
        // create instance of pressing left arrow key
        JPanel j = new JPanel();
        KeyEvent leftEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_LEFT, (char) 1);
                                    
        keyHandler.keyPressed(leftEvent);
        // assert pressing the left arrow key sets the KeyHandler's leftPressed to true
        boolean result = KeyHandler.leftPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateD_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's rightPressed to false
        KeyHandler.rightPressed = false;
        // create instance of pressing the d key
        JPanel j = new JPanel();
        KeyEvent dEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_D, (char) 1);
                                    
        keyHandler.keyPressed(dEvent);
        // assert pressing the d key sets the KeyHandler's rightPressed to true
        boolean result = KeyHandler.rightPressed;
        assertTrue(result);
    }

    @Test
    void testKeyPressedPlayStateRight_Pressed() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's rightPressed to false
        KeyHandler.rightPressed = false;
        // create instance of pressing right arrow key
        JPanel j = new JPanel();
        KeyEvent rightEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_RIGHT, (char) 1);
                                    
        keyHandler.keyPressed(rightEvent);
        // assert pressing the right arrow key sets the KeyHandler's rightPressed to true
        boolean result = KeyHandler.rightPressed;
        assertTrue(result);
    }

    @Test
    void testKeyReleasedPlayStateW_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's upPressed to true
        KeyHandler.upPressed = true;
        // create instance of w key
        JPanel j = new JPanel();
        KeyEvent wEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_W, (char) 1);
                                    
        keyHandler.keyReleased(wEvent);
        // assert releasing the w key sets the KeyHandler's upPressed to false
        boolean result = KeyHandler.upPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateUp_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's upPressed to true
        KeyHandler.upPressed = true;
        // create instance of up arrow key
        JPanel j = new JPanel();
        KeyEvent upEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_UP, (char) 1);
                                    
        keyHandler.keyReleased(upEvent);
        // assert releasing the up arrow key sets the KeyHandler's upPressed to false
        boolean result = KeyHandler.upPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateS_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's downPressed to true
        KeyHandler.downPressed = true;
        // create instance of s key
        JPanel j = new JPanel();
        KeyEvent sEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_S, (char) 1);
                                    
        keyHandler.keyReleased(sEvent);
        // assert releasing the s key sets the KeyHandler's downPressed to false
        boolean result = KeyHandler.downPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateDown_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's downPressed to true
        KeyHandler.downPressed = true;
        // create instance of down arrow key
        JPanel j = new JPanel();
        KeyEvent downEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_DOWN, (char) 1);
                                    
        keyHandler.keyReleased(downEvent);
        // assert releasing the down arrow key sets the KeyHandler's downPressed to false
        boolean result = KeyHandler.downPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateA_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's leftPressed to true
        KeyHandler.leftPressed = true;
        // create instance of the a key
        JPanel j = new JPanel();
        KeyEvent aEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_A, (char) 1);
                                    
        keyHandler.keyReleased(aEvent);
        // assert releasing the a key sets the KeyHandler's leftPressed to false
        boolean result = KeyHandler.leftPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateLeft_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's leftPressed to true
        KeyHandler.leftPressed = true;
        // create instance of left arrow key
        JPanel j = new JPanel();
        KeyEvent leftEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_LEFT, (char) 1);
                                    
        keyHandler.keyReleased(leftEvent);
        // assert releasing the left arrow key sets the KeyHandler's leftPressed to false
        boolean result = KeyHandler.leftPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateD_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's rightPressed to true
        KeyHandler.rightPressed = true;
        // create instance of the d key
        JPanel j = new JPanel();
        KeyEvent dEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_D, (char) 1);
                                    
        keyHandler.keyReleased(dEvent);
        // assert releasing the d key sets the KeyHandler's rightPressed to false
        boolean result = KeyHandler.rightPressed;
        assertFalse(result);
    }

    @Test
    void testKeyReleasedPlayStateRight_Released() {
        keyHandler.gamePanel.gameState = GamePanel.playState;
        // set KeyHandler's rightPressed to true
        KeyHandler.rightPressed = true;
        // create instance of right arrow key
        JPanel j = new JPanel();
        KeyEvent rightEvent = new KeyEvent(j, 1, 1, 1, 
                                            KeyEvent.VK_RIGHT, (char) 1);
                                    
        keyHandler.keyReleased(rightEvent);
        // assert releasing the right arrow key sets the KeyHandler's rightPressed to false
        boolean result = KeyHandler.rightPressed;
        assertFalse(result);
    }





}