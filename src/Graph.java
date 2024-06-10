/*
Talha Chaudhry
tchaud@uwo.ca
251309928
December 7, 2023
 */


import java.util.Iterator;
import java.util.ArrayList;

public class Graph implements GraphADT {

    private ArrayList<GraphNode> nodes; // contains nodes of graph
    private GraphEdge[][] edges; // contains edges of graph

    // Constructor to initialize the graph with 'n-1' nodes and no edges using an adjacent matrix
    public Graph(int n) {
        nodes = new ArrayList<>();
        edges = new GraphEdge[n][n];

        // Create nodes and add them to the list
        for (int i = 0; i < n; i++) {
            nodes.add(new GraphNode(i));
        }
    }

    // Inserts an undirected edge between nodes 'nodeu' and 'nodev' with given 'type' and 'label'
    @Override
    public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
        // Check if nodes exist in the graph
        if (!nodes.contains(nodeu) || !nodes.contains(nodev)) throw new GraphException("Nodes do not exist in Graph.");

        // Create a new edge to add
        GraphEdge aToAdd = new GraphEdge(nodeu, nodev, type, label);
        GraphEdge bToAdd = new GraphEdge(nodev, nodeu, type, label);

        // Find indices of the nodes in the nodes list
        int uIndex = nodes.indexOf(nodeu);
        int vIndex = nodes.indexOf(nodev);

        // Check if the edge already exists
        if (edges[uIndex][vIndex] != null || edges[vIndex][uIndex] != null)
            throw new GraphException("Edge already exists.");

        // Add the edge to the adjacency matrix
        edges[uIndex][vIndex] = aToAdd;
        edges[vIndex][uIndex] = bToAdd;
    }

    // Returns the node at index 'u' in the graph
    @Override
    public GraphNode getNode(int u) throws GraphException {
        GraphNode node = nodes.get(u);
        if (node != null) return node;
        else throw new GraphException("Node does not exist in Graph.");
    }

    // Returns an iterator over the edges incident on node 'u'
    @Override
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
        // Check if the node exists in the graph
        if (!nodes.contains(u)) throw new GraphException("Node does not exist in Graph.");

        // Create a list to store incident edges
        ArrayList<GraphEdge> incident = new ArrayList<>();

        // Iterate over all nodes to find incident edges
        for (int i = 0; i < nodes.size(); i++) {
            if (edges[nodes.indexOf(u)][i] != null) incident.add(edges[nodes.indexOf(u)][i]);
        }

        return incident.iterator();
    }

    // Returns the edge between nodes 'u' and 'v'
    @Override
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        // Check if nodes exist in the graph
        if (!nodes.contains(u) || !nodes.contains(v)) throw new GraphException("Node(s) does not exist in Graph.");

        // Find indices of the nodes in the nodes list
        int uIndex = nodes.indexOf(u);
        int vIndex = nodes.indexOf(v);

        // Check if the edge exists
        if (edges[uIndex][vIndex] != null) return edges[uIndex][vIndex];
        else if (edges[vIndex][uIndex] != null) return edges[vIndex][uIndex];
        else throw new GraphException("Edge does not exist.");
    }

    // Checks if nodes 'u' and 'v' are adjacent
    @Override
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        // Check if nodes exist in the graph
        if (!nodes.contains(u) || !nodes.contains(v)) throw new GraphException("Node(s) does not exist in Graph.");

        // Check if there is an edge between nodes 'u' and 'v'
        return edges[nodes.indexOf(u)][nodes.indexOf(v)] != null;
    }
}