import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map.Entry;

public class Day23bis {

    static HashSet<Integer> pathsLenghts = new HashSet<>();

    static class Graph {
        ArrayList<Node> nodes = new ArrayList<>();

        public void add(Node node) {
            if (!nodes.contains(node))
                nodes.add(node);
        }
    }

    static class Node {
        Pos pos;
        HashMap<Node, Integer> adjacentNodes = new HashMap<>();

        public Node(Day23bis.Pos pos) {
            this.pos = pos;
        }

        public void connect(Node node, int cost) {
            adjacentNodes.put(node, cost);
        }

        @Override
        public String toString() {
            return "Node [pos=" + pos + ", adjacentNodes=" + adjacentNodes.size() + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((pos == null) ? 0 : pos.hashCode());
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
            if (pos == null) {
                if (other.pos != null)
                    return false;
            } else if (!pos.equals(other.pos))
                return false;
            return true;
        }

    }

    enum Direction {
        UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGHT(0, 1);

        int deltaRow, deltaCol;

        Direction(int deltaRow, int deltaCol) {
            this.deltaRow = deltaRow;
            this.deltaCol = deltaCol;
        }

        public static Direction getFromChar(char c) {
            switch (c) {
                case 'v':
                    return DOWN;
                case '^':
                    return UP;
                case '<':
                    return LEFT;
                case '>':
                    return RIGHT;
            }
            return null;
        }
    }

    record Pos(int row, int col, char c) {

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
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
            Pos other = (Pos) obj;
            if (row != other.row)
                return false;
            if (col != other.col)
                return false;
            return true;
        }

    }

    record Link(Node n1, Node n2, int cost) {
    }

    public static Pos[][] parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<ArrayList<Pos>> matrix = new ArrayList<>();

        int r = 0;
        while (s.hasNextLine()) {
            ArrayList<Pos> row = new ArrayList<>();
            String in = s.nextLine();
            for (int c = 0; c < in.length(); c++) {
                row.add(new Pos(r, c, in.charAt(c)));
            }
            r++;
            matrix.add(row);
        }

        Pos[][] map = new Pos[matrix.size()][matrix.get(0).size()];

        r = 0;
        for (ArrayList<Pos> row : matrix) {
            int c = 0;
            for (Pos pos : row) {
                map[r][c] = pos;
                c++;
            }
            r++;
        }

        return map;

    }

    public static void main(String[] args) {
        Pos[][] map = parseInput();
        Graph graph = mapToGraph(map);
        Node startNode = findStart(graph);
        Node endNode = findEnd(graph, map.length - 1);
        HashSet<Node> visited = new HashSet<>();
        findLongestHike(graph, startNode, endNode, visited, 0);
        System.out.println(pathsLenghts.stream().max((a, b) -> a - b));
    }

    private static Node findEnd(Graph graph, int row) {
        for (Node node : graph.nodes) {
            if (node.pos.row == row) {
                return node;
            }
        }
        return null;
    }

    private static Node findStart(Graph graph) {
        for (Node node : graph.nodes) {
            if (node.pos.row == 0) {
                return node;
            }
        }
        return null;
    }

    private static Graph mapToGraph(Pos[][] map) {

        Graph graph = new Graph();
        ArrayList<Link> links = new ArrayList<>();
        HashSet<Pos> visited = new HashSet<>();
        findNextEdge(map, links, visited, new Node(findStart(map)), findStart(map));
        for (Link link : links) {
            graph.add(link.n1);
            graph.add(link.n2);
            Node n3 = graph.nodes.get(graph.nodes.indexOf(link.n1));
            Node n4 = graph.nodes.get(graph.nodes.indexOf(link.n2));
            n3.connect(n4, link.cost);
            n4.connect(n3, link.cost);
        }

        return graph;

    }

    private static void findNextEdge(Pos[][] map, ArrayList<Link> links, HashSet<Pos> visited, Node node, Pos prev) {
        visited.add(node.pos);
        int cost = 0;
        Pos curr = node.pos;
        node = new Node(prev);
        while (true) {
            cost++;
            ArrayList<Pos> possibleMoves = new ArrayList<>();
            for (Direction d : Direction.values()) {
                int newRow = curr.row + d.deltaRow;
                int newCol = curr.col + d.deltaCol;

                if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
                    if (map[newRow][newCol].c != '#') {
                        if (newRow == map.length - 1) {
                            links.add(new Link(node, new Node(map[newRow][newCol]), cost+1));
                            return;
                        }
                        possibleMoves.add(map[newRow][newCol]);
                    }
                }
            }
            possibleMoves.remove(prev);
            if (possibleMoves.size() == 1) {
                prev = curr;
                curr = possibleMoves.get(0);
            } else {
                Node node2 = new Node(curr);
                links.add(new Link(node, node2, cost));
                for (Pos pos : possibleMoves) {
                    if (!visited.contains(pos)) {
                        findNextEdge(map, links, visited, new Node(pos), curr);
                    }
                }
                return;
            }
        }
    }

    private static Pos findStart(Pos[][] map) {
        for (Pos pos : map[0]) {
            if (pos.c == '.') {
                return pos;
            }
        }
        return null;
    }

    private static int findLongestHike(Graph graph, Node startNode, Node endNode, HashSet<Node> visited, int dist) {
        //System.out.println("Currently on " + startNode + " (" + dist + ")");

        visited.add(startNode);

        if (startNode.equals(endNode))
            return dist;

        for (Entry<Node, Integer> entry : startNode.adjacentNodes.entrySet()) {
            if (visited.contains(entry.getKey())) {
                continue;
            }
            HashSet<Node> nextVisited = new HashSet<>(visited);
            int result = findLongestHike(graph, entry.getKey(), endNode,
                    nextVisited, dist + entry.getValue());
            if (nextVisited.contains(endNode)) {
                System.out.println("Il Percorso arriva in fondo! " + result);
                pathsLenghts.add(result);
            }
        }
        return 0;

    }

}
