
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    // OVERVIEW: classe che descrive il Nodo di un Grafo

    // attributes
    private String name;
    Map<Node, Integer> adjacentNodes = new HashMap<>(); // Nodi adiacenti a this e relativi costi
    private List<Node> shortestPath = new LinkedList<>(); // Lista del percorso più vicino a partire dal Source Node
    private Integer distance = Integer.MAX_VALUE; // costo a partire dal Source Node

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public String toString() {
        return "Node " + name + ": Distance " + distance;
    }

}
