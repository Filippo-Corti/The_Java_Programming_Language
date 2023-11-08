
public class Esame implements Cloneable {
    // OVERVIEW: classe immutabile che descrive un esame

    // attributes
    String nome;
    int voto;

    public Esame(String nome, int voto) throws IllegalArgumentException {
        if (nome == null || nome.equals("") || voto < 18 || voto > 30)
            throw new IllegalArgumentException("Parametri di Esame non validi");

        this.nome = nome;
        this.voto = voto;

        assert repOk();
    }

    public boolean repOk() {
        if (nome == null || nome.equals(""))
            return false;

        if (voto < 18 || voto > 30) 
            return false;
        
        return true;
    }

    public String getNome() {
        return nome;
    }

    public int getVoto() {
        return voto;
    }

    @Override
    public String toString() {
        return "Esame [nome=" + nome + ", voto=" + voto + "]";
    }

    @Override
    protected Object clone() {
        Esame nuovo = null;

        try {
            nuovo = (Esame) super.clone();
        } catch (CloneNotSupportedException e) {
            nuovo = new Esame(this.nome, this.voto);
        }

        return nuovo;

    }

}
