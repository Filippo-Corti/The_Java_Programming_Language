public class Cavia extends Pet {

    public Cavia(String nome) throws NullPointerException, IllegalArgumentException {
        super(nome);
    }

    @Override
    public String getVerso() {
        return "squit";
    }

}
