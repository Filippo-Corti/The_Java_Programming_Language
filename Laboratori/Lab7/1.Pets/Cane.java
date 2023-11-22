public class Cane extends Pet {

    public Cane(String nome) throws NullPointerException, IllegalArgumentException {
        super(nome);
    }

    @Override
    public String getVerso() {
        return "bau";
    }

}
