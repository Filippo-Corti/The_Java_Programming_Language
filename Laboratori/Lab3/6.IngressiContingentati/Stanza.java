public class Stanza {
    //OVERVIEW: una classe che descrive una Stanza avente una capienza massima e in cui entrano ed escono persone

    final int capienza;
    int persone;

    //constructors
    public Stanza(int capienza) {
        this.capienza = capienza;
        this.persone = 0;
    }

    //methods
    public int getPersone() {
        return persone;
    }

    public void personaEsce() throws StanzaGiaVuotaException {
        //MODIFIES: this
        //EFFECTS: persone_post = persone - 1;
        //  lancia StanzaGiaVuotaException se persone = 0
        if (this.persone == 0) {
            throw new StanzaGiaVuotaException("Nessuno nella stanza");
        }

        this.persone --;
    }

    public void personaEntra() throws StanzaGiaPienaException {
        //MODIFIES: this
        //EFFECTS: persone_post = persone + 1;
        //  lancia StanzaGiaPienaException se persone = capienza
        if (this.persone == this.capienza) {
            throw new StanzaGiaVuotaException("Capienza massima raggiunta nella stanza");
        }

        this.persone ++;
    }
    




}
