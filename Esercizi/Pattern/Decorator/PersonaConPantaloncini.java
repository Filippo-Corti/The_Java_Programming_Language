public class PersonaConPantaloncini implements Persona {

    Persona p;


    public PersonaConPantaloncini(Persona p) {
        this.p = p;
    }

    @Override
    public void stampaVestiti() {
        p.stampaVestiti();
        System.out.println("e dei Pantaloncini");
    }
} 
