package AlexSpring.GestioneEventi.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewEventDTO(

        @NotEmpty(message = "E' obbligatorio inserire il titolo dell'Evento")
        @Size(min = 5, max = 25, message = "Il titolo deve essere compreso tra i 3 e i 20 caratteri")
        String titoloEvento,
        @NotNull(message = "E' obbligatorio inserire la data dell'Evento")
        @Future(message = "La data deve essere nel futuro")
//        @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "La data deve essere nel formato dd-MM-yyyy")
        LocalDate date,
        @NotEmpty(message = "E' obbligatorio inserire l'ubicazione dell'Evento")
        @Size(min = 5, max = 25, message = "L'ubicazione deve essere compresa tra i 3 e i 20 caratteri")
        String ubicazione,
        @NotNull(message = "E' obbligatorio inserire il numero massimo di partecipanti dell'Evento")
        Integer max_partecipanti,
        @NotEmpty(message = "E' obbligatorio inserire la tipologia dell'Evento")
        @Size(min = 5, max = 25, message = "La tipologia deve essere compresa tra i 3 e i 20 caratteri")
        String tipologia_evento


) {
}
