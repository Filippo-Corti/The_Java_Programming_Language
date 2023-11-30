public class Main {

    public static void main(String[] args) {
        CounterModel model = new CounterModel();
        CounterView view = new CounterView();
        CounterViewModel view_model = new CounterViewModel(model);
        view_model.subscribe(view);


        view_model.incrementCounter();
        view_model.incrementCounter();
        view_model.incrementCounter();
        view_model.incrementCounter();

    }
    
}
