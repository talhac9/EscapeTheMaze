/*
Talha Chaudhry
tchaud@uwo.ca
251309928
December 7, 2023
 */

import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

public class Maze {

    private Graph maze; // contains the labyrinth
    private GraphNode start; // stores the start of the labyrinth
    private GraphNode exit; // stores the exit of the labyrinth
    private int keys; // contains the amount of keys in the labyrinth;

    private Stack<GraphNode> stack = new Stack<>(); // contains the stack that is used to find the path out of labyrinth

    /*
    A constructor that reads the input file and builds the graph representing the maze. If the input file does not
    exist, or the format of the input file is incorrect this method should throw a MazeException.
    Read below to learn about the format of the input file.
    The constructor method uses the following rules to construct a labyrinth using nodes and edges:

    - â€™sâ€™: entrance to the maze
    - â€™xâ€™: exit of the maze
    -  â€™oâ€™: room
    - â€™câ€™: corridor
    -  â€™wâ€™: wall
    - â€™0â€™, â€™1â€™, ... â€™9â€™: door that to be opened requires the number of coins specified by the digit; so â€™0â€™
    represents a door that does not need any coins to be opened, â€™1â€™ represents a door that requires 1 coin,
    and so on.
     */
    public Maze(String inputFile) throws MazeException {

        try {

            FileReader fr  = new FileReader(inputFile);
            Scanner inFile = new Scanner(fr);


            inFile.nextLine(); // skip scale factor
            int width = Integer.parseInt(inFile.nextLine()); // get width
            int length = Integer.parseInt(inFile.nextLine()); // get length
            keys = Integer.parseInt(inFile.nextLine()); // get num of keys

            maze = new Graph(length*width); // create a graph


            int counter = 0; // use a counter to keep track of horizontal nodes
            int vCounter = 0; // use a counter to keep track of vertical nodes

            while (inFile.hasNext()) {

                String horizontal = inFile.nextLine();
                String vertical = null;

                if (inFile.hasNext()) vertical = inFile.nextLine();

                if (counter > 0) counter += 1; // update counter for next node after first read
                if (vCounter > 0) vCounter += 1;

                for (int i = 0; i < width-1; i++) {

                    if (horizontal.charAt(0) == 's') start = maze.getNode(counter);

                    if (horizontal.charAt(0) == 'x' || horizontal.charAt(1) == 'x') exit = maze.getNode(counter);

                    if (Character.isDigit(horizontal.charAt(1))) {
                        GraphNode u = maze.getNode(counter);
                        GraphNode v = maze.getNode(counter + 1);
                        maze.insertEdge(u, v, Character.getNumericValue(horizontal.charAt(1)), "door");
                        counter++;
                    }

                    else if (horizontal.charAt(1) == 'w') {
                        counter++;
                    }

                    else if (horizontal.charAt(1) == 'c') {
                        GraphNode u = maze.getNode(counter);
                        GraphNode v = maze.getNode(counter + 1);
                        maze.insertEdge(u, v, 0, "corridor");
                        counter++;
                    }

                    if (vertical != null) {

                        if (Character.isDigit(vertical.charAt(0))) {
                            GraphNode u = maze.getNode(vCounter);
                            GraphNode v = maze.getNode(vCounter + width);
                            maze.insertEdge(u,v,Character.getNumericValue(vertical.charAt(0)), "door");
                            vCounter++;
                        }


                        else if (vertical.charAt(0) == 'c') {
                            GraphNode u = maze.getNode(vCounter);
                            GraphNode v = maze.getNode(vCounter + width);
                            maze.insertEdge(u, v, 0, "corridor");
                            vCounter++;
                        }

                        if (i == width-2) { // need an extra iteration for vertical substring

                            vertical = vertical.substring(2);

                            if (Character.isDigit(vertical.charAt(0))) {
                                GraphNode u = maze.getNode(vCounter);
                                GraphNode v = maze.getNode(vCounter + width);
                                maze.insertEdge(u,v,Character.getNumericValue(vertical.charAt(0)), "door");
                            }

                            else if (vertical.charAt(0) == 'c') {
                                GraphNode u = maze.getNode(vCounter);
                                GraphNode v = maze.getNode(vCounter + width);
                                maze.insertEdge(u, v, 0, "corridor");
                            }
                        }
                    }

                    horizontal = horizontal.substring(2);
                    if (vertical != null && vertical.length() > 1) vertical = vertical.substring(2);
                }
            }
        } catch (Exception e) {
            throw new MazeException(e.getMessage());
        }

    }
    /*
        This method will return the graph stored in the instance variable maze
     */
    public Graph getGraph() throws MazeException {
        if (maze != null) return maze;
        else throw new MazeException("Maze does not exist.");
    }
    /*
    This method will be used to find a path from the start of the maze to the exit.
     */
    private boolean path(GraphNode u, GraphNode d) throws GraphException {

        stack.push(u); // add node to stack
        if (u == d) return true;
        Iterator<GraphEdge> incident = maze.incidentEdges(u);
        u.mark(true);

        while (incident.hasNext()) {
            GraphEdge s = incident.next();

            if ((!s.secondEndpoint().isMarked())) {

                keys -= s.getType(); // check if total number of keys is used
                if (keys >= 0) {
                    if (path(s.secondEndpoint(), d)) return true; // traverse through graph
                }
                keys += s.getType(); // add keys used when backtracking

            }
        }
        GraphNode pop = stack.pop();
        pop.mark(false);  // mark nodes as false when going back through stack
        return false;
    }
    /*
        This method returns a java Iterator containing the nodes of the path from the entrance to
        the exit of the maze, if such a path exists. If the path does not exist, this method returns the value null.

     */
    public Iterator solve() {

        try {
            boolean solved = path(start, exit);
            if (solved) {
                return stack.iterator();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

}
