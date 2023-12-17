import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day17 {

    public static Graph parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        while (s.hasNextLine()) {
            ArrayList<Integer> row = new ArrayList<>();
            String in = s.nextLine();
            for (int i = 0; i < in.length(); i++) {
                row.add(Integer.parseInt(in.charAt(i) + ""));
            }
            matrix.add(row);
        }

        Graph graph = new Graph();

        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> intRow = matrix.get(i);
            for (int j = 0; j < intRow.size(); j++) {
                Node node = new Node("Node " + i + "-" + j, intRow.get(j), i, j);
                graph.addNode(node);
            }
        }

        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> intRow = matrix.get(i);
            for (int j = 0; j < intRow.size(); j++) {
                Node curr = graph.getNode("Node " + i + "-" + j);

                try {
                    Node next = graph.getNode("Node " + i + "-" + (j + 1));
                    curr.addDestination(next, next.getValue());
                } catch (NullPointerException e) {
                }

                try {
                    Node below = graph.getNode("Node " + (i + 1) + "-" + j);
                    curr.addDestination(below, below.getValue());
                } catch (NullPointerException e) {
                }

            }
        }

        return graph;

    }

    public static void main(String[] args) {

        Graph graph = parseInput();

        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.getNode("Node 0-0"));

        System.out.println(graph);

    }

}