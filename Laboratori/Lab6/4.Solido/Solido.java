public abstract class Solido implements Comparable<Solido> { 
    //OVERVIEW: classe astratta che descrive un Solido
    //  con attributo tipo

    private final String tipo;

    public Solido(String tipo) throws NullPointerException, IllegalArgumentException {
        if (tipo == null)
            throw new NullPointerException("Tipo null");
        if (tipo.equals("")) 
            throw new IllegalArgumentException("Tipo vuoto");

        this.tipo = tipo;
    }
    
    public String tipo() {
        return tipo;
    }
    
    public abstract double volume();

    @Override
    public int compareTo(Solido arg0) {
        if (this.volume() == arg0.volume())
            return 0;
        if (this.volume() < arg0.volume())
            return -1;
        return 1;
    }

}
