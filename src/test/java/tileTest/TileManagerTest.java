package tileTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GamePanel;
import tile.TileManager;
import java.awt.Graphics2D;

public class TileManagerTest {
	private TileManager<BufferedImage> tileManager;
	GamePanel gp;

	@BeforeEach
	public void setUp() throws Exception {
		// Define new game panel
		gp = new GamePanel();
		tileManager = new TileManager<BufferedImage>(gp);
	}

	@Test
	public void testGetTileManager() {
		assertNotNull(tileManager.mapTileNum);
	}

	@Test
	public void testSetMap() {
		tileManager.setMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
		assertNotNull(tileManager.mapTileNum);
	}

	@Test
	public void testGetTile() {
		for (int i = 0; i < tileManager.getTile().length; i++) {
			System.out.println(tileManager.getTile()[i]);
			assertNotNull(tileManager.getTile()[i]);
		}
	}

	@Test
	public void testMapTileNum() {
		tileManager.loadMap("./src/main/java/ui/maps/txt-maps/trap-tile.txt");
		for (int i = 0; i < tileManager.mapTileNum.length; i++) {
			for (int j = 0; j < tileManager.mapTileNum[i].length; j++) {
				assertNotNull(tileManager.mapTileNum[i][j]);
			}
		}
	}

	@Test
	public void testMapAssignNumbers() throws IOException {
		String mapPath = "./src/main/java/ui/maps/txt-maps/trap-tile.txt";
		tileManager.loadMap(mapPath);

		InputStream is = Files.newInputStream(Paths.get(mapPath));

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		for (int i = 0; i < tileManager.mapTileNum.length; i++) {
			String line = br.readLine();
			for (int j = 0; j < tileManager.mapTileNum[i].length; j++) {
				String numbers[] = line.split(" ");
				int num = Integer.parseInt(numbers[j]);
				assertEquals(num, tileManager.mapTileNum[j][i]);
			}
		}
		br.close();
	}

	@Test
	public void testDrawMethod() {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();

		tileManager.setMap("src/main/java/ui/maps/txt-maps/trap-tile.txt");

		tileManager.draw(g2);
	}
}
