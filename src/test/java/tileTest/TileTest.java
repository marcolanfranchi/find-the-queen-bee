package tileTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GamePanel;
import tile.TileManager;

public class TileTest {
	private TileManager<BufferedImage> tileManager;

	@BeforeEach
	public void setUp() throws Exception {
		// Define new game panel
		GamePanel gp = new GamePanel();
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

}
