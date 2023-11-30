public class CounterView implements Listener<Integer>{

    @Override
    public void update(Integer t) {
        System.out.println("Counter is now " + t);
    }
}
