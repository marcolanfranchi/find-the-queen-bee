package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import object.OBJ_ARROW;
import object.OBJ_HoneyDropReward;
import object.OBJ_WASD;

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
	String ddSecond, ddMinute;

	DecimalFormat dformat = new DecimalFormat("00");

	public UI(GamePanel gp) {
		this.gp = gp;
		second = 0;
		minute = 3;
		ddSecond = dformat.format(second);
		ddMinute = dformat.format(minute);
		countDownTimer();

		try {
			InputStream is = getClass().getResourceAsStream("../ui/font/retro_pixel.ttf");
			retroPixel = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OBJ_HoneyDropReward honey = new OBJ_HoneyDropReward();
		OBJ_ARROW arrowKeys = new OBJ_ARROW();
		OBJ_WASD wasdKeys = new OBJ_WASD();

		arrowKeysImage = arrowKeys.image;
		wasdKeysImage = wasdKeys.image;
		honeyImage = honey.image;
	}

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

	public void drawTitleScreen() {
		g2.setColor(new Color(244, 187, 68));
		g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

		// TItle Name
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

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 7;
		g2.drawString(text, x, y);

		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "CONTROLS";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

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
	}

	public void drawPauseScreen() {
		// Draw Pause Menu
		// Draw the Paused Text
		// Draw Button to Resume
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

		// Draw Resume Button
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "RESUME GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 8;
		g2.drawString(text, x, y);

		if (pauseCommandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		text = "CONTROLS";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (pauseCommandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		// Draw Quit Button
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (pauseCommandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

	public void drawGameOverScreen() {
		timer.start();
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

		// Draw buttons for the game over screen
		// Draw go to title screen
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "TITLE SCREEN";
		x = getXforCenteredText(text);
		y += gp.tileSize * 8;
		g2.drawString(text, x, y);

		if (endCommandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		// Draw Quit Button
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (endCommandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}

	}

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

		// Draw buttons for the game over screen
		// Draw go to title screen
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55f));
		text = "TITLE SCREEN";
		x = getXforCenteredText(text);
		y += gp.tileSize * 8;
		g2.drawString(text, x, y);

		if (winCommandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		// Draw Quit Button
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);

		if (winCommandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

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
		y = gp.tileSize * 7;

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
		y = gp.tileSize * 13;

		// shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x + 3, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);

		// Rules
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
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

	public int getXforCenteredText(String text) {
		int x;
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public void countDownTimer() {
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
