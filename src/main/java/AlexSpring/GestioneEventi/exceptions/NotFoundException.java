package AlexSpring.GestioneEventi.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.UUID;

@Getter
public class NotFoundException extends RuntimeException{

    private List<ObjectError> errorList;
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(List<ObjectError> errorList){
        super("Ci sono stati errori");
        this.errorList= errorList;

    }
    public NotFoundException(UUID uuid){

        super("Record con id "+ uuid+ " non è stato trovato");
    }


}
