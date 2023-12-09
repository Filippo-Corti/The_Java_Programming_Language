public class AdCampaignSocial extends AdCampaign{
    //OVERVIEW: classe che descrive una Campagna Pubblicitaria mediante post su Social Network. Estende AdCampaign

    //attributes
    private int nVis;
    private int nLike;

    //constructors
    public AdCampaignSocial(String nome) throws NullPointerException, IllegalArgumentException {
        super(nome);
        nVis = 0;
        nLike = 0;

        assert repOk();
    }

    //methods
    public void updatenVis(int nVisNew) throws IllegalArgumentException, CampaignClosedException {
        //MODIFIES: this
        //EFFECTS: aggiunge nVisNew alle visualizzazioni correnti di this
        //  lancia IllegalArgumentException se nVisNew < 0
        //  lancia CampaignClosedException se this è chiusa
        if (!isOpen())
            throw new CampaignClosedException("Campagna chiusa");
        if (nVisNew < 0)
            throw new IllegalArgumentException("nViewNew non valido");

        this.nVis += nVisNew;

        assert repOk();
    }

    public void updatenLike(int nLikeNew) throws IllegalArgumentException, CampaignClosedException {
        //MODIFIES: this
        //EFFECTS: aggiunge nLikeNew alle visualizzazioni correnti di this
        //  lancia IllegalArgumentException se nLikeNew < 0
        //  lancia CampaignClosedException se this è chiusa
        if (!isOpen())
            throw new CampaignClosedException("Campagna chiusa");
        if (nLikeNew < 0)
            throw new IllegalArgumentException("nLikeNew non valido");

        this.nLike += nLikeNew;

        assert repOk();
    }
    

    @Override
    public double getPerformanceValue() {
        return (nVis == 0) ? 0 : (double)nLike/nVis;
    }
    
    @Override
    public String toString() {
        return "Social " + super.toString();
    }
    
    public boolean repOk() {
        if (nVis < 0)
            return false;
        if (nLike < 0)
            return false;
        if (nLike > nVis)
            return false;
        return true;
    }


    
}
