public interface Figura extends Comparable<Figura> { 
    //OVERVIEW: interfaccia che accomuna Figure Piane

    public double perimetro();

    @Override
    public default int compareTo(Figura f) {
        return Double.compare(perimetro(), f.perimetro());
    }

}
