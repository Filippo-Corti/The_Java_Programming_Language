import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CompanyAds implements Iterable<AdCampaign> {
    //OVERVIEW: classe che descrive un'azienda e le sue campagne pubblicitarie

    //attributes
    private final String nome;
    private ArrayList<AdCampaign> campagne = new ArrayList<>();

    //constructors
    public CompanyAds(String nome)  throws NullPointerException, IllegalArgumentException{
        //MODIFIES: this
        //EFFECTS: inializza una nuova CompanyAds
        //  lancia NullPointerException se nome è nullo
        //  lancia IllegalArgumentException se nome è vuoto
        if (nome == null)  
            throw new NullPointerException("Nome nullo");
        if (nome.equals(""))  
            throw new IllegalArgumentException("Nome vuoto");
        
        this.nome = nome;
    }

    //methods
    public String getNome() {
        return nome;
    }

    public void add(AdCampaign a) throws NullPointerException, CampaignExistsException {
        //MODIFIES: this
        //EFFECTS: aggiunge a a this 
        //  lancia NullPointerException se a è nullo
        //  lancia CampaignExistsException se a è già in this
        if (a == null)
            throw new NullPointerException("AdCampaign nulla");

        if (campagne.contains(a)) 
            throw new CampaignExistsException("Campagna gia presente");
        
        campagne.add((AdCampaign) a.clone());

        assert repOk();
    }

    public AdCampaign get(String nome) throws NullPointerException, NoSuchElementException {
        //EFFECTS: ritorna la Campagna con nome specificato
        //  lancia NullPointerException se nome è nullo
        //  lancia NoSuchElementException se non esiste la campagna specificata
        if (nome == null)
            throw new NullPointerException("Nome nullo");

        for (AdCampaign adCampaign : campagne) {
            if (adCampaign.getNome().equals(nome))
                return adCampaign;
        }
        
        throw new NoSuchElementException("Campagna non presente");
    }

    @Override
    public Iterator<AdCampaign> iterator() {
        //EFFECTS: ritorna un iteratore sulle campagne, ordinate
        return new Iterator<AdCampaign>() {

            Iterator<AdCampaign> i = initIterator();

            @SuppressWarnings("unchecked")
            public Iterator<AdCampaign> initIterator() {
                ArrayList<AdCampaign> sortedCampagne = (ArrayList<AdCampaign>) campagne.clone();
                sortedCampagne.sort(null);
                return sortedCampagne.iterator();
            }

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public AdCampaign next() {
                return i.next();
            }

        };
    }

    @Override
    public String toString() {
        String res = "Campagne di " + nome + "\n";
        for (AdCampaign adCampaign : this) {
            res += "\t" + adCampaign + "\n";
        }
        return res;
    }

    public boolean repOk() {
        if (campagne == null)
            return false;
    
        for (AdCampaign adCampaign : campagne) {
            if (adCampaign == null)
                return false;
        }

        return true;
    }


}
