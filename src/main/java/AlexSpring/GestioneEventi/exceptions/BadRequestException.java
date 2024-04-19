package AlexSpring.GestioneEventi.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException{

    //! DOBBIAMO ISTANZIARE UNA LISTA DI OGGETTI PER GLI ERRORI

    private List<ObjectError> errorList;

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList){
        super("Ci sono stati errori di validazione nel payload");
        this.errorList= errorList;

    }
}
