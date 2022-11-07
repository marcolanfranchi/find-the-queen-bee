package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import reward.Reward;

/**
 * This class represents a Bee character in the game which is the main
 * character controlled by the player.
 * 
 * @author Marco Lanfranchi
 */
public class Bee extends Entity {

	KeyHandler keyHandler;
	public final int screenX;
	public final int screenY;
	public ArrayList<Reward> rewardList = new ArrayList<>();

	public int beeScore;
	public int punishment = 10;
	public int punishmentBuffer = 0;

	/**
	 * Creates an instance of a Bee to be held in the given GamePanel and who
	 * controls
	 * are observed by the given KeyHandler. Sets this Bee's starting X and Y
	 * position at
	 * the first tile of the map, sets it's speed, starting direction,
	 * and gets the appropriate image.
	 * 
	 * @param gp a GamePanel which will contain this Bee.
	 * @param kh a KeyHandler used to handle the player's keyboard inputs.
	 */
	public Bee(GamePanel gp, KeyHandler kh) {
		super(gp);
		this.keyHandler = kh;
		beeScore = 20; // bee starts with 30 points
		bounds = new Rectangle();
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = this.width;
		bounds.height = this.height;
		this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		worldX = 1 * gp.tileSize;
		worldY = 1 * gp.tileSize;
		speed = gp.tileSize / 2;
		direction = "down";
		setImages();
	}

	/**
	 * Sets Bee's image fields to corresponding image, 2 photos used for each
	 * direction
	 * to illustrate the bees wings flapping .
	 */
	public void setImages() {
		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/Bee-right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws this Bee on the given Graphics2D object where the X and Y position
	 * is the game map's starting point and the image corresponds to this Bee's
	 * current direction and wing-flap state.
	 * 
	 * @param g2 Graphics2D object used to contain all images and text for the UI.
	 */
	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		if (direction == "up") {
			if (spriteNum == 1) {
				image = up1;
			} else
				image = up2;
		} else if (direction == "down") {
			if (spriteNum == 1) {
				image = down1;
			} else
				image = down2;
		} else if (direction == "left") {
			if (spriteNum == 1) {
				image = left1;
			} else
				image = left2;
		} else if (direction == "right") {
			if (spriteNum == 1) {
				image = right1;
			} else
				image = right2;
		}
		g2.drawImage(image, screenX, screenY, width, height, null);
	}

	/**
	 * Update method for this Bee which is called in GamePanel's update method every
	 * frame per second. This method calls methods to check if this Bee is colliding
	 * with a wall, moving over top over a punishment tile, picking up any of the
	 * rewards in the game, update this Bee's direction, and changes the
	 * spriteCounter
	 * used to illustrate flapping wings.
	 */
	public void update() {

		checkWallCollision();
		checkPunishmentCollision();

		// for each reward, apply pickUpReward method
		for (int i = 0; i < gamePanel.rewards.length; i++) {
			if (gamePanel.rewards[i] != null) {
				pickUpReward(gamePanel.rewards[i]);
			}
		}

		updateBeeMove();

		// counter used to switch bee images to flap wings over and over
		spriteCounter++;
		if (spriteCounter > 2) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	/**
	 * Update this Bee's X or Y position according to the key pressed and
	 * switches the direction it is facing after this move.
	 */
	private void updateBeeMove() {
		if (keyHandler.upPressed && moveUp) {
			worldY -= speed;
			direction = "up";
		} else if (keyHandler.downPressed && moveDown) {
			worldY += speed;
			direction = "down";
		} else if (keyHandler.leftPressed && moveLeft) {
			worldX -= speed;
			direction = "left";
		} else if (keyHandler.rightPressed && moveRight) {
			worldX += speed;
			direction = "right";
		}
	}

	/**
	 * Returns true if this Bee has reached the end tile of the map. Returns
	 * False otherwise. The end tile's X and Y position is obtained by getting
	 * the X and Y position's of the item stored at index 0 of GamePanel's
	 * object list which is the Queen Bee object this Bee must reach.
	 * 
	 * @return a boolean value indicating if this Bee reached the end tile or not.
	 */
	public boolean checkReachedEnd() {

		int endTileX = gamePanel.objects[0].worldX;
		int endTileY = gamePanel.objects[0].worldY;

		if (this.worldX >= endTileX && this.worldY >= endTileY) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns True if this Bee has reached the end Tile and has collected all
	 * of the regular rewards, therefore completing the game. Returns False
	 * otherwise.
	 * 
	 * @return a boolean value indicating if this Bee completed all requirements and
	 *         won the game or not.
	 */
	public boolean checkDoneGameWon() {
		if (checkReachedEnd() && hasAllRegRewards()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Given a Reward, checks if this Bee is currently on the tile containing the
	 * Reward.
	 * If so, it collects the Reward, removes it from the map, and calls the
	 * appropriate sound effect to play.
	 * 
	 * @param reward a Reward which the Bee is attempting to pick up
	 */
	public void pickUpReward(Reward reward) {
		if (onReward(reward) && reward.displayNow) {
			reward.collectReward(this);
			reward.remove();
			gamePanel.playSoundEffect(0);
		}
	}

	/**
	 * Adds the given Reward to this Bee's list of Rewards collected and
	 * increases this Bee's score by the value of the Reward.
	 * 
	 * @param reward a Reward which is being collected by this Bee
	 */
	public void addReward(Reward reward) {
		rewardList.add(reward);
		beeScore += reward.value;
	}

	/**
	 * Returns True if this Bee is currently located on a Reward. False otherwise.
	 * Since this Bee moves up and down by half of a tile size and a Reward takes up
	 * an entire Tile size, the method checks if the bee is located on any 4 corners
	 * of the Reward.
	 * 
	 * @param reward a Reward to be checked if the bee is on top of
	 * @return a boolean vlaue indicating if this Bee is located on the given
	 *         Reward or not.
	 */
	public boolean onReward(Reward reward) {
		int rewardX = reward.worldX;
		int rewardY = reward.worldY;

		final boolean inTopLeft = this.worldX == rewardX && this.worldY == rewardY;
		final boolean inTopRight = this.worldX - 24 == rewardX && this.worldY == rewardY;
		final boolean inBottomLeft = this.worldX == rewardX && this.worldY == rewardY + 24;
		final boolean inBottomRight = this.worldX - 24 == rewardX && this.worldY == rewardY + 24;

		if (inTopLeft || inTopRight || inBottomLeft || inBottomRight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns True if this Bee has collected all of the Regular Rewards
	 * on the map, False otherwise.
	 * 
	 * @return a boolean value indicating if the Bee has all of the Rewards or not
	 */
	private boolean hasAllRegRewards() {
		if (rewardList.size() == gamePanel.rewardGenerator.maxRegReward) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns True if this Bee's location is within the 3 tiles of the Queen Bee
	 * and it has not collected all of the regular rewards, False otherwise.
	 * 
	 * @return a boolean value indicating if this Bee is within 3 tiles of the Queen
	 *         Bee
	 *         end tile and it has not collected all rewards.
	 */
	public boolean nearQueenMissingRewards() {
		if (this.worldX >= gamePanel.objects[0].worldX - 3 * gamePanel.tileSize &&
				this.worldY >= gamePanel.objects[0].worldY - 3 * gamePanel.tileSize) {
			if (!hasAllRegRewards()) {
				// ((OBJ_TextBubble) gamePanel.objects[1]).setAltImage();
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the Bee's current Tile locations corresponds to index 3 of the
	 * TileManager
	 * class which represents the punishment Tile, if the bee is located on this
	 * Tile
	 * calls a method to reduce score.
	 */
	public void checkPunishmentCollision() {
		if (getTileNum() == 3) {
			reduceScore();
		}
	}

	/**
	 * Uses a buffer to only apply changes to this Bee's score every 2 frames per
	 * second, increases the punishmentBuffer int by 1 and if it is an even int,
	 * reduces this Bee's score by the punishment score and plays the corresponding
	 * sound effect.
	 */
	public void reduceScore() {
		punishmentBuffer++;
		if (punishmentBuffer % 2 == 0) {
			beeScore -= punishment;
			gamePanel.playSoundEffect(2);
		}
	}
}
