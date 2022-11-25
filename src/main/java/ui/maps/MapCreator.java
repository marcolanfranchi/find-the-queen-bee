package ui.maps;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to create a map for the game.
 * When this class is run, it will create a map file
 * consiting of a 2D array of integers. Each integer
 * represents a tile type. With:
 * 1 = Wall
 * 2 = Door
 * 3 = Trap Tile
 * 
 * @author Satvik Garg
 */
public class MapCreator {
	public static void main(String[] args) {

		try {
			FileWriter myWriter = new FileWriter("./trap-tile6.txt");

			int x = 25;
			int y = 25;
			int[][] map = new int[x][y];

			map = createBasicGrid(x, y, 4);

			placeObjects(map, 36);

			// now write the map to the file
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					myWriter.write(map[i][j] + " ");
				}
				myWriter.write("\n");
			}

			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a basic grid of the given size with the given number of walls
	 * 
	 * @param sizeX         Size of the grid in the x direction
	 * @param sizeY         Size of the grid in the y direction
	 * @param numberOfRooms Number of rooms to create
	 * @return A 2D array of integers representing the map
	 */
	public static int[][] createBasicGrid(int sizeX, int sizeY, int numberOfRooms) {
		int walls = (int) Math.floor((double) sizeX / numberOfRooms);
		int[][] map = new int[sizeX][sizeY];

		// Add boundry walls
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1) {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}

		// Add walls at at intervals horizontally
		for (int i = 0; i < sizeX; i += walls) {
			for (int j = 0; j < sizeY; j++) {
				map[i][j] = 1;
			}
		}

		// Add walls at at intervals vertically
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j += walls) {
				map[i][j] = 1;
			}
		}

		// add doors to map in the middle of the walls on the x axis
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (i - 1 >= 0 && i + 1 < sizeX) {
					if (map[i][j] == 1 && map[i + 1][j] == 0 && map[i - 1][j] == 0 && j % walls == walls / 2) {
						map[i][j] = 2;
					}
				}

			}
		}

		// add doors to map in the middle of the walls on the y axis
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (j - 1 >= 0 && j + 1 < sizeY) {
					if (map[i][j] == 1 && map[i][j + 1] == 0 && map[i][j - 1] == 0 && i % walls == walls / 2) {
						map[i][j] = 2;
					}
				}

			}
		}

		return map;
	}

	/**
	 * Places traps randomly on the map
	 * 
	 * @param map The map to place objects on
	 * @param num The number of traps to place randomly on the map
	 */
	public static void placeObjects(int[][] map, int numberOfTraps) {
		int sizeX = map.length;
		int sizeY = map[0].length;

		for (int i = 0; i < numberOfTraps; i++) {
			int x = (int) (Math.random() * sizeX);
			int y = (int) (Math.random() * sizeY);

			if (map[x][y] == 0) {
				map[x][y] = 3;
			} else {
				i--;
			}
		}
	}
}
