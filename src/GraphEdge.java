public class GraphEdge {

    private GraphNode firstEnd;
    private GraphNode secondEnd;
    private int type;
    private String label;

    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        firstEnd = u;
        secondEnd = v;
        this.type = type;
        this.label = label;
    }

    public GraphNode firstEndpoint() {
        return this.firstEnd;
    }

    public GraphNode secondEndpoint() {
        return this.secondEnd;
    }

    public int getType() {
        return this.type;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String newLabel) {
        this.label = newLabel;
    }

}
