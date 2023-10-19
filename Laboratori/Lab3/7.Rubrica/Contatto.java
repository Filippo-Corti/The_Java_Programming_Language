public class Contatto {
    //OVERVIEW: classe mutabile che modella un Contatto, elemento di una rubrica

    String nome, cognome;
    String numeroDiTelefono;
    String email;

    // constructors
    public Contatto(String nome, String cognome) throws IllegalArgumentException {
        // EFFECTS: istanzia un nuovo Contatto
        // lancia IllegalArgumentExceptions se nome o cognome sono null o vuoti
        if (nome == null || nome.equals("") || cognome == null || cognome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");

        this.nome = nome;
        this.cognome = cognome;
    }

    public Contatto(String nome, String cognome, String numeroDiTelefono, String email)  throws IllegalArgumentException {
        // EFFECTS: istanzia un nuovo Contatto
        // lancia IllegalArgumentExceptions se nome o cognome sono null o vuoti (sono
        // ammessi numeroDiTelefono e email nulli o vuoti)
        if (nome == null || nome.equals("") || cognome == null || cognome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.nome = nome;
        this.cognome = cognome;
        this.numeroDiTelefono = numeroDiTelefono;
        this.email = email;
    }

    //methods
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) throws IllegalArgumentException {
        if (cognome == null || cognome.equals(""))
            throw new IllegalArgumentException("Non sono ammessi campi vuoti o nulli");
        this.cognome = cognome;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " " + numeroDiTelefono + " " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contatto other = (Contatto) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        if (numeroDiTelefono == null) {
            if (other.numeroDiTelefono != null)
                return false;
        } else if (!numeroDiTelefono.equals(other.numeroDiTelefono))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }


}
