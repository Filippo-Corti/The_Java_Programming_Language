public class PersonaConCiabatte implements Persona {

    Persona p;
    

    public PersonaConCiabatte(Persona p) {
        this.p = p;
    }



    @Override
    public void stampaVestiti() {
        p.stampaVestiti();
        System.out.println("e delle ciabatte");
    }
}
