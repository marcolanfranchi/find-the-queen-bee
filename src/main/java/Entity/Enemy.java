package Entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;


public class Enemy extends Entity{
	


	public final int screenX;
	public final int screenY;

    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

	public Enemy(GamePanel gp) {
		super(gp);
		instantiateNodes();
        // allows the enemy to track down the Bee
        onPath = true;
		//this.keyHandler = kh;
		bounds = new Rectangle();
		bounds.x = getX();
		bounds.y = getY();
		bounds.width = this.width;
		bounds.height = this.height;
		this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		// System.out.println("Test1");
		// System.out.println(bounds.x = getX());
		// System.out.println(bounds.y = getY());
		// System.out.println(bounds.width);
		// System.out.println(bounds.height);


		worldX = gp.tileSize*10;
		worldY = gp.tileSize*10;
		speed = 48 / 2;
		direction = "up";
		//contact = false;
		getEnemyImage();
	}

	public void getEnemyImage() {
		try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-up.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-down.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }       
	}

	public void update(){
        this.checkCollision();
        int goalCol = gamePanel.bee.worldX; //(gamePanel.bee.worldX + gamePanel.bee.bounds.x) / gamePanel.tileSize;
        int goalRow = gamePanel.bee.worldY; //(gamePanel.bee.worldY + gamePanel.bee.bounds.y) / gamePanel.tileSize;
		searchPath(goalCol, goalRow);
    }


    //****************************************************
    //Codes below are still in process
    //****************************************************


	public void searchPath(int goalCol, int goalRow) {
		// getBounds();
		int startCol = worldX / gamePanel.tileSize;
		int startRow = worldY / gamePanel.tileSize;
		// System.out.println("Test 5");
		// System.out.println(startCol);
		// System.out.println(startRow);
		// System.out.println(goalCol);
		// System.out.println(goalRow);
		setNodes(startCol, startRow, goalCol, goalRow);
		if (search() == true) {
			int nextX = pathList.get(0).col * gamePanel.tileSize;
			int nextY = pathList.get(0).row * gamePanel.tileSize;
			int enLeftX = worldX + bounds.x;
			int enRightX = worldX + bounds.x + bounds.width;
			int enTopY = worldY + bounds.y;
			int enDownY = worldY + bounds.y + bounds.height;
			// System.out.println("Test 4");
			// System.out.println(nextX);
			// System.out.println(nextY);
			// System.out.println(enLeftX);
			// System.out.println(enRightX);
			// System.out.println(enTopY);
			// System.out.println(enDownY);

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
				direction = "up";
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
				direction = "down";
			} else if (enTopY >= nextY && enDownY < nextY + gamePanel.tileSize) {
				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}
			}
			// else if(enTopY > nextY && enLeftX > nextX){
			// direction = "up";
			// //checkCollision();
			// }else if(enTopY > nextY && enLeftX < nextX){
			// direction = "up";
			// //checkCollision();
			// }else if(enTopY < nextY && enLeftX > nextX){
			// direction = "down";
			// //checkCollision();
			// }else if(enTopY < nextY && enLeftX < nextX){
			// direction = "down";
			// //checkCollision();
			// }
		}
	}

	public void instantiateNodes() {
		node = new Node[gamePanel.worldWidth][gamePanel.worldHeight];
		// System.out.println("maxWorldCol: " + gamePanel.worldWidth);
		// System.out.println("maxWorldRow: " + gamePanel.worldHeight);
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

	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		// startNode = node[startCol][startRow];
		// currentNode = startNode;
		// goalNode = node[goalCol][goalRow];
		// openList.add(currentNode);
		resetNodes();
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		// System.out.println("goalNode.col: " + goalNode);
		openList.add(currentNode);
		int col = 0;
		int row = 0;
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

	public void getCost(Node node) {
		// G cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;

		// H cost
		// System.out.println("node.col: " + node.col);
		// System.out.println("goalNode.col: " + goalNode);
		xDistance = Math.abs(node.col - goalNode.col);
		// System.out.println("xDistance: " + xDistance);
		// System.out.println("node.row: " + node.row);
		// System.out.println("goalNode.row: " + goalNode.row);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;

		// F cost
		node.fCost = node.gCost + node.hCost;
	}

	public boolean search() {
		while (goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			currentNode.checked = true;
			openList.remove(currentNode);
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

	public void openNode(Node node) {
		if (node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}

	public void trackThePath() {
		Node current = goalNode;
		while (current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
        int screenX = worldX - gamePanel.bee.worldX + gamePanel.bee.screenX;
        int screenY = worldY - gamePanel.bee.worldY + gamePanel.bee.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.bee.worldX - gamePanel.bee.screenX &&
           worldX - gamePanel.tileSize < gamePanel.bee.worldX + gamePanel.bee.screenX &&
           worldY + gamePanel.tileSize > gamePanel.bee.worldY - gamePanel.bee.screenY &&
           worldY - gamePanel.tileSize < gamePanel.bee.worldY + gamePanel.bee.screenY){
            if (direction == "up") {
                image = up1; 
            } else if (direction == "down") {
                image = down1; 
            } else if (direction == "left") {
                image = left1; 
            } else if (direction == "right") {
                image = right1; 
            }
            g2.drawImage(image, screenX, screenY, width, height, null);
        }
    }
}
