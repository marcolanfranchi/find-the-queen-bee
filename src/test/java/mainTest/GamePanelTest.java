package mainTest;

import org.junit.jupiter.api.*;

import entity.Enemy;
import main.GamePanel;
import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanelTest {
	GamePanel g;

	@BeforeEach
	public void setUp() {
		g = new GamePanel();
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

	@Test
	void testDrawObjects() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		g.objManager.setObjects();

		System.out.println("g.objects = " + g.objects);

		g.drawObjects(g2);
	}

	@Test
	void testDrawRewards() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		g.rewardGenerator.setRegRewards();
		g.rewardGenerator.setBonusRewards();

		System.out.println("g.rewards = " + g.rewards);

		g.drawRewards(g2);
	}

	@Test
	void testDrawRewardsNull() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		System.out.println("g.rewards = " + g.rewards);

		g.drawRewards(g2);
	}

	@Test
	void testGameLost() {
		g.bee.beeScore = -1;
		for (int i = 0; i < g.enemies.length; i++) {
			g.enemies[i] = new Enemy(g);
		}
		g.updateGameLost();

		assertEquals(GamePanel.gameOverState, g.gameState);

		g.gameState = GamePanel.playState;
		g.bee.beeScore = 2;
		g.updateGameLost();

		assertEquals(GamePanel.playState, g.gameState);
	}

	@Test
	void testStartGameThread() {
		g.startGameThread();
		assertNotNull(g);
	}

	@Test
	void testSetupGame() {
		g.setupGame(0);
		assertNotNull(g);
	}

	@Test
	void testRun(){
		g.run();
		assertNotNull(g);
	}
}
