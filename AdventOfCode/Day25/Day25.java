import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map.Entry;

public class Day25 {

    static class Node {

        String label;
        ArrayList<Node> adjacentNodes = new ArrayList<>();

        public Node(String label) {
            this.label = label;
        }

        public void connectTo(Node node) {
            if (!adjacentNodes.contains(node) && !node.equals(this))
                adjacentNodes.add(node);
        }

        @Override
        public String toString() {
            String res = "Node " + label + ": ";
            for (Node node : adjacentNodes) {
                res += node.label + " - ";
            }
            return res + "\n";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (label == null) {
                if (other.label != null)
                    return false;
            } else if (!label.equals(other.label))
                return false;
            return true;
        }

    }

    static class Graph implements Cloneable {
        HashMap<String, HashSet<String>> nodes = new HashMap<>();

        public void add(String node) {
            if (!nodes.containsKey(node))
                nodes.put(node, new HashSet<>());
        }

        public void addConnection(String node, String connection) {
            nodes.get(node).add(connection);
            nodes.get(connection).add(node);
        }

        @Override
        public String toString() {
            return "Graph: " + nodes;
        }

        @Override
        protected Graph clone() {
            Graph newGraph = new Graph();
            for (Entry<String, HashSet<String>> entry : this.nodes.entrySet()) {
                newGraph.add(entry.getKey());
                for (String n : entry.getValue()) {
                    newGraph.add(n);
                    newGraph.addConnection(entry.getKey(), n);
                }
            }
            return newGraph;
        }

    }

    public static Graph parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Graph graph = new Graph();

        while (s.hasNextLine()) {
            String in = s.nextLine().replace(":", "");
            String[] labels = in.split(" ");
            for (String label : labels) {
                graph.add(label);
            }
            for (String label : labels) {
                if (label.equals(labels[0]))
                    continue;
                graph.addConnection(labels[0], label);
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        Graph graph = parseInput();
        System.out.println(graph);
        int mincut;
        Graph copy = null;
        do {
            copy = graph.clone();
            mincut = collapseGraphWithKrager(copy, graph);
        } while (mincut > 3);
        int s1 = size(copy.nodes.keySet().stream().findFirst().get().split("\\+"));
        int s2 = graph.nodes.size() - s1;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 * s2);
    }

    private static int size(String[] split) {
        int c = 0;
        for (String string : split) {
            c++;
        }
        return c;
    }

    private static int collapseGraphWithKrager(Graph collapsableGraph, Graph baseGraph) {
        // Collapse
        while (collapsableGraph.nodes.size() > 2) {
            contractRandom(collapsableGraph);
        }
        // Count Remaining Edges
        int mincut = 0;

        System.out.println(collapsableGraph);

        String[] subGraph1 =  collapsableGraph.nodes.values().stream().findFirst().get().stream().findFirst().get().split("\\+");

        for (String string : subGraph1) {
            for (String string2 : baseGraph.nodes.get(string)) {
                if (!contains(subGraph1, string2)) {
                    mincut++;
                }
            }
        }

        return mincut;
    }

    private static boolean contains(String[] subGraph1, String string2) {
        for (String string : subGraph1) {
            if (string.equals(string2))
                return true;
        }
        return false;
    }

    public static void contractRandom(Graph graph) {
        String n1 = (String) graph.nodes.keySet().stream().toArray()[(int) (Math.random() * graph.nodes.size())];
        String n2 = (String)graph.nodes.get(n1).stream().toArray()[(int) (Math.random() * graph.nodes.get(n1).size())];


        String superNode = n1 + "+" + n2;
        HashSet<String> superNodeConnections = merge(graph.nodes.get(n1), graph.nodes.get(n2));

        superNodeConnections.remove(n1);
        superNodeConnections.remove(n2);

        graph.add(superNode);

        for (String node : superNodeConnections) {
            if (graph.nodes.get(node).contains(n1))
                graph.nodes.get(node).remove(n1);

            if (graph.nodes.get(node).contains(n2))
                graph.nodes.get(node).remove(n2);

            graph.addConnection(node, superNode);
        }

        graph.nodes.remove(n1);
        graph.nodes.remove(n2);
    }

    private static HashSet<String> merge(HashSet<String> hashSet, HashSet<String> hashSet2) {
        HashSet<String> merged = new HashSet<>();
        for (String string : hashSet) {
            merged.add(string);
        }
        for (String string : hashSet2) {
            merged.add(string);
        }
        return merged;
    }

}
