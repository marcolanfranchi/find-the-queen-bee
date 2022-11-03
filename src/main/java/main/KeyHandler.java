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

	private int prevState = GamePanel.titleState;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
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
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				gamePanel.ui.endCommandNum--;
				if (gamePanel.ui.endCommandNum < 0) {
					gamePanel.ui.endCommandNum = 1;
				}
			}

			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				gamePanel.ui.endCommandNum++;
				if (gamePanel.ui.endCommandNum > 1) {
					gamePanel.ui.endCommandNum = 0;
				}
			}

			if (keyCode == KeyEvent.VK_ENTER) {
				if (gamePanel.ui.endCommandNum == 0) {
					gamePanel.gameState = GamePanel.titleState;
				}
				if (gamePanel.ui.endCommandNum == 1) {
					System.exit(0);
				}
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
