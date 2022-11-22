package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This class is used to observe and handle the game player's 
 * keyboard inputs.
 * 
 * @author Marco Lanfranchi
 * @author Satvik Garg
 */
public class KeyHandler implements KeyListener {

    public GamePanel gamePanel;

    public boolean upPressed, downPressed, 
                    leftPressed, rightPressed;

	private int prevState = GamePanel.titleState;

	/**
	 * Creates an instance of KeyHandler and sets it's GamePanel to the given GamePanel
	 * @param gp a GamePanel which this KeyListener will observe
	 */
    public KeyHandler(GamePanel gp) {
        this.gamePanel = gp;
    }

    
    /**
	 * 
	 */
	public void keyTyped(KeyEvent e) {
        
    }

    /**
	 * Observes the given KeyEvent and applies the appropriate UI change based on this
	 * KeyHandler's GamePanel's current state. Sets the boolean values for indicating
	 * the directional change attempting to be made using the player's move keys.
	 * @param e a KeyEvent indicating the keyboard stroke by the game player
	 */
    public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		// title state
		if (gamePanel.gameState == GamePanel.titleState) {
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				gamePanel.ui.commandNum--;
				if (gamePanel.ui.commandNum < 0) {
					gamePanel.ui.commandNum = 2;
				}
			}

			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				gamePanel.ui.commandNum++;
				if (gamePanel.ui.commandNum > 2) {
					gamePanel.ui.commandNum = 0;
				}
			}

			if(keyCode == KeyEvent.VK_ENTER) {
				if(gamePanel.ui.commandNum == 0) {
					gamePanel.gameState = GamePanel.playState;
				}
				if(gamePanel.ui.commandNum == 1) {
					prevState = gamePanel.gameState;
					gamePanel.gameState = GamePanel.controlState;
				}
				if(gamePanel.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}

		// pause state
		if (gamePanel.gameState == GamePanel.pauseState) {
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				gamePanel.ui.pauseCommandNum--;
				if (gamePanel.ui.pauseCommandNum < 0) {
					gamePanel.ui.pauseCommandNum = 2;
				}
			}

			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				gamePanel.ui.pauseCommandNum++;
				if (gamePanel.ui.pauseCommandNum > 2) {
					gamePanel.ui.pauseCommandNum = 0;
				}
			}

			if (keyCode == KeyEvent.VK_ENTER) {
				if (gamePanel.ui.pauseCommandNum == 0) {
					gamePanel.gameState = GamePanel.playState;
				}
				if (gamePanel.ui.pauseCommandNum == 1) {
					prevState = gamePanel.gameState;
					System.out.println("PrevState == " + prevState);
					gamePanel.gameState = GamePanel.controlState;
				}
				if (gamePanel.ui.pauseCommandNum == 2) {
					System.exit(0);
				}
			}
		}

		// Game Over state
		if (gamePanel.gameState == GamePanel.gameOverState) {
			if (keyCode == KeyEvent.VK_ENTER) {
				if (gamePanel.ui.endCommandNum == 0) {
					System.exit(0);
				}
			}
		}

		// Game Win state
		if (gamePanel.gameState == GamePanel.winState) {
			if (keyCode == KeyEvent.VK_ENTER) {
				if (gamePanel.ui.winCommandNum == 0) {
					System.exit(0);
				}
			}
		}

		// Game Play state
		if (keyCode == KeyEvent.VK_ESCAPE) {
			if (gamePanel.gameState == GamePanel.playState) {
				gamePanel.gameState = GamePanel.pauseState;
			} else if (gamePanel.gameState == GamePanel.pauseState) {
				gamePanel.gameState = GamePanel.playState;
			} else if (gamePanel.gameState == GamePanel.controlState) {
				System.out.println("PrevState == " + prevState);
				gamePanel.gameState = prevState;
			}
		}

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


    /**
	 * Sets the boolean values for indicating the directional move being made to
	 * False for the given KeyEvent to indicate when they Key was released
	 * @param e a KeyEvent indicating the keyboard key released the game player
	 */
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
