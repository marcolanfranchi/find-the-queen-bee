package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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

	TileManager tileM = new TileManager(this);

	Thread gameThread;

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
		while (gameThread != null) {
			update();

			repaint();
		}
	}

	public void update() {

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);

		g2.dispose();
	}
}
