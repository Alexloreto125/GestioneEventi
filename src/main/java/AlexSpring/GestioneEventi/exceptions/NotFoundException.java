package AlexSpring.GestioneEventi.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID uuid){

        super("Record con id "+ uuid+ " non è stato trovato");
    }

    public NotFoundException(String email){
        super(email);
    }
}
