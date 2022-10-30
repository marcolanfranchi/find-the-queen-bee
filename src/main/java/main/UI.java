package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.OBJ_Honey;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font retroPixel;
	BufferedImage honeyImage;

	public int commandNum = 0;

	public UI(GamePanel gp) {
		this.gp = gp;

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

		OBJ_Honey honey = new OBJ_Honey();
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
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40f));
		g2.setColor(Color.WHITE);
		g2.drawImage(honeyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize - 10, gp.tileSize - 10, null);
		// The 100 will be replaced with the current number of points in the bee class
		g2.drawString("x " + 100, 80, 55);
	}

	public void drawPauseScreen() {
		// Draw Pause Menu
		// Draw the Paused Text
		// Draw Button to Resume
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
		g2.drawString(text, x, y);

	}

	public int getXforCenteredText(String text) {
		int x;
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
