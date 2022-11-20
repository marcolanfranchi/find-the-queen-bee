package tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;

import main.GamePanel;

/**
 * This class is a manager for all the tiles in the game. It is used to load the
 * tiles from the file and draw them to the screen using the file created
 * from the MapCreator class.
 * 
 * @author Satvik Garg
 */
public class TileManager<BufferedImage> {
	GamePanel gp;
	Tile[] tile;
	public int mapTileNum[][];

	/**
	 * Constructor for the TileManager class
	 * It loads the tile images and the map file
	 * 
	 * @param gp The GamePanel object
	 */
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[4];

		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();

		Random rand = new Random();
		File[] files = new File("./src/main/java/ui/maps/txt-maps").listFiles();
		File file = files[rand.nextInt(files.length)];

		loadMap(file.getPath());
	}

	/**
	 * Loads the tile images from the file
	 */
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

	public void setMap(String mapPath) {
		loadMap(mapPath);
	}

	/**
	 * Used to test Tile array
	 * 
	 * @return The tile array
	 */
	public Tile[] getTile() {
		return tile;
	}

	/**
	 * Loads the map from the file and stores it in the mapTileNum array
	 * 
	 * @param filename The name of the file to load
	 */
	public void loadMap(String mapPath) {
		try {
			InputStream is = Files.newInputStream(Paths.get(mapPath));
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
			e.printStackTrace();
		}
	} 

	/**
	 * Draws the tiles to the screen that are only in the view of the player
	 * 
	 * @param g The Graphics2D object
	 */
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
