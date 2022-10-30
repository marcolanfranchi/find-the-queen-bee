package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Bee;
import Environment.EnvironmentManager;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SYSTEM
	EnvironmentManager eManager = new EnvironmentManager(this);
	public TileManager tileM = new TileManager(this);
	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler(this);
	public UI ui = new UI(this);

	// Screen Setting
	final int originalTileSize = 16;
	final int scale = 3;
	int FPS = 10;

	// Entity and Object
	public Bee bee = new Bee(this, keyHandler);

	// World Settings
	public final int tileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 20;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	public final int maxWorldCol = 25;
	public final int maxWorldRow = 25;
	public final int worldWidth = tileSize * maxWorldCol; // 1200 pixels
	public final int worldHeight = tileSize * maxWorldRow; // 1200 pixels

	// Game State
	public int gameState;
	public static final int playState = 1;
	public static final int pauseState = 2;
	public static final int titleState = 0;


	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		eManager.setup();
		gameState = titleState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double tick = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / tick;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}

		}
	}

	public void update() {
		if (gameState == playState) {
			bee.update();
		}

		if (gameState == pauseState) {
			// do nothing
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// title screen
		if (gameState == titleState) {
			ui.draw(g2);
		} else {

			tileM.draw(g2);
			bee.draw(g2);
			eManager.draw(g2);

			// Temporary Text for Testing
			if (false) {
				g2.setFont(new Font("TimesRoman", Font.BOLD, 25));
				g2.drawString("Bee WorldX: " + bee.worldX, 50, 50);
				g2.drawString("Bee WorldY: " + bee.worldY, 50, 80);
			}

			// UI
			ui.draw(g2);
		}

		g2.dispose();
	}
}
