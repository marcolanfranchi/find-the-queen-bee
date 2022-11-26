package uiTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Graphics2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;


import main.GamePanel;
import main.UI;

public class UITest {

	UI ui;
	GamePanel gp;

	@BeforeEach
	public void setUp() throws Exception {
		gp = new GamePanel();
		ui = new UI(gp);
	}

	@Test
	void testDrawTitleMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.titleState;

		ui.commandNum = 0;
		ui.draw(g2);


		ui.commandNum = 1;
		ui.draw(g2);


		ui.commandNum = 2;
		ui.draw(g2);

		assertEquals(GamePanel.titleState, gp.gameState);
	}

	@Test
	void testDrawPlayMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.playState;

		ui.draw(g2);

		assertEquals(GamePanel.playState, gp.gameState);
	}

	@Test
	void testDrawPauseMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.pauseState;

		ui.pauseCommandNum = 0;
		ui.draw(g2);


		ui.pauseCommandNum = 1;
		ui.draw(g2);


		ui.pauseCommandNum = 2;
		ui.draw(g2);

		assertEquals(GamePanel.pauseState, gp.gameState);

	}

	@Test
	void testDrawOverMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.gameOverState;

		ui.endCommandNum = 0;
		ui.draw(g2);


		ui.endCommandNum = 1;
		ui.draw(g2);

		assertEquals(GamePanel.gameOverState, gp.gameState);

	}


	@Test
	void testDrawWinMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.winState;

		ui.winCommandNum = 0;
		ui.draw(g2);

		ui.winCommandNum = 1;
		ui.draw(g2);

		assertEquals(GamePanel.winState, gp.gameState);

	}

	@Test
	void testDrawControlMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		gp.gameState = GamePanel.controlState;

		ui.draw(g2);

		assertEquals(GamePanel.controlState, gp.gameState);

	}

}
