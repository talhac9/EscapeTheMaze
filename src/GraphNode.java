/*
Talha Chaudhry
tchaud@uwo.ca
251309928
December 7, 2023
 */

public class GraphNode {

    private int name;
    private boolean marked;

    // the constructor for the class. Creates a node with the given name. The
    //name of a node is an integer value between 0 and n − 1, where n is the number of nodes in the graph.
    //A node can be marked with a value that is either true or false using the following method.
    public GraphNode(int name) {
        this.name = name;
        marked = false;
    }

    // marks the node with the specified value
    public void mark(boolean mark) {
        this.marked = mark;
    }

    // returns the value with which the node has been marked.
    public boolean isMarked() {
        return this.marked;
    }

    // returns the name of the node.
    public int getName() {
        return this.name;
    }

}
