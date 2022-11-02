package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Entity.Bee;
import Entity.Enemy;
import Environment.EnvironmentManager;
import Tile.TileManager;
import object.ObjectManager;
import object.OBJ_QueenBee;
import object.OBJ_TextBubble;
import object.SuperObject;

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
	public Enemy enemy = new Enemy(this);
	public SuperObject objects[] = new SuperObject[20];
	public ObjectManager objManager = new ObjectManager(this);


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
	public static final int gameOverState = 3;
	public static final int winState = 4;
	public static final int controlState = 5;


	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}


	public void setupGame() {
		eManager.setup();
		objManager.setObjects();
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
			enemy.update();
			updateQueenMessage();
			//update QueenBee image

			// update message from queen bee
			//objManager.updateObjects(this);
			
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

			for(int i = 0; i < objects.length; i++) {
				if (objects[i] != null) {
					objects[i].draw(g2, this);
				}
			}

			enemy.draw(g2);
			bee.draw(g2);
			eManager.draw(g2);

			// Temporary Text for Testing
			// if (false) {
			// 	g2.setFont(new Font("TimesRoman", Font.BOLD, 25));
			// 	g2.drawString("Bee WorldX: " + bee.worldX, 50, 50);
			// 	g2.drawString("Bee WorldY: " + bee.worldY, 50, 80);
			// }

			// UI
			ui.draw(g2);
		}

		g2.dispose();
	}

	public void updateQueenMessage() {
		if (bee.nearQueenMissingRewards()) {
            ((OBJ_TextBubble) objects[1]).setAltImage();
        } else {
            if (!bee.nearQueenMissingRewards()) {
                objects[1] = new OBJ_TextBubble();
                objects[1].worldX = 23 * tileSize - 24;
                objects[1].worldY = 22 * tileSize;
            }
        }
	}
}
