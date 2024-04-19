package AlexSpring.GestioneEventi.payloads;

import AlexSpring.GestioneEventi.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(

        //! RICORDATI CHE E' UN RECORD.
        //! QUESTO SARA' IL PAYLOAD
        //! ABBIAMO STABILITO ALCUNI CONTROLLI AL PAYLOAD
        @NotEmpty(message = "Il nome utente è obbligatorio")
        @Size(min = 3, max = 20, message = "Il nome utente deve essere compreso tra i 3 e i 20 caratteri")
        String name,

        @NotEmpty(message = "Il cognome utente è obbligatorio")
        @Size(min = 3, max = 20, message = "Il cognome utente deve essere compreso tra i 3 e i 20 caratteri")
        String surname,
        @NotEmpty(message = "L'email utente è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere come minimo 8 caratteri")
        String password,


        Role role

) {
}
