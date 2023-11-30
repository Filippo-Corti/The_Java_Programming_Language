public class PersonaConScarpe implements Persona {

    Persona p;
    

    public PersonaConScarpe(Persona p) {
        this.p = p;
    }



    @Override
    public void stampaVestiti() {
        p.stampaVestiti();
        System.out.println("e delle scarpe");
    }
}
