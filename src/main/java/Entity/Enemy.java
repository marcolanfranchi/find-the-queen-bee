package Entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
	int randomX;
	int randomY;
	Random num = new Random();

	public Enemy(GamePanel gp) {
		super(gp);
		instantiateNodes();
        onPath = true;
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
		worldX = randomX * gp.tileSize;
		worldY = randomY * gp.tileSize;
		speed = 48 / 6;
		direction = "up";
		getEnemyImage();
	}

	public void getEnemyImage() {
		try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-up.png"));
            //up2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-up.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-down.png"));
            //down2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-down.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-left.png"));
            //left2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-right.png"));
            //right2 = ImageIO.read(getClass().getResourceAsStream("../ui/images/BeeKeeper-right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }       
	}

	public void update(){
	
		this.checkWallCollision();
		int goalCol = gamePanel.bee.worldX / gamePanel.tileSize;
		int goalRow = gamePanel.bee.worldY / gamePanel.tileSize;
		searchPath(goalCol, goalRow);

		// this.enemyCheckCollision();
		// actionLockCounter++;
		// if(actionLockCounter == 10){
		// 	//worldX += speed;
		// 	//direction = "right";
		// 	// Random random = new Random();
		// 	// int i = random.nextInt(100) + 1;
		// 	// if(i <= 25){
		// 	// 	worldY -= speed;
		// 	// 	direction = "up";
		// 	// }
		// 	// if(i > 25 && i <= 50){
		// 	// 	worldY += speed;
		// 	// 	direction = "down";
		// 	// }
		// 	// if(i > 50 && i <= 75){
		// 	// 	worldX -= speed;
		// 	// 	direction = "left";
		// 	// }
		// 	// if(i > 75 && i <=100){
		// 	// 	worldX += speed;
		// 	// 	direction = "right";
		// 	// }
		// 	// spriteCounter ++;
		// 	if (spriteCounter > 2) {
		// 		if (spriteNum == 1) {
		// 			spriteNum = 2;
		// 		} else if (spriteNum == 2) {
		// 			spriteNum = 1;
		// 		}
		// 		spriteCounter = 0;
		// 	}
		// 	actionLockCounter = 0;

		// }
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

		//System.out.println(search());
		if (search() == true) {
			//System.out.println(search());
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
				worldY -= speed;
				direction = "up";
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
				worldY += speed;
				direction = "down";
			} else if (enTopY >= nextY && enDownY < nextY + gamePanel.tileSize) {
				if (enLeftX > nextX) {
					worldX -= speed;
					direction = "left";
				}
				if (enLeftX < nextX) {
					worldX += speed;
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX){
				worldY -= speed;
				direction = "up";
				enemyCheckCollision();
				if(enemyCollision == true){
					worldX -= speed;
					direction = "left";
				}
			}else if(enTopY > nextY && enLeftX < nextX){
				direction = "up";
				enemyCheckCollision();
				if(enemyCollision == true){
					worldX += speed;
					direction = "right";
				}
			}else if(enTopY < nextY && enLeftX > nextX){
				direction = "down";
				enemyCheckCollision();
				if(enemyCollision == true){
					worldX -= speed;
					direction = "left";
				}
			}else if(enTopY < nextY && enLeftX < nextX){
				direction = "down";
				enemyCheckCollision();
				if(enemyCollision == true){
					worldX += speed;
					direction = "right";
				}
			}
		}
	}

	public void instantiateNodes() {
		node = new Node[gamePanel.worldWidth][gamePanel.worldHeight];
		//System.out.println("maxWorldCol: " + gamePanel.worldWidth);
		//System.out.println("maxWorldRow: " + gamePanel.worldHeight);
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
		// System.out.println("startNode.col: " + startNode.col);
		// System.out.println("startNode.row: " + startNode.row);
		// System.out.println("goalNode.col: " + goalNode.col);
		// System.out.println("goalNode.col: " + goalNode.row);
		
		openList.add(currentNode);
		int col = 0;
		int row = 0;
		while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
			//System.out.println("mapTileNum: " + gamePanel.tileM.mapTileNum[col][row]);
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
		// System.out.println("gCost: " + node.gCost);
		// System.out.println("hCost: " + node.hCost);
		// System.out.println("fCost: " + node.fCost);
	}

	public boolean search() {
		// System.out.println("goalReached: " + goalReached);
		// System.out.println("step: " + step);
		// System.out.println("currentNode.col: " + currentNode.col);
		// System.out.println("currentNode.row: " + currentNode.row);

		while (goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			currentNode.checked = true;
			//System.out.println("before openList.size: " + openList.size());
			openList.remove(currentNode);
			//System.out.println("after openList.size: " + openList.size());
			if (row - 1 >= 0) {
				openNode(node[col][row - 1]);
				//System.out.println("Left N: " + );
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
			//openList.remove(currentNode);
		}
		return goalReached;
	}

	public void openNode(Node node) {
		// System.out.println("If Open: " + node.open);
		// System.out.println("If Checked: " + node.open);
		// System.out.println("If Solid: " + node.open);
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
