public interface Figura extends Comparable<Figura> { 
    //OVERVIEW: interfaccia che accomuna Figure Piane. Comparabile secondo il perimetro

    public double perimetro();

    @Override
    public default int compareTo(Figura f) {
        return Double.compare(f.perimetro(), perimetro()); //Ordine decrescente
    }

    

}
