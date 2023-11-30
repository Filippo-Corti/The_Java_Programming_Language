interface Persona {

    public void stampaVestiti();

    public static void main(String[] args) {
        Persona p1 = new PersonaConScarpe(new PersonaConPantaloni( new PersonaConMaglietta()));
        Persona p2 = new PersonaConCiabatte(new PersonaConPantaloncini( new PersonaConCamicia()));

        p1.stampaVestiti();
        System.out.println();
        p2.stampaVestiti();


        //Persona 
        //  > Persona Con Maglietta
        //      > Persona Con Maglietta e Pantaloni
        //          > Persona Con Maglietta, Pantaloni e Scarpe
        //          > Persona Con Maglietta, Pantaloni e Ciabatte
        //      > Persona Con Maglietta e Pantaloncini
        //          > Persona Con Maglietta, Pantaloncini e Scarpe
        //          > Persona Con Maglietta, Pantaloncini e Ciabatte
        //  > Persona Con Camicia
        //      > Persona Con Camicia e Pantaloni
        //          > Persona Con Camicia, Pantaloni e Scarpe
        //          > Persona Con Camicia, Pantaloni e Ciabatte
        //      > Persona Con Camicia e Pantaloncini
        //          > Persona Con Camicia, Pantaloncini e Scarpe
        //          > Persona Con Camicia, Pantaloncini e Ciabatte

        //In tutto 14 possibili outfit, con 6 Classi
    }

}