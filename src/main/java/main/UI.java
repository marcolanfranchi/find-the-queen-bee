package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import object.OBJ_ARROW;
import object.OBJ_HoneyDrop;
import object.OBJ_WASD;

/**
 * This class is used to create the user interface for the game. It is used to
 * display the score, time, and the player's health.
 * As well as all the screens for the various states of the game
 * 
 * @author Satvik Garg
 */
public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font retroPixel;
	BufferedImage honeyImage;
	BufferedImage arrowKeysImage;
	BufferedImage wasdKeysImage;

	public int commandNum = 0;
	public int pauseCommandNum = 0;
	public int endCommandNum = 0;
	public int winCommandNum = 0;

	public Timer timer;
	int second, minute;
	private int origSecond, origMinute;
	String ddSecond, ddMinute;

	DecimalFormat dformat = new DecimalFormat("00");

	/**
	 * Constructor for the UI class
	 * 
	 * @param gp The GamePanel object
	 */
	public UI(GamePanel gp) {
		this.gp = gp;
		second = origSecond = 30;
		minute = origMinute = 1;
		ddSecond = dformat.format(second);
		ddMinute = dformat.format(minute);
		countDownTimer();

		try {
			InputStream is = getClass().getResourceAsStream("../ui/font/retro_pixel.ttf");
			retroPixel = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OBJ_HoneyDrop honey = new OBJ_HoneyDrop();
		OBJ_ARROW arrowKeys = new OBJ_ARROW();
		OBJ_WASD wasdKeys = new OBJ_WASD();

		arrowKeysImage = arrowKeys.image;
		wasdKeysImage = wasdKeys.image;
		honeyImage = honey.image;
	}

	/**
	 * This method is used to draw the UI to the screen depending on the state of
	 * the game
	 * 
	 * @param g2 The Graphics2D object
	 */
	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(retroPixel);
		g2.setColor(Color.white);

		// switch statement for different game states
		switch (gp.gameState) {
			case GamePanel.playState:
				drawPlayScreen();
				break;
			case GamePanel.pauseState:
				drawPauseScreen();
				break;
			case GamePanel.titleState:
				drawTitleScreen();
				break;
			case GamePanel.gameOverState:
				drawGameOverScreen();
				break;
			case GamePanel.winState:
				drawWinScreen();
				break;
			case GamePanel.controlState:
				drawControlState();
				break;
		}
	}

	/**
	 * This method is used to draw the quit button used in multiple game states
	 * @param text the string to be displayed as a button
	 * @param height the y coordinate of the button being displayed
	 * @param command 
	 */
	private void drawTextButton(String textToDisplay, int height, int num, int commandNum) {
        String text;
        int x;
        text = textToDisplay;
        x = getXforCenteredText(text);
        g2.drawString(text, x, height);

        if (commandNum == num) {
            g2.drawString(">", x - gp.tileSize, height);
        }
    }

	/**
	 * This method is used to draw the title screen to the graphics object
	 */
	public void drawTitleScreen() {
		g2.setColor(new Color(244, 187, 68));
		g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		String text = "Find The Queen Bee";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 5;

		// shadow
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 7, y + 7);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Bee image
		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 2;
		g2.drawImage(gp.bee.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		
		// Menu
		// New Game
		y += gp.tileSize * 5;
        drawTextButton("NEW GAME", y, 0, commandNum);

		// Controls
		y += gp.tileSize * 1.5;
        drawTextButton("CONTROLS", y, 1, commandNum);

		// Quit
		y += gp.tileSize * 1.5;
		drawTextButton("QUIT", y, 2, commandNum);


	}

	/**
	 * This method is used to draw the play screen to the graphics object
	 */
	public void drawPlayScreen() {
		timer.start();

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));
		g2.setColor(Color.WHITE);
		g2.drawImage(honeyImage, gp.tileSize / 12, gp.tileSize / 12, gp.tileSize * 2, gp.tileSize * 2, null);
		// The 100 will be replaced with the current number of points in the bee class
		int x = gp.tileSize * 2;
		int y = gp.tileSize + gp.tileSize / 4;

		// Draw number of honey
		g2.drawString("x " + gp.bee.rewardList.size() + " / " + gp.rewardGenerator.maxRegReward, x, y);

		// Draw Score
		g2.drawString("Score: " + gp.bee.beeScore, gp.screenWidth - gp.tileSize * 4, gp.tileSize);

		// Timer
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));
		g2.drawString(ddMinute + ":" + ddSecond, gp.screenWidth / 2, 55);

		// Draw pause instruction text
		g2.drawString("Press ESC to Pause", gp.screenWidth - gp.tileSize * 7, gp.tileSize * 15);

	}

	/**
	 * This method is used to draw the pause screen to the graphics object
	 */
	public void drawPauseScreen() {

		timer.stop();
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 100f));
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 5;

		// Draw Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);
		// Draw Text
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));

		// Resume Button
		y += gp.tileSize * 6;
        drawTextButton("RESUME GAME", y, 0, pauseCommandNum);;
		
		// Control Button
		y += gp.tileSize * 1.5;
        drawTextButton("CONTROLS", y, 1, pauseCommandNum);

		// Quit Button
		y += gp.tileSize * 1.5;
        drawTextButton("QUIT", y, 2, pauseCommandNum);

	}

	/**
	 * This method is used to draw the game over screen to the graphics object
	 */
	public void drawGameOverScreen() {
		timer.stop();
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

		// Control Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		String text = "YOU LOSE";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 5;

		// shadow
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 7, y + 7);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "Score: " + gp.bee.beeScore;
		x = getXforCenteredText(text) - gp.tileSize * 3;
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);

		// subtract start time and current time
		String time1 = minute + ":" + second;
		String time2 = origMinute + ":" + origSecond;
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(time1);
			date2 = format.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long difference = date2.getTime() - date1.getTime();
		long diffSeconds = difference / 1000 % 60;
		long diffMinutes = difference / (60 * 1000) % 60;

		text = "Time: " + dformat.format(diffMinutes) + ":" + dformat.format(diffSeconds);
		x = getXforCenteredText(text) + gp.tileSize * 3;
		g2.drawString(text, x, y);

		// Draw Quit Button
		y += gp.tileSize * 5;	
		drawTextButton("QUIT", y, 0, endCommandNum);
	}

	/**
	 * This method is used to draw the game win screen to the graphics object
	 */
	public void drawWinScreen() {
		timer.stop();
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

		// Control Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		String text = "YOU WIN";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 5;

		// shadow
		g2.setColor(Color.BLACK);
		g2.drawString(text, x + 7, y + 7);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "Score: " + gp.bee.beeScore;
		x = getXforCenteredText(text) - gp.tileSize * 3;
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);

		String time1 = minute + ":" + second;
		String time2 = origMinute + ":" + origSecond;
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(time1);
			date2 = format.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long difference = date2.getTime() - date1.getTime();
		long diffSeconds = difference / 1000 % 60;
		long diffMinutes = difference / (60 * 1000) % 60;

		text = "Time: " + dformat.format(diffMinutes) + ":" + dformat.format(diffSeconds);
		x = getXforCenteredText(text) + gp.tileSize * 3;
		g2.drawString(text, x, y);

		// Draw Quit Button
		y += gp.tileSize * 5;	
		drawTextButton("QUIT", y, 0, winCommandNum); 
	}

	/**
	 * This method is used to draw the controls screen to the graphics object
	 */
	public void drawControlState() {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

		// Control Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		String text = "Instructions";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;

		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 7, y + 7);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Controls
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Movement";
		x = getXforCenteredText(text);
		y = gp.tileSize * 5;

		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Control Image
		x = gp.tileSize * 4;
		y += gp.tileSize;
		g2.drawImage(arrowKeysImage, x, y, gp.tileSize * 3, gp.tileSize * 2, null);

		// Or text
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "OR";
		x = getXforCenteredText(text);
		y += gp.tileSize;

		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Control WASD Image
		x += gp.tileSize * 4;
		y -= gp.tileSize;
		g2.drawImage(wasdKeysImage, x, y, gp.tileSize * 3, gp.tileSize * 2, null);

		// Rules
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
		text = "Rules";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;

		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Rules
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25f));
		text = "Your goal is to reach the end of the maze by going through doors that";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		text = "may or may not be locked. While also avoiding the bee hunters.";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		text = "If you get caught by a bee hunter, you lose. If you reach ";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		text = "the end of the maze, you win. Press ESC to pause the game.";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
	}

	/**
	 * This method is used to get the x coordinate for given text text
	 * 
	 * @param text
	 * @return The center for the given text
	 */
	private int getXforCenteredText(String text) {
		int x;
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	/**
	 * This method is used as a countdown timer for the game
	 */
	private void countDownTimer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				ddSecond = dformat.format(second);
				ddMinute = dformat.format(minute);

				if (second == -1) {
					second = 59;
					minute--;
					ddSecond = dformat.format(second);
					ddMinute = dformat.format(minute);
				}

				if (minute == 0 && second == 0) {
					timer.stop();
					gp.gameState = GamePanel.gameOverState;
				}
			}
		});
	}


}
