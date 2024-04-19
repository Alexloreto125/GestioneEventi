package AlexSpring.GestioneEventi.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class DuplicatedParticipationException extends RuntimeException {

    private List<ObjectError> errorList;

    public DuplicatedParticipationException(String message) {
        super(message);
    }

    public DuplicatedParticipationException(List<ObjectError> errorList) {
        super("Ci sono stati errori di duplicazione");
        this.errorList = errorList;
    }
}
