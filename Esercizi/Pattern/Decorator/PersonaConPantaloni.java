public class PersonaConPantaloni implements Persona {

    Persona p;


    public PersonaConPantaloni(Persona p) {
        this.p = p;
    }

    @Override
    public void stampaVestiti() {
        p.stampaVestiti();
        System.out.println("e dei Pantaloni");
    }
} 
