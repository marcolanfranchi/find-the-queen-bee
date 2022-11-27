package entity;

/**
 * Initialize variables for Node that will be used in A algorithm for Path Finding
 * 
 * @author Jinshuo Zhang
 */
public class Node {
    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
}
