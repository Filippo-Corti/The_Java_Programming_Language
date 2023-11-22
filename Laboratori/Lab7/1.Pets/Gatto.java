public class Gatto extends Pet {

    public Gatto(String nome) throws NullPointerException, IllegalArgumentException {
        super(nome);
    }

    @Override
    public String getVerso() {
        return "miao";
    }
}
