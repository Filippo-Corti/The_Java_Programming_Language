public abstract class AdCampaign implements Comparable<AdCampaign>, Cloneable {
    //OVERVIEW: classe astratta che descrive una Campagna Pubblicitaria
    
    //attributes
    private final String nome;
    private boolean aperta;

    //constructors
    public AdCampaign(String nome) throws NullPointerException, IllegalArgumentException{
        //MODIFIES: this
        //EFFECTS: inializza una nuova AdCampaign
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if (nome == null)  
            throw new NullPointerException("Nome nullo");
        if (nome.equals(""))  
            throw new IllegalArgumentException("Nome vuoto");
        
        this.nome = nome;
        aperta = true;
    }

    //methods
    public String getNome() {
        return nome;
    }

    public boolean isOpen() {
        return aperta;
    }

    public void close() {
        //MODIFIES: this
        //EFFECTS: chiude this
        aperta = false;
    }

    public abstract double getPerformanceValue();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdCampaign other = (AdCampaign) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public int compareTo(AdCampaign o) {
        return Double.compare(this.getPerformanceValue(), o.getPerformanceValue());
    }

    @Override
    public String toString() {
        return "name: " + nome + " performance: " + getPerformanceValue();
    }

    @Override
    protected Object clone() {
        AdCampaign ac = null;
        try {
            ac = (AdCampaign) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return ac;
    }

}
