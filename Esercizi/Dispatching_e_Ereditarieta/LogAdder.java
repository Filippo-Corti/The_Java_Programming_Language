import java.util.ArrayList;

public class LogAdder extends Adder {
    
    public ArrayList<Integer> aggiunti = new ArrayList<>();

    @Override
    public void add(int x) {
        aggiunti.add(x);
        super.add(x);
    }

    @Override
    public void addAll(ArrayList<Integer> l) {
        aggiunti.addAll(l);
        super.addAll(l);
    }

    @Override
    public String toString() {
        return super.toString() + " - Logged: " + aggiunti.toString();
    }

    public static void main(String[] args) {
        Adder adder = new LogAdder();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(6);
        list.add(5);
        adder.addAll(list);
        System.out.println(adder); //Sono stati loggati due volte (poiché Adder.addAll chiama LogAdder.add invece di Adder.add)
    } 

}
