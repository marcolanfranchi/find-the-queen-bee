package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Bee;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// Screen Setting
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 19;
	public final int maxScreenRow = 19;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	int FPS = 60;

	TileManager tileM = new TileManager(this);

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	Bee bee = new Bee(this, keyHandler);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
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
				delta = delta - 1;
			}

		}
	}

	public void update() {

		bee.update();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		bee.draw(g2);

		g2.dispose();
	}
}
