public class Persona {
    //OVERVIEW: Classe che descrive una Persona con un nome. Non mutabile

    //attributes
    private String nome;

    //contructor 
    public Persona(String nome) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza una nuova persona 
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if(nome == null)
            throw new NullPointerException("nome nullo");
        if(nome.equals(""))
            throw new IllegalArgumentException("nome vuoto");
        
        this.nome = nome;

        assert repOk();
    }

    //methods
    public String getNome() {
        return this.nome;
    } 

    public boolean repOk() {
        if (nome == null)
            return false;
        if (nome.equals(""))
            return false;
        
        return true;
    }

    @Override
    public String toString() {
        return "Persona di nome " + nome;
    }
}
