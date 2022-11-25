package mainTest;

import org.junit.jupiter.api.*;

import main.GamePanel;

public class GamePanelTest {
	GamePanel g;

	@BeforeEach
	public void setUp() {
		g = new GamePanel();
	}

	@Test
	void testUpdateMethod() {
		g.gameState = GamePanel.playState;
		g.startGameThread();
		g.setupGame(0);
		g.update();
	}

	@Test
	void testUpdateQueenMessage() {
		g.updateQueenMessage();
	}

	@Test
	void testUpdateGameCompletion() {
		g.updateGameCompletion();
	}

	@Test
	void testPlayMusic() {
		g.playMusic(1);
		g.stopMusic();
	}

}
