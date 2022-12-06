package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Bee;
import entity.Enemy;
import object.ObjectManager;
import object.OBJ_TextBubble;
import object.SuperObject;
import reward.Reward;
import reward.RewardGenerator;
import tile.TileManager;
import environment.EnvironmentManager;
import environment.Sound;

/**
 * This class is the Game Panel which contains the UI at all different states of 
 * the game, the map and all of its components, and methods to run and update the game.
 * 
 * @author Satvik Garg
 * @author Marco Lanfranchi
 */
public class GamePanel extends JPanel implements Runnable {

	// SYSTEM
	EnvironmentManager eManager = new EnvironmentManager(this);
	public TileManager tileM = new TileManager(this);
	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler(this);
	public UI ui = new UI(this);
	Sound sound = new Sound();
	Sound music = new Sound();

	// Screen Setting
	final int originalTileSize = 16;
	final int scale = 3;
	int FPS = 10;

	// Entities, Rewards, & Objects
	public Bee bee = new Bee(this, keyHandler);
	public Enemy[] enemies = new Enemy[2];
	public RewardGenerator rewardGenerator = new RewardGenerator(this);
	public Reward rewards[] = new Reward[rewardGenerator.totalRewards];
	public SuperObject objects[] = new SuperObject[20];
	public ObjectManager objManager = new ObjectManager(this);


	// World Settings
	public final int tileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 25;
	public final int maxScreenRow = 16;
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


	/**
	 * Creates an instance of a GamePanel and sets it's size, background color,
	 * and KeyHandler.
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}


	/**
	 * Calls setup methods to create instances of this GamePanels components with set 
	 * fields and sets the starting game state as the title state.
	 */
	public void setupGame(int gameStateToPut) {
		eManager.setup();
		rewardGenerator.setRegRewards();
		rewardGenerator.setBonusRewards();
		objManager.setObjects();
		// instances of enemies
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy(this);
		}
		gameState = gameStateToPut;
	}

	
	/**
	 * Creates a new Game thread and starts it.
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Runs the game by calling this GamePanel's update method
	 * each frame per second.
	 */
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

	/**
	 * Updates all items needing to be updated in this GamePanel and checks
	 * if the game has been won or lost while it is in the play state.
	 */
	public void update() {
		if (gameState == playState) {
			bee.update();
			// update all enemies in enemies
			for (int i = 0; i < enemies.length; i++) {
				enemies[i].update();
			}
			updateQueenMessage();
			updateGameLost();
			updateGameCompletion();			
		}
	}


	/**
	 * Draws all of this GamePanel's items to the given Graphics object according
	 * to the game state.
	 * @param g a Graphics object used to paint on all components in the GamePanel
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// title screen
		if (gameState == titleState) {
			ui.draw(g2);
		} else {
			tileM.draw(g2);
			drawRewards(g2);
			drawObjects(g2);

			// draw all enemies in enemy
			for (int i = 0; i < enemies.length; i++) {
				enemies[i].draw(g2);
			}

			bee.draw(g2);
			eManager.draw(g2);

			// UI
			ui.draw(g2);
		}

		g2.dispose();
	}


	/**
	 * Checks if this GamePanel's Bee is near the Queen Bee and does not have all
	 * of the rewards and if so, set's the Text Bubble object's image to a warning
	 * to collect all regular rewards. If not, set the text bubble object to it's 
	 * original image.
	 */
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

	/**
	 * For all Objects in this GamePanel's objects list, calls draw on the
	 * objects if it is not null.
	 * @param g2 Graphics2D object used to contain all images and text for the UI.
	 */
	public void drawObjects(Graphics2D g2) {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null) {
				objects[i].draw(g2, this); 
			}
		}
	}

	/**
	 * For all Rewards in this GamePanel's rewards list, calls draw on the
	 * rewards if it is not null.
	 * @param g2 Graphics2D object used to contain all images and text for the UI.
	 */
	public void drawRewards(Graphics2D g2) {
		for (int i = 0; i < rewards.length; i++) {
			if (rewards[i] != null) {
				rewards[i].draw(g2, this); 
			}
		}
	}

	/**
	 * Checks if this GamePanel's Bee won the game, if so sets the game state
	 * to the win state and plays the corresponding music.
	 */
	public void updateGameCompletion() {
		if (bee.checkGameWon()) {
			gameState = winState;
			playMusic(1);
		}
	}

	/**
	 * Checks if this GamePanel's Bee's score is below 0, if so sets the game
	 * state to the game over state and plays the corresponding music. Checks 
	 * if Bee dies to one of this GamePanel's enemies, if so sets the game state
	 * to the game over state and plays the corresponding music.
	 */
	public void updateGameLost() {
		if (bee.beeScore < 0) {
			gameState = gameOverState;
			playMusic(3);

		}

		if(bee.checkGameOver(bee, this.enemies) <= 45){
            gameState = gameOverState; 
			playMusic(3);
        }
	}

	/**
	 * Sets this GamePanel's Sound to the Sound at the given index i, plays
	 * the audio and loops the audio.
	 * @param i an int representing an index containing a Sound in Sound's soundURL list
	 */
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	/**
	 * Stops the audio playing from this GamePanel's Sound.
	 */
	public void stopMusic() {
		music.stop();
	}

	/**
	 * Sets this GamePanel's Sound to the Sound at the given index i and plays
	 * the audio.
	 * @param i an int representing an index containing a Sound in Sound's soundURL list
	 */
	public void playSoundEffect(int i) {
		sound.setFile(i);
		sound.play();
	}
}
