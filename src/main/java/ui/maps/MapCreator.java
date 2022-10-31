package ui.maps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapCreator {
	public static void main(String[] args) {
		try {
			File myObj = new File("filename.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			// create map with 1 as the grid lines and 0 as the empty space
			// the map is 20x20
			int x = 60;
			int y = 60;
			int[][] map = new int[x][y];

			map = createBasicGrid(x, y, 5);

			placeObjects(map, 100, 40, 400);

			// now write the map to the file
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					myWriter.write(map[i][j] + " ");
				}
				myWriter.write("\n");
			}

			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private static int[][] createBasicGrid(int sizeX, int sizeY, int numberOfRooms) throws IOException {
		System.out.println("sizeX/numberOfRooms: " + (double) sizeX / numberOfRooms);
		int walls = (int) Math.floor((double) sizeX / numberOfRooms);
		System.out.println("walls: " + walls);
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
				// if (i % walls == 0 || j % walls == 0) {
				// map[i][j] = 1;
				// }
				map[i][j] = 1;
			}
		}

		// Add walls at at intervals vertically
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j += walls) {
				// if (i % walls == 0 || j % walls == 0) {
				// map[i][j] = 1;
				// }
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

	private static void placeObjects(int[][] map, int numberOfTraps, int numberOfHoney, int numberOfPots) {
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

		// for (int i = 0; i < numberOfHoney; i++) {
		// int x = (int) (Math.random() * sizeX);
		// int y = (int) (Math.random() * sizeY);

		// if (map[x][y] == 0) {
		// map[x][y] = 4;
		// } else {
		// i--;
		// }
		// }

		// for (int i = 0; i < numberOfPots; i++) {
		// int x = (int) (Math.random() * sizeX);
		// int y = (int) (Math.random() * sizeY);

		// if (map[x][y] == 0) {
		// map[x][y] = 5;
		// } else {
		// i--;
		// }
		// }
	}
}
