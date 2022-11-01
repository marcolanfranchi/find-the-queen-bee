package Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager<BufferedImage> {
	GamePanel gp;
	Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];

		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("../ui/maps/trap-tile.txt");
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("../ui/images/Tile2.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("../ui/images/Wall-2.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("../ui/images/Door-2.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("../ui/images/TrapTile2.png"));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void addImage(BufferedImage i1, BufferedImage i2) {

	// }

	public void loadMap(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();

				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}

			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	} 

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.bee.worldX + gp.bee.screenX;
			int screenY = worldY - gp.bee.worldY + gp.bee.screenY;

			// Optimization (Only draw tiles that are visibile)
			if (worldX + gp.tileSize > gp.bee.worldX - gp.bee.screenX &&
					worldX - gp.tileSize < gp.bee.worldX + gp.bee.screenX &&
					worldY + gp.tileSize > gp.bee.worldY - gp.bee.screenY &&
					worldY - gp.tileSize < gp.bee.worldY + gp.bee.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
