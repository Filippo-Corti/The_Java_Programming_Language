
/* Esempio di Classe che implementa l'interfaccia Iterator */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Alfabeto implements Iterator<Character> {
    //OVERVIEW: iteratore sulle lettere minusole dell'alfabeto
    
    char start;
    char current;
    char end;

    public Alfabeto() {
        start = 'a';
        current = start;
        end = 'z';
    }

    @Override
    public boolean hasNext() {
        return current <= end;
    }

    @Override
    public Character next() {
        if (current > end) 
            throw new NoSuchElementException("Alfabeto terminato. Nessuna lettera successiva");
        
        return current++;
    }

    public static void main(String[] args) {
        Alfabeto alfabeto = new Alfabeto();

        while (alfabeto.hasNext()) {
            System.out.print(alfabeto.next() + " ");
        }

        alfabeto.next();
    }






}
