package AlexSpring.GestioneEventi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Events {
    @Id
    @GeneratedValue
    private long id;

    private String titoloEvento;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String ubicazione;
    private Integer max_partecipanti;
    private String tipologia_evento;

    @OneToMany(mappedBy = "event")
    private Set<Partecipazioni> partecipazioni= new HashSet<>();

    public Events(String titoloEvento,String tipologia_evento, LocalDate date, String ubicazione, int max_partecipanti ) {
        this.titoloEvento = titoloEvento;
        this.date = date;
        this.ubicazione = ubicazione;
        this.max_partecipanti = max_partecipanti;
        this.tipologia_evento = tipologia_evento;
    }
}