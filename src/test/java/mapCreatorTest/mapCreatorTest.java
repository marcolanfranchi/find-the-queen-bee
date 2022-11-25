package mapCreatorTest;

import org.junit.jupiter.api.Test;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;

import ui.maps.MapCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mapCreatorTest {

	MapCreator mc;

	@BeforeEach
	void setUp() {
		mc = new MapCreator();
	}

	@Test
	void testCreateBasicGrid() {
		int[][] map = MapCreator.createBasicGrid(25, 25, 4);

		assertEquals(25, map.length);
		assertEquals(25, map[0].length);
	}

	@Test
	void testPlaceObject() {
		int[][] map = MapCreator.createBasicGrid(25, 25, 4);

		MapCreator.placeObjects(map, 36);

		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 3) {
					count++;
				}
			}
		}

		assertEquals(36, count);
	}

	@Test
	void testMainMethod() {
		MapCreator.main(null);

		assertEquals(25, MapCreator.createBasicGrid(25, 25, 4).length);
		assertEquals(25, MapCreator.createBasicGrid(25, 25, 4)[0].length);
	}

}
