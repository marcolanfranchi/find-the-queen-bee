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

    public static boolean upPressed;
    public static boolean downPressed;
    public static boolean leftPressed;
    public static boolean rightPressed;

	public int prevState = GamePanel.titleState;

	/**
	 * Creates an instance of KeyHandler and sets it's GamePanel to the given GamePanel
	 * @param gp a GamePanel which this KeyListener will observe
	 */
    public KeyHandler(GamePanel gp) {
        this.gamePanel = gp;
    }

    
    /**
	 * Mandatory method from KeyListener interface. This method is called when a key
	 * is pressed.
	 */
	public void keyTyped(KeyEvent e) {}


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
			gamePanel.ui.commandNum = updateCommandNum(keyCode, gamePanel.ui.commandNum);
		}

		// pause state
		if (gamePanel.gameState == GamePanel.pauseState) {
			gamePanel.ui.pauseCommandNum = updateCommandNum(keyCode, gamePanel.ui.pauseCommandNum);
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
				gamePanel.gameState = prevState;
			}
		}

		updatePressedVar(keyCode);
	}

	/**
	 * Updates the pressed variables in the UI class based on the given key code
	 * @param keyCode
	 */
	private void updatePressedVar(int keyCode) {
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
	 * Updates the commandNum variable in the UI class based on the given key code
	 * @param keyCode
	 * @param commandNum
	 * @return
	 */
	private int updateCommandNum(int keyCode, int commandNum) {
		System.out.println("commandNum: " + commandNum);
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			commandNum--;
			System.out.println("commandNum: " + commandNum);
			if (commandNum < 0) {
				commandNum = 2;
			}
		}

		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			commandNum++;
			if (commandNum > 2) {
				commandNum = 0;
			}
		}

		if (keyCode == KeyEvent.VK_ENTER) {
			if (commandNum == 0) {
				gamePanel.gameState = GamePanel.playState;
			}
			if (commandNum == 1) {
				prevState = gamePanel.gameState;
				gamePanel.gameState = GamePanel.controlState;
			}
			if (commandNum == 2) {
				System.exit(0);
			}
		}

		return commandNum;
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
