import java.util.ArrayList;

public class Generator<T> {

    ArrayList<Listener<T>> listeners = new ArrayList<>();

    public void subscribe(Listener<T> l) {
        listeners.add(l);
    }

    public void notifyAll(T t) {
        listeners.forEach(l -> l.update(t));
    }
    
}
