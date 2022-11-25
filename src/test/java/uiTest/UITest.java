package uiTest;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import java.awt.Graphics;

import main.GamePanel;
import main.UI;
import main.Main;

public class UITest {

	UI ui;
	GamePanel gp;
	Graphics g;

	@BeforeEach
	public void setUp() throws Exception {
		gp = new GamePanel();
		ui = new UI(gp);
	}

	@Test
	void testDrawTitleMethod1() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.ui.commandNum = 1;
		gamePanel.setupGame(0);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawTitleMethod2() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.ui.commandNum = 2;
		gamePanel.setupGame(0);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawPlayMethod() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.setupGame(1);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawPauseMethod1() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.ui.pauseCommandNum = 1;
		gamePanel.setupGame(2);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawPauseMethod2() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.ui.pauseCommandNum = 2;
		gamePanel.setupGame(2);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawWinMethod() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.setupGame(3);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawControlMethod() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.setupGame(5);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

	@Test
	void testDrawOverMethod() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Bee Maze");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);

		// Game Setup
		gamePanel.ui.endCommandNum = 1;
		gamePanel.setupGame(3);

		window.setVisible(true);

		gamePanel.startGameThread();
	}

}
