package application;

/**
 * Created by nicholas on 7/25/17.
 */
public class GraphNode{
    Edge edge = null;
    int id;

    public GraphNode(int id){
        this.id = id;
    }

    public void setEdge(Edge edge){
        this.edge = edge;
    }

    public Edge getEdge(){
        return this.edge;
    }
}
