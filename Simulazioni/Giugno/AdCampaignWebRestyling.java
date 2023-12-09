public class AdCampaignWebRestyling extends AdCampaign {
    // OVERVIEW: classe che descrive una Campagna Pubblicitaria basata sul redesign
    // del sito web. Estende AdCampaign

    // attributes
    private int nVis;
    private double tVis;
    private final double tVisOld;

    // constructors
    public AdCampaignWebRestyling(String nome, double tVisOld) throws NullPointerException, IllegalArgumentException {
        // MODIFIES: this
        // EFFECTS: inizializza una nuova Campagna
        // lancia IllegalArgumentException se tVisOld <= 0
        super(nome);

        if (tVisOld <= 0)
            throw new IllegalArgumentException("tVisOld non valido");

        this.tVisOld = tVisOld;
        nVis = 0;
        tVis = 0;

        assert repOk();
    }

    // methods
    public void updatenVis(int nVisNew, double tVisNew) throws IllegalArgumentException, CampaignClosedException {
        // MODIFIES: this
        // EFFECTS: aggiunge nVisNew alle visualizzazioni correnti di this e aggiorna il
        // tempo medio di visualizzazione
        // lancia IllegalArgumentException se nVisNew < 0 o tVisNew <= 0
        // lancia CampaignClosedException se this è chiusa
        if (!isOpen())
            throw new CampaignClosedException("Campagna chiusa");
        if (nVisNew < 0)
            throw new IllegalArgumentException("nViewNew non valido");
        if (tVisNew <= 0)
            throw new IllegalArgumentException("tVisNew non valido");

        this.tVis = ((nVis * tVis) + (nVisNew * tVisNew)) / (nVis + nVisNew);
        this.nVis += nVisNew;

        assert repOk();
    }


    @Override
    public double getPerformanceValue() {
        return tVis / (tVis + tVisOld);
    }

    @Override
    public String toString() {
        return "WebRestyle " + super.toString();
    }

    public boolean repOk() {
        if (nVis < 0)
            return false;
        if (tVis < 0)
            return false;
        return true;
    }

}
