package AlexSpring.GestioneEventi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(

        @NotEmpty(message = "L'email è obbligatoria per effettuare il login")
        @Email(message = "Email non valida")
        String email,

        @NotEmpty(message = "La password è obbligatoria per effettuare il login")
        String password) {
}
