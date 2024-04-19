package AlexSpring.GestioneEventi.repositories;

import AlexSpring.GestioneEventi.entities.Events;
import AlexSpring.GestioneEventi.entities.Partecipazioni;
import AlexSpring.GestioneEventi.entities.User;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartecipazioniDAO extends JpaRepository<Partecipazioni,Long> {

    Partecipazioni findByUserAndEvent(User user, Events event);
}
