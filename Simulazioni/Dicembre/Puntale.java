public class Puntale extends Decorazione {
    //OVERVIEW: Puntale è una Decorazione particolare

    public Puntale(String nome, double peso) throws NullPointerException, IllegalArgumentException {
        super(nome, peso);
    }

    @Override
    public String toString() {
        return super.toString() + ", puntale";
    }

    @Override
    public Puntale clone(){
        return new Puntale(nome, peso);
    }

}
