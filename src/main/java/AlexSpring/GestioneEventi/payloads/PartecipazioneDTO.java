package AlexSpring.GestioneEventi.payloads;

import jakarta.validation.constraints.NotNull;

public record PartecipazioneDTO(
        @NotNull(message = "L'id non può essere nullo")
        Long id_evento
) {
}
