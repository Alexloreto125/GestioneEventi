package AlexSpring.GestioneEventi.repositories;

import AlexSpring.GestioneEventi.entities.Partecipazioni;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartecipazioniDAO extends JpaRepository<Partecipazioni,Long> {
}
