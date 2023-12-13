import java.util.ArrayList;

public class Adder {

    public int result = 0;

    public void add(int x) {
        result += x;
    }

    public void addAll(ArrayList<Integer> l) {
        for (int x : l) {
            this.add(x);
        }
    }


    @Override
    public String toString() {
        return "Result: " + result;
    }
}
