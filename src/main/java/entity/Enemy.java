package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import util.Point;

/**
	 * This class represents an Enemy character in the game.
	 * 
	 * @author Jinshuo Zhang 
	 */
public class Enemy extends Entity{

	public final Point screen;

    int step = 0;
	int randomX;
	int randomY;
	int tempGCost = 0;
	Random num = new Random();
	boolean goalReached = false;
	Node[][] node;
	Node startNode, goalNode, currentNode;
	public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    
	/**
	 * Creates number's enemies which is born randomly on the map except the first room.
	 * Setting its speed, starting direction, and load images.
	 * Initialize Nodes for A* path finding algorithm of enemies.
	 * @param gp a GamePanel which will contain this Enemy.
	 */
	public Enemy(GamePanel gp) {
		super(gp);
		instantiateNodes();
        onPath = true;
		bounds = new Rectangle();
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = this.width;
		bounds.height = this.height;
		this.screen = new Point(0, 0);
		this.screen.setLocation(gp.screenWidth / 2 - (gp.tileSize / 2), gp.screenHeight / 2 - (gp.tileSize / 2));

		randomX = num.nextInt(25);
		randomY = num.nextInt(25);
		if (randomX % 6 == 0) {
			randomX++;
			if (randomX == 25) {
				randomX -= 2;
			}
		}
		if (randomY % 6 == 0) {
			randomY++;
			if (randomY == 25) {
				randomY -= 2;
			}
		}
		setRandomStartPoint();
		speed = gamePanel.tileSize / 8;
		direction = Direction.UP;
		setImages();

	}

	/**
	 * Load Enemy's images for differnt directions and apply these images
	 * when its moving to different directions.
	 */
	public void setImages() {
		try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-up.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-down.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }       
	}

	/**
	 * Set X and Y positions where enemies will be born randomly at the beginning of the game.
	 * If the X and Y positions are in the first room where the Bee is born,
	 * then this function will retake a new X and Y positions for enemies until the postion is not in the first room. 
	 */
	public void setRandomStartPoint() {
		for (int i = 0; i < 1; i++) {
			Point random = new Point(getRandom(0, gamePanel.tileM.mapTileNum.length - 1),
					getRandom(0, gamePanel.tileM.mapTileNum[0].length - 1));

			if (gamePanel.tileM.mapTileNum[random.getX()][random.getY()] == 1
					|| gamePanel.tileM.mapTileNum[random.getX()][random.getY()] == 2
					|| inFirstRoom(random.getX(), random.getY())) {
				i--;
				continue;
			}
			world.setX(random.getX() * gamePanel.tileSize);
			world.setY(random.getY() * gamePanel.tileSize);
		}
	}

	/**
	 * Update method for enemies which is called in GamePanel's update method every
     * frame per second. This method calls methods to check if enemies are colliding
     * with a wall and update Bee's latest location which will be setted as the 
	 * goalposition that enemies will reach.
	 */
	public void update(){
		this.checkWallCollision();

		// Bee's current position
		int goalCol = gamePanel.bee.world.getX() / gamePanel.tileSize;
		int goalRow = gamePanel.bee.world.getY() / gamePanel.tileSize;

		// A* Path Finding Algorithm
		searchPath(goalCol, goalRow);
    }

	/**
	 * Find the shortest path between enemies and the Bee, and avoid walls while enemies
	 * are moving to the Bee. This algorithm will chase the Bee until one of the enemies
	 * reaches the Bee.
	 * @param goalCol	Bee's latest X position
	 * @param goalRow	Bee's latest Y position
	 */
	public void searchPath(int goalCol, int goalRow) {

		// Enemies' starting and current postion 
		int startCol = world.getX() / gamePanel.tileSize;
		int startRow = world.getY() / gamePanel.tileSize;

		// Divide the entire map into multiple samll areas which are represented by Nodes
		setNodes(startCol, startRow, goalCol, goalRow);

		// Check if the shortest path between enemies and the Bee is setted
		if (search() == true) {
			int nextX = pathList.get(0).col * gamePanel.tileSize;
			int nextY = pathList.get(0).row * gamePanel.tileSize;
			int enLeftX = world.getX() + bounds.x;
			int enRightX = world.getX() + bounds.x + bounds.width;
			int enTopY = world.getY() + bounds.y;
			int enDownY = world.getY() + bounds.y + bounds.height;

			// Enemies' moving method which contains directions for different situations
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
				world.setY(world.getY() - speed);
				direction = Direction.UP;
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
				world.setY(world.getY() + speed);
				direction = Direction.DOWN;
			} else if (enTopY >= nextY && enDownY < nextY + gamePanel.tileSize) {
				if (enLeftX > nextX) {
					world.setX(world.getX() - speed);
					direction = Direction.LEFT;
				}
				if (enLeftX < nextX) {
					world.setX(world.getX() + speed);
					direction = Direction.RIGHT;
				}
			}
			else if(enTopY > nextY && enLeftX > nextX){
				world.setY(world.getY() - speed);
				direction = Direction.UP;
				enemyCheckCollision();
				if(enemyCollision == true){
					world.setX(world.getX() - speed);
					direction = Direction.LEFT;
				}
			}else if(enTopY > nextY && enLeftX < nextX){
				direction = Direction.UP;
				enemyCheckCollision();
				if(enemyCollision == true){
					world.setX(world.getX() + speed);
					direction = Direction.RIGHT;
				}
			}else if(enTopY < nextY && enLeftX > nextX){
				direction = Direction.DOWN;
				enemyCheckCollision();
				if(enemyCollision == true){
					world.setX(world.getX() - speed);
					direction = Direction.LEFT;
				}
			}else if(enTopY < nextY && enLeftX < nextX){
				direction = Direction.DOWN;
				enemyCheckCollision();
				if(enemyCollision == true){
					world.setX(world.getX() + speed);
					direction = Direction.RIGHT;
				}
			}
		}
	}

	/**
     * For all 4 directions, checks if the tile 1 position away from this Entity
     * is a Wall Tile. If it is, sets the enemyCollision value to True, and the
     * direction to null in order to stop or help enemies to choose directions.
     */
    public void enemyCheckCollision() {

        if (this.tileNumUp() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = Direction.UP;
        }

        if (this.tileNumDown() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = Direction.DOWN;
        }

        if (this.tileNumLeft() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = Direction.LEFT;
        }

        if (this.tileNumRight() == 1) {
            enemyCollision = true;
            direction = null;
        } else {
            direction = Direction.RIGHT;
        }
	}

	/**
	 * Divide the entire map into multiple nodes and initialize these nodes
	 */
	public void instantiateNodes() {
		node = new Node[gamePanel.worldWidth][gamePanel.worldHeight];
		int col = 0;
		int row = 0;
		while (col < gamePanel.worldWidth && row < gamePanel.worldHeight) {
			node[col][row] = new Node(col, row);
			col++;
			if (col == gamePanel.worldWidth) {
				col = 0;
				row++;
			}
		}
	}

	/**
	 * For every update() function called, whether the Bee moves or not,
	 * the latest position of the Bee is update, which means A* Path Finding
	 * algorithm need to be done again. But before redo the algorithm, nodes
	 * which contain previous positions for both the Bee and enemies need to
	 * be emptied in order to contain the latest nodes.
	 */
	public void resetNodes() {
		int col = 0;
		int row = 0;
		while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if (col == gamePanel.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}

	/**
	 * Update latest position for both the Bee and enemies and find nodes that
	 * cannot path which means to avoid walls on the final path.
	 * @param startCol	Enemy's current X position
	 * @param startRow	Enemy's current Y position
	 * @param goalCol	Bee's current X position
	 * @param goalRow	Bee's current Y position
	 */
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		resetNodes();
		startNode = node[startCol][startRow];

		// Current enemies' position 
		currentNode = startNode;

		// Bee's current position
		goalNode = node[goalCol][goalRow];
		
		openList.add(currentNode);
		int col = 0;
		int row = 0;

		// Find the solide nodes which cannot set as a path between the enemy and the Bee
		while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
			if (gamePanel.tileM.mapTileNum[col][row] == 1) {
				node[col][row].solid = true;
			}
			getCost(node[col][row]);
			col++;
			if (col == gamePanel.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	/**
	 * When finding the path between the enemy and the Bee, each node
	 * contains two distances, one is the distance between the current node and 
	 * the enemy, another one is the distance between the current node and the 
	 * enemy. Also there is a number which is the sum of the two distances.
	 * This will label all nodes, labels will be used later.
	 * @param node
	 */
	public void getCost(Node node) {
		// G cost: Sum of X distance between the enemy and the current check node and 
		//			Y distance between the enmey and the current check node
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;

		// H cost: Sum of X distance between the Bee and the current check node and 
		//			Y distance between the Be and the current check node
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;

		// F cost: Sun of G cost and H Cost
		node.fCost = node.gCost + node.hCost;
	}

	/**
	 * Check four nodes on four directions of the node which is currently checking now.
	 * And it will return a path if the node which the Bee is currently staying is checked.
	 * @return	It returns a boolean value to check if the enemy reaches the Bee. If not
	 * 			it will do the loop again to check the next node until it checked the node
	 * 			which is the current position of the Bee.
	 */
	public boolean search() {
		while (goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			currentNode.checked = true;
			openList.remove(currentNode);

			// Check four nodes on four directions of the current node.
			if (row - 1 >= 0) {
				openNode(node[col][row - 1]);
			}
			if (col - 1 >= 0) {
				openNode(node[col - 1][row]);
			}
			if (row + 1 < gamePanel.maxWorldRow) {
				openNode(node[col][row + 1]);
			}
			if (col + 1 < gamePanel.maxWorldCol) {
				openNode(node[col + 1][row]);
			}
			int bestNodeIndex = 0;
			int bestNodefCost = 999;

			// This will choose the next node from the four nodes on four directions
			// of the current node by using fCost, gCost, and hCost previously.
			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				} else if (openList.get(i).fCost == bestNodefCost) {
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			if (openList.size() == 0) {
				break;
			}
			currentNode = openList.get(bestNodeIndex);
			if (currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
	}

	/**
	 * Check the node on one of the four directions that is passed by previous function.
	 * If the node which is checking is 'new' node and can be set as a path (not a wall)
	 * this will be labeled as alternative node and added to openList.
	 * @param node 	Node which is checking now for the path.
	 */
	public void openNode(Node node) {
		if (node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}

	/**
	 * If if the node which the Bee is currently staying is checked, then the shortest path between
	 * the Bee and the Enemy will be added to the pathList and apply to enemy in order to chase
	 * the Bee.
	 */
	public void trackThePath() {
		Node current = goalNode;
		while (current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}

	/**
	 * Draws enemies on the given Graphics2D object where the X and Y position 
     * is the game map's starting point and the image corresponds to enemies'
     * current direction.
	 * @param g2	Graphics2D object used to contain all images and text for the UI.
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = world.getX() - gamePanel.bee.world.getX() + gamePanel.bee.screen.getX();
		int screenY = world.getY() - gamePanel.bee.world.getY() + gamePanel.bee.screen.getY();
		if (world.getX() + gamePanel.tileSize > gamePanel.bee.world.getX() - gamePanel.bee.screen.getX() &&
				world.getX() - gamePanel.tileSize < gamePanel.bee.world.getX() + gamePanel.bee.screen.getX() &&
				world.getY() + gamePanel.tileSize > gamePanel.bee.world.getY() - gamePanel.bee.screen.getY() &&
				world.getY() - gamePanel.tileSize < gamePanel.bee.world.getY() + gamePanel.bee.screen.getY()) {
            if (direction == Direction.UP) {
                image = up1; 
            } else if (direction == Direction.DOWN) {
                image = down1; 
            } else if (direction == Direction.LEFT) {
                image = left1; 
            } else if (direction == Direction.RIGHT) {
                image = right1; 
            }
            g2.drawImage(image, screenX, screenY, width, height, null);
        }
    }

	/**
	 * Get a random number in the range which will be used on set up the start point of enemies.
	 * @param min	the min value of the range
	 * @param max	the max value of the range
	 * @return		return the final value
	 */
	public static int getRandom(int min, int max) {
        Random num = new Random();
        return num.nextInt(max - min + 1);
    }
}
