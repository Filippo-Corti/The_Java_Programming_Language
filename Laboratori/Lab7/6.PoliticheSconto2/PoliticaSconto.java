public interface PoliticaSconto {
    //OVERVIEW: interfaccia descrittiva di una Politica di Sconto
    
    public double calcolaSconto();

    public double getNumeroArticoli();

    public double getPrezzoArticolo();

    public default double prezzoTotaleListino() {
        return getNumeroArticoli() * getPrezzoArticolo();
    }
    

}
