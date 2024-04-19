package AlexSpring.GestioneEventi.repositories;

import AlexSpring.GestioneEventi.entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EventDAO extends JpaRepository<Events,Long> {

    Optional<Events> findFirstByTitoloEvento(String titoloEvento);


}
