public abstract class Pet {
    //OVERVIEW: classe astratta che descrive un animale domestico
    
    //attributes
    public final String nome;

    //contructors
    public Pet(String nome) throws NullPointerException, IllegalArgumentException {
        //MODIFIES: this
        //EFFECTS: inizializza un nuovo Pet
        //  lancia NullPointerExceptions se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if (nome == null)
            throw new NullPointerException("Nome nullo");
        
        if (nome.equals(""))   
            throw new IllegalArgumentException("Nome vuoto");
        
        this.nome = nome;

        assert repOk();
    }

    //methods
    public void verso() {
        System.out.println(this.nome + " dice " + getVerso());    
    }

    public abstract String getVerso();

    public boolean repOk() {
        if (nome == null)
            return false;
        
        if (nome.equals(""))
            return false;
    
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
