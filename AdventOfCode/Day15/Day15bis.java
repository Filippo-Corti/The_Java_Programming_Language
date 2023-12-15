import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day15bis {

    static class Instruction {
        String label;
        int focalLength;

        public Instruction(String label, int focalLength) {
            this.label = label;
            this.focalLength = focalLength;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Instruction other = (Instruction) obj;
            if (label == null) {
                if (other.label != null)
                    return false;
            } else if (!label.equals(other.label))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return label + focalLength;
        }


    }

    public static ArrayList<String> parseInput() {

        File f = new File("input.txt");
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<String> steps = new ArrayList<>(Arrays.asList(s.nextLine().split(",")));

        return steps;
    }

    public static int hash(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            value += s.charAt(i);
            value *= 17;
            value %= 256;
        }
        return value;
    }

    public static int solvePart2(ArrayList<String> input) {
        ArrayList<ArrayList<Instruction>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new ArrayList<Instruction>());
        }
        for (String instruction : input) {
            if (instruction.contains("=")) {
                //Equals Sign
                String[] parts = instruction.split("=");
                Instruction i = new Instruction(parts[0], Integer.parseInt(parts[1]));
                int boxIndex = hash(parts[0]);
                if (boxes.get(boxIndex).contains(i)) {
                    int labelIndex = boxes.get(boxIndex).indexOf(i);
                    boxes.get(boxIndex).remove(labelIndex);
                    boxes.get(boxIndex).add(labelIndex, i);
                } else {
                    boxes.get(boxIndex).add(i);
                }
            } else {
                //Dash
                Instruction i = new Instruction(instruction.replace("-", ""), 0);
                boxes.get(hash(i.label)).remove(i);
            }
        }
        return calcPower(boxes);
    }


    private static int calcPower(ArrayList<ArrayList<Instruction>> boxes) {
        int totalPower = 0;
        
        int boxIndex = 0;
        for (ArrayList<Instruction> lenses : boxes) {
            int slotIndex = 1;
            for (Instruction lense : lenses) {
                totalPower += (1 + boxIndex) * (slotIndex) * (lense.focalLength);
                slotIndex++;
            }
            boxIndex++;
        }

        return totalPower;
    }

    public static void main(String[] args) {
        ArrayList<String> input = parseInput();
        System.out.println(solvePart2(input));
    }


}
