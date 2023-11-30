import java.util.ArrayList;

public class EnteDiCertificazione {
    
    private static EnteDiCertificazione ente;
    ArrayList<String> certificatiDati = new ArrayList<>();

    private EnteDiCertificazione() {}

    public static EnteDiCertificazione getEnte() {
        if (ente == null)
            ente = new EnteDiCertificazione();
        return ente;
    }

    private String getCertificate() {
        //EFFECTS: ritorna un Certificato ufficiale
        String certificato = "Certificato dall'Ente Ufficiale di Certificazione - #" + certificatiDati.size();
        certificatiDati.add(certificato);
        return certificato;
    }

    public static void main(String[] args) {
        EnteDiCertificazione ente = getEnte();
        System.out.println(ente.getCertificate()); //#0

        EnteDiCertificazione altroEnte = getEnte();
        System.out.println(altroEnte.getCertificate()); //#1
    }

}
