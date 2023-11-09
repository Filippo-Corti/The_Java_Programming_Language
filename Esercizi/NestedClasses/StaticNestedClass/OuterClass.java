import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OuterClass implements Iterable<Integer> {

    private ArrayList<Integer> numbers;

    public OuterClass() {
        numbers = new ArrayList<>();
    }

    public void add(int n) {
        numbers.add(n);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new StaticNestedClass(this);
    }

    public static class StaticNestedClass implements Iterator<Integer> {

        private OuterClass o;
        private int index;

        public StaticNestedClass(OuterClass o) {
            this.o = o;
        }

        @Override
        public boolean hasNext() {
            return index < o.numbers.size();
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException("Non ci sono altri elementi");
            return o.numbers.get(index++);
        }

    }

    public static void main(String[] args) {
        OuterClass o = new OuterClass();
        o.add(10);
        o.add(20);
        o.add(30);
        for (Integer integer : o) {
            System.out.println(integer);
        }

        Iterator<Integer> i = o.iterator();
        i.next();
        i.remove(); // Questo genera UnsupportedOperationException
                    // (Ho protetto la rappresentazione)
    }

}