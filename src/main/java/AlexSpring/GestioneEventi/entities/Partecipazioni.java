package AlexSpring.GestioneEventi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Partecipazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Partecipazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;




    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Events event;

    @JsonIgnore
    private String name;
    @JsonIgnore
    private String surname;


    public Partecipazioni(User user, Events event) {
        this.user = user;
        this.event = event;
    }
}
