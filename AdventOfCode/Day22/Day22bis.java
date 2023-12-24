import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day22bis {

    record Pos(int x, int y, int z) {
    }

    record PuzzleData(ArrayList<Brick> bricks, int maxX, int maxY, int maxZ) {

    }

    record Brick(int[] x, int[] y, int[] z) implements Comparable<Brick>, Cloneable {

        public ArrayList<Pos> getOccupiedSpace() {
            ArrayList<Pos> cubes = new ArrayList<>();
            for (int i = x[0]; i <= x[1]; i++) {
                for (int j = y[0]; j <= y[1]; j++) {
                    for (int k = z[0]; k <= z[1]; k++) {
                        cubes.add(new Pos(i, j, k));
                    }
                }
            }
            return cubes;
        }

        @Override
        public int compareTo(Brick o) {
            return Integer.compare(this.z[0], o.z[0]);
        }

        @Override
        public String toString() {
            return "Brick [" + x[0] + ", " + y[0] + ", " + z[0] + " | " + x[1] + ", " + y[1] + ", " + z[1] + "]";
        }

        public int height() {
            return z[1] - z[0] + 1;
        }

        protected Object clone() {
            Brick b = new Brick(
                    new int[] { x[0], x[1] },
                    new int[] { y[0], y[1] },
                    new int[] { z[0], z[1] });
            return b;
        }

    }

    record BrickLinks(HashSet<Integer> isSupportedBy, HashSet<Integer> supports) {
    }

    public static PuzzleData parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Brick> bricks = new ArrayList<>();
        int maxX = 0, maxY = 0, maxZ = 0;

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split("~");
            String[] starts = in[0].split(",");
            String[] ends = in[1].split(",");
            int x1 = Integer.parseInt(starts[0]);
            int y1 = Integer.parseInt(starts[1]);
            int z1 = Integer.parseInt(starts[2]);
            int x2 = Integer.parseInt(ends[0]);
            int y2 = Integer.parseInt(ends[1]);
            int z2 = Integer.parseInt(ends[2]);
            bricks.add(new Brick(
                    new int[] { x1, x2 },
                    new int[] { y1, y2 },
                    new int[] { z1, z2 }));
            if (x2 > maxX)
                maxX = x2;
            if (y2 > maxY)
                maxY = y2;
            if (z2 > maxZ)
                maxZ = z2;
        }

        return new PuzzleData(bricks, maxX, maxY, maxZ);

    }

    public static void main(String[] args) {
        PuzzleData data = parseInput();
        data.bricks.sort(null);
        int[][][] map = new int[data.maxX + 1][data.maxY + 1][data.maxZ + 1];;
        for (int i = 0; i < data.bricks.size(); i++) {
            simulateBrickFall(map, data.bricks.get(i), i + 1);
        }
        int countFallingBricks = 0;
        ArrayList<Integer> disintegrables = getDisintegrables(map, data.bricks);
        for (int i = 0; i < data.bricks.size(); i++) {
            countFallingBricks += countFallingBricks(map, data.bricks, data.bricks.get(i), disintegrables);
        }
        System.out.println(countFallingBricks);
    }

    private static int countFallingBricks(int[][][] map, ArrayList<Brick> bricks, Brick brick,
            ArrayList<Integer> disintegrables) {
        int c = 0;
        int[][][] mapSimulation = new int[map.length][map[0].length][map[0][0].length];
        ArrayList<Brick> bricksSimulation = cloneBricks(bricks);
        for (int i = 0; i < bricksSimulation.size(); i++) {
            if (!bricks.get(i).equals(brick))
                simulateBrickFall(mapSimulation, bricksSimulation.get(i), i + 1);
        }

        return countDifferences(map, mapSimulation);
    }

    private static int countDifferences(int[][][] map, int[][][] mapSimulation) {
        HashSet<Integer> diffs = new HashSet<>();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                for (int z = 0; z < map[0][0].length; z++) {
                    if (mapSimulation[x][y][z] != map[x][y][z]) {
                        diffs.add(mapSimulation[x][y][z]);
                    }
                }
            }
        }
        diffs.remove(0);
        return diffs.size();
    }

    private static ArrayList<Brick> cloneBricks(ArrayList<Brick> bricks) {
        ArrayList<Brick> newBricks = new ArrayList<>();
        for (Brick brick : bricks) {
            newBricks.add((Brick) brick.clone());
        }
        return newBricks;
    }

    private static ArrayList<Integer> getDisintegrables(int[][][] map, ArrayList<Brick> bricks) {
        HashMap<Integer, BrickLinks> links = new HashMap<>();
        int c = 1;
        for (Brick brick : bricks) {
            HashSet<Integer> below = new HashSet<>();
            HashSet<Integer> above = new HashSet<>();
            ArrayList<Pos> brickSpace = brick.getOccupiedSpace();
            for (Pos pos : brickSpace) {
                if (map[pos.x][pos.y][pos.z - 1] != 0) {
                    below.add(map[pos.x][pos.y][pos.z - 1]);
                }
                if (map[pos.x][pos.y][pos.z + 1] != 0) {
                    above.add(map[pos.x][pos.y][pos.z + 1]);
                }
            }
            Pos in = brickSpace.get(0);
            below.remove(map[in.x][in.y][in.z]);
            above.remove(map[in.x][in.y][in.z]);
            links.put(c, new BrickLinks(below, above));
            c++;
        }
        ArrayList<Integer> disintegrables = new ArrayList<>();
        for (int i = 1; i <= bricks.size(); i++) {
            BrickLinks bl = links.get(i);
            if (bl.supports.size() == 0) {
                disintegrables.add(i);
                continue;
            }
            boolean removable = true;
            for (int brickAbove : bl.supports) {
                if (links.get(brickAbove).isSupportedBy.size() <= 1) {
                    removable = false;
                }
            }
            if (removable) {
                disintegrables.add(i);
            }

        }
        return disintegrables;
    }

    private static void print3DMap(int[][][] map) {
        // Print X and Z Axis
        System.out.println("--------- X and Y Axis ---------");
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                int z;
                for (z = map[0][0].length - 1; z >= 0; z--) {
                    if (map[x][y][z] != 0) {
                        System.out.print(String.format("%04d ", map[x][y][z]));
                        break;
                    }
                }
                if (z == -1) {
                    System.out.print(String.format("%04d ", map[x][y][map[0][0].length - 1]));
                }
            }
            System.out.print("|" + (y));
            System.out.println();
        }
        System.out.println("-".repeat(map.length));

        // Print X and Z Axis
        System.out.println("--------- X and Z Axis ---------");
        for (int z = map[0][0].length - 1; z >= 0; z--) {
            for (int x = 0; x < map.length; x++) {
                int y;
                for (y = 0; y < map[0].length; y++) {
                    if (map[x][y][z] != 0) {
                        System.out.print(String.format("%04d ", map[x][y][z]));
                        break;
                    }
                }
                if (y == map[0].length) {
                    System.out.print(String.format("%04d ", map[x][0][z]));
                }
            }
            System.out.print("|" + (z));
            System.out.println();
        }
        System.out.println("-".repeat(map.length));

        // Print Y and Z Axis
        System.out.println("--------- Y and Z Axis ---------");
        for (int z = map[0][0].length - 1; z >= 0; z--) {
            for (int y = 0; y < map[0].length; y++) {
                int x;
                for (x = 0; x < map.length; x++) {
                    if (map[x][y][z] != 0) {
                        System.out.print(String.format("%04d ", map[x][y][z]));
                        break;
                    }
                }
                if (x == map.length) {
                    System.out.print(String.format("%04d ", map[0][y][z]));
                }
            }
            System.out.print("|" + (z));
            System.out.println();
        }
        System.out.println("-".repeat(map[0].length));
    }

    private static void simulateBrickFall(int[][][] map, Brick brick, int label) {
        ArrayList<Pos> brickSpace = brick.getOccupiedSpace();
        for (int i = map[0][0].length - 1; i >= 0; i--) {
            if (!isThereSpace(map, brickSpace, i) || i == 0) {
                occupySpace(map, brick, brickSpace, i + 1, label);
                return;
            }
        }
    }

    private static boolean isThereSpace(int[][][] map, ArrayList<Pos> brickSpace, int z) {
        for (int i = 0; i < brickSpace.size(); i++) {
            Pos pos = brickSpace.get(i);
            int currZ = z + (brickSpace.get(i).z - brickSpace.get(0).z);
            if (currZ < map[0][0].length && map[pos.x][pos.y][currZ] != 0)
                return false;
        }
        return true;
    }

    private static void occupySpace(int[][][] map, Brick brick, ArrayList<Pos> brickSpace, int z, int label) {
        for (int i = 0; i < brickSpace.size(); i++) {
            Pos pos = brickSpace.get(i);
            int currZ = z + (brickSpace.get(i).z - brickSpace.get(0).z);
            map[pos.x][pos.y][currZ] = label;
            if (i == 0)
                brick.z[0] = currZ;
            if (i == brickSpace.size() - 1)
                brick.z[1] = currZ;
        }
    }

}
