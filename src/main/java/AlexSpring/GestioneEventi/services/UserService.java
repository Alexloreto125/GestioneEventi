package AlexSpring.GestioneEventi.services;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.BadRequestException;
import AlexSpring.GestioneEventi.payloads.NewUserDTO;
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

    //! CREIAMO IL METODO PER RIPORTARE GLI USERS
    //TODO ABBIAMO USATO IL PAGEABLE E IL METODO USER DAO INTEGRATO CON JPA REPOSITORY
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

        User newUser = new User(body.name(), body.surname(), body.name(), body.password());

        return this.userDAO.save(newUser);
    }
}
