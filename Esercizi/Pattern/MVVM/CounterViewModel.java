public class CounterViewModel extends Generator<Integer>{

    CounterModel counter;

    public CounterViewModel(CounterModel counter) {
        this.counter = counter;
    }

    public void incrementCounter() {
        counter.increment();
        notifyAll(counter.counter);
    }
    
}