
import java.util.HashSet;
import java.util.Set;

public class Graph {
    // OVERVIEW: classe che descrive un Grafo come insieme di Nodi

    // attributes
    private Set<Node> nodes = new HashSet<>();

    // methods
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        String res = "Graph: \n";
        for (Node node : nodes) {
            res += "\t" + node + "\n";
        }
        return res;
    }

}