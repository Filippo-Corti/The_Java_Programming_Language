public class Sottoclasse extends Superclasse implements Interfaccia, Interfaccia2 {

    public void saluta() {
        super.saluta();
    }

    @Override
    public void stampaCiao() {
        // TODO Auto-generated method stub
        System.out.println("Ciao dalla Sottoclasse");
    }

    public static void main(String[] args) {
        Sottoclasse s = new Sottoclasse();

        // s.stampaCiao(); //"Ciao dalla Superclasse"

        s.saluta(); // "Ciao dalla Sottoclasse" (sebbene dovrebbe fare il percorso
                    // sottoclasse.saluta() -> superclasse.saluta() -> superclasse.stampaCiao(),
                    // l'ultimo step finisce invece in sottoclasse.stampaCiao())
    }

}
