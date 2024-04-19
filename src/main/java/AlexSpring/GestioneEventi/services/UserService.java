package AlexSpring.GestioneEventi.services;

import AlexSpring.GestioneEventi.entities.Events;
import AlexSpring.GestioneEventi.entities.Partecipazioni;
import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.exceptions.NotFoundException;
import AlexSpring.GestioneEventi.payloads.NewUserDTO;
import AlexSpring.GestioneEventi.repositories.EventDAO;
import AlexSpring.GestioneEventi.repositories.PartecipazioniDAO;
import AlexSpring.GestioneEventi.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private PartecipazioniDAO partecipazioniDAO;

    //! CREIAMO IL METODO PER RIPORTARE GLI USERS
    //TODO ABBIAMO USATO IL PAGEABLE E IL METODO USER DAO INTEGRATO CON JPA REPOSITORY
    //TODO SOLO GLI ORGANIZZATORI POSSONO AVERE QUESTO METODO
    public Page<User> getUsers(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);

    }




    public User save(NewUserDTO body) {
        //? L'email è già in uso? verifico
        this.userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già in uso");
        });

        User newUser = new User(body.name(), body.surname(), body.email(), body.password());

        return this.userDAO.save(newUser);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }



    public Partecipazioni addUserToEvent(User user, Long eventId){
        Events event= this.eventDAO.findById(eventId).orElseThrow(()->new NotFoundException(String.valueOf(eventId)));
        Partecipazioni partecipazioni= new Partecipazioni();
//        partecipazioni.setUser(user);
        partecipazioni.setName(user.getName());
        partecipazioni.setSurname(user.getUsername());
        partecipazioni.setEvent(event);
      return   partecipazioniDAO.save(partecipazioni);
    }

}
