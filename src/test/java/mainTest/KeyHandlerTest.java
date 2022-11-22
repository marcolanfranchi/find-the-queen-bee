package mainTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.GamePanel;
import main.KeyHandler;
import java.awt.event.KeyEvent;


public class KeyHandlerTest {

    private KeyHandler keyHandler;
    
    @BeforeEach
    void setup() {
        GamePanel gp = new GamePanel();
        keyHandler = new KeyHandler(gp);
    }

    // @Test
    // void testKeyPressedPlayStateEsc_Pressed() {
    //     keyHandler.gamePanel.gameState = GamePanel.playState;
    //     int esc_Pressed = KeyEvent.VK_ESCAPE;
    //     keyHandler.keyPressed(esc_Pressed);
    // }
}