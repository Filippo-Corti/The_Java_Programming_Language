
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

public class Dijkstra {

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        // EFFECTS: ritorna il Grafo i cui Nodi contengono i cammini minimi per
        // raggiunge il nodo source

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>(); // Nodi visitati e conclusi
        Set<Node> unsettledNodes = new HashSet<>(); // Nodi in corso di visita

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            System.out.println("Currently on " + currentNode);
            unsettledNodes.remove(currentNode);

            Node twoStepsAgo = null;
            if (currentNode.getShortestPath().size() >= 2) {
                twoStepsAgo = currentNode.getShortestPath().get(currentNode.getShortestPath().size() - 2);
            }

            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (twoStepsAgo != null && sameDirection(twoStepsAgo, adjacentNode)) {
                    System.out.println("NOP!" + twoStepsAgo + " - " + adjacentNode);
                    break;
                }

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;

    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        // EFFECTS: restituisce il nodo con distanza minore dalla sorgente

        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        // EFFECTS: aggiorna la distanza minima di evaluationNode da sourceNode, se
        // questa è minore di quella corrente.
        // e se non sto superando i 3 blocchi nella stessa direzione

        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }

    }

    private static boolean sameDirection(Node threeStepsAgo, Node now) {

        int deltaX = Math.abs(threeStepsAgo.getCol() - now.getCol());
        int deltaY = Math.abs(threeStepsAgo.getRow() - now.getRow());

        return (!(deltaX < 3 && deltaY < 3));
    }

}
