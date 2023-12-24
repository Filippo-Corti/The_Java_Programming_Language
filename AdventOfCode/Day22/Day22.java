import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day22 {

    record Pos(int x, int y, int z) {
    }

    record PuzzleData(ArrayList<Brick> bricks, int maxX, int maxY, int maxZ) {

    }

    record Brick(int[] x, int[] y, int[] z) implements Comparable<Brick> {

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
        System.out.println(data.bricks);
        int[][][] map = new int[data.maxX + 1][data.maxY + 1][data.maxZ + 1];
        for (int i = 0; i < data.bricks.size(); i++) {
            simulateBrickFall(map, data.bricks.get(i), i + 1);
        }
        print3DMap(map);
        System.out.println(countDisintegrables(map, data.bricks));
        System.out.println(data.bricks.get(2).getOccupiedSpace());
        System.out.println(contaPezzi(map));
        System.out.println(contaDimensioni(data.bricks));
    }

    private static int contaDimensioni(ArrayList<Brick> bricks) {
        return bricks.stream().mapToInt((b) -> b.getOccupiedSpace().size()).sum();
    }

    private static int contaPezzi(int[][][] map) {
        int c = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                for (int z = 0; z < map[0][0].length; z++) {
                    if (map[x][y][z] != 0)
                        c++;
                }
            }
        }
        return c;
    }

    record BrickLinks(HashSet<Integer> isSupportedBy, HashSet<Integer> supports) {
    }

    private static int countDisintegrables(int[][][] map, ArrayList<Brick> bricks) {
        int c = 1;
        HashMap<Integer, BrickLinks> links = new HashMap<>();
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
        c = 0;
        for (int i = 1; i <= bricks.size(); i++) {
            BrickLinks bl = links.get(i);
            System.out.println(i + ": " + bl.isSupportedBy + " - " + bl.supports);
            if (bl.supports.size() == 0) {
                c++;
                System.out.println(i + " is removable");
                continue;
            }
            boolean removable = true;
            for (int brickAbove : bl.supports) {
                if (links.get(brickAbove).isSupportedBy.size() <= 1) {
                    removable = false;
                }
            }
            if (removable) {
                c++;
                System.out.println(i + " is removable");
            }

        }
        return c;
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
